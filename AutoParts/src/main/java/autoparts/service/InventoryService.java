package autoparts.service;

import autoparts.logger.Logger;
import autoparts.model.*;
import autoparts.exception.*;
import java.util.stream.Collectors;

import java.util.*;

public class InventoryService {
    private final Map<String, AutoPart> parts;
    private final Map<String, StorageZone> zones;
    private final Map<String, List<PartBatch>> batches;
    private final Logger logger;
    
    public InventoryService(Logger logger) {
        this.parts = new HashMap<>();
        this.zones = new HashMap<>();
        this.batches = new HashMap<>();
        this.logger = logger;
    }
    
    // ОБРАЗЕЦ РЕАЛИЗАЦИИ (занятие 2)
    public void addPart(AutoPart part) {
        parts.put(part.getId(), part);
        batches.put(part.getId(), new ArrayList<>());
        logger.log("[CATALOG] Добавлена запчасть: " + part.getName());
    }

    public PartBatch receiveBatch(AutoPart autoPart, int quantity, String zoneId,
                                  String certificateNumber)
            throws CertificateExpiredException {
        // 1. Найти зону
        StorageZone zone = zones.get(zoneId);
        if (zone == null) {
            throw new IllegalArgumentException("Зона не найдена: " + zoneId);
        }
        // 2. Проверить вместимость
        double weight = autoPart.getWeightKg() * quantity;
        if (!zone.canAccept(quantity, weight)) {
            throw new IllegalArgumentException("Недостаточно места в зоне " + zoneId);
        }

        // 3. Создать партию
        PartBatch batch = new PartBatch(
                "BATCH-" + System.currentTimeMillis(),
                autoPart,
                quantity,
                null, // expiryDate пока null
                certificateNumber,
                zone
        );

        // 4. Добавить в хранилище
        batches.get(autoPart.getId()).add(batch);

        // 5. Обновить загрузку зоны
        zone.addLoad(quantity, weight);

        // 6. Логирование
        logger.log("[ПРИЁМКА] Партия: " + batch.getBatchId() +
                ", запчасть: " + autoPart.getName() +
                ", кол-во: " + quantity +
                ", зона: " + zoneId);

        return batch;
    }

    public void relocateBatch(String batchId, String toZoneId) {
        // 1. Найти партию
        PartBatch foundBatch = null;
        StorageZone oldZone = null;

        for (List<PartBatch> batchList : batches.values()) {
            for (PartBatch batch : batchList) {
                if (batch.getBatchId().equals(batchId)) {
                    foundBatch = batch;
                    oldZone = batch.getZone();
                    break;
                }
            }
            if (foundBatch != null) break;
        }

        if (foundBatch == null) {
            throw new IllegalArgumentException("Партия не найдена: " + batchId);
        }

        // 2. Найти новую зону
        StorageZone newZone = zones.get(toZoneId);
        if (newZone == null) {
            throw new IllegalArgumentException("Зона не найдена: " + toZoneId);
        }

        // 3. Проверить вместимость новой зоны
        double weight = foundBatch.getAutoPart().getWeightKg() * foundBatch.getQuantity();
        if (!newZone.canAccept(foundBatch.getQuantity(), weight)) {
            throw new IllegalArgumentException("Недостаточно места в зоне " + toZoneId);
        }

        // 4. Переместить
        oldZone.removeLoad(foundBatch.getQuantity(), weight);
        newZone.addLoad(foundBatch.getQuantity(), weight);
        foundBatch.setZone(newZone);

        logger.log("[ПЕРЕМЕЩЕНИЕ] Партия " + batchId +
                " перемещена из зоны " + oldZone.getId() +
                " в зону " + toZoneId);
    }
    

    public void reserveParts(AutoPart autoPart, int amount) throws InsufficientStockException {
        List<PartBatch> partBatches = batches.get(autoPart.getId());
        if (partBatches == null || partBatches.isEmpty()) {
            throw new InsufficientStockException(
                    "Нет партий для запчасти " + autoPart.getName(),
                    autoPart.getId(),
                    amount,
                    0
            );
        }

        // FEFO: сортируем по дате получения (сначала старые)
        partBatches.sort(Comparator.comparing(PartBatch::getReceivedDate));

        int remaining = amount;
        int totalAvailable = partBatches.stream().mapToInt(PartBatch::getAvailable).sum();

        if (totalAvailable < amount) {
            throw new InsufficientStockException(
                    "Недостаточно товара. Доступно: " + totalAvailable,
                    autoPart.getId(),
                    amount,
                    totalAvailable
            );
        }

        for (PartBatch batch : partBatches) {
            if (remaining <= 0) break;

            int available = batch.getAvailable();
            int toReserve = Math.min(available, remaining);

            batch.reserve(toReserve);
            remaining -= toReserve;

            logger.log("[РЕЗЕРВ] Партия: " + batch.getBatchId() +
                    ", зарезервировано: " + toReserve);
        }

        logger.log("[РЕЗЕРВ] Всего зарезервировано: " + amount +
                " шт. запчасти " + autoPart.getName());
    }

    public void releaseReservation(AutoPart autoPart, int amount) {
        List<PartBatch> partBatches = batches.get(autoPart.getId());
        if (partBatches == null) return;

        int remaining = amount;

        for (PartBatch batch : partBatches) {
            if (remaining <= 0) break;

            int reserved = batch.getReserved();
            int toRelease = Math.min(reserved, remaining);

            batch.releaseReservation(toRelease);
            remaining -= toRelease;

            logger.log("[ОСВОБОЖДЕНИЕ] Партия: " + batch.getBatchId() +
                    ", освобождено: " + toRelease);
        }

        logger.log("[ОСВОБОЖДЕНИЕ] Всего освобождено: " + (amount - remaining) +
                " шт. запчасти " + autoPart.getName());
    }

    public void confirmShipment(AutoPart autoPart, int amount) {
        List<PartBatch> partBatches = batches.get(autoPart.getId());
        if (partBatches == null) return;

        int remaining = amount;

        for (PartBatch batch : partBatches) {
            if (remaining <= 0) break;

            int reserved = batch.getReserved();
            int toShip = Math.min(reserved, remaining);

            batch.confirmShipment(toShip);
            remaining -= toShip;

            logger.log("[ОТГРУЗКА] Партия: " + batch.getBatchId() +
                    ", отгружено: " + toShip);
        }

        logger.log("[ОТГРУЗКА] Всего отгружено: " + (amount - remaining) +
                " шт. запчасти " + autoPart.getName());
    }

    public AutoPart findByOem(String oemNumber) {
        if (oemNumber == null) {
            return null;
        }
        return parts.get(oemNumber);
    }

    public List<AutoPart> findByCrossNumber(String crossNumber) {
        if (crossNumber == null) {
            return new ArrayList<>();
        }

        List<AutoPart> result = new ArrayList<>();
        for (AutoPart part : parts.values()) {
            if (part.hasCrossNumber(crossNumber)) {
                result.add(part);
            }
        }
        return result;
    }

    public List<AutoPart> findCompatibleByVin(String vinCode, PartCategory category) {
        if (vinCode == null) {
            return new ArrayList<>();
        }

        return parts.values().stream()
                .filter(part -> part.getCategory() == category)
                .filter(part -> part.isCompatibleWithVin(vinCode))
                .collect(Collectors.toList());
    }

    public int getStockByZone(String zoneId) {
        int total = 0;
        for (List<PartBatch> batchList : batches.values()) {
            for (PartBatch batch : batchList) {
                StorageZone zone = batch.getZone();
                if (zone != null && zone.getId().equals(zoneId)) {
                    total += batch.getQuantity();  // ← quantity, а не getAvailable()!
                }
            }
        }
        return total;
    }

    public List<AutoPart> getLowStockReport(int threshold) {
        // TODO: занятие 5 - товары с остатком < threshold
        return new ArrayList<>();
    }
    
    public List<PartBatch> getExpiredBatches() {
        // TODO: занятие 5 - партии с isExpired() = true
        return new ArrayList<>();
    }
    
    public void updateABCCategories() {
        // TODO: занятие 5 - пересчёт категорий по обороту за месяц
    }
    
    // Геттеры для тестов
    public Map<String, AutoPart> getParts() { return parts; }
    public Map<String, StorageZone> getZones() { return zones; }
    public Map<String, List<PartBatch>> getBatches() { return batches; }
}
