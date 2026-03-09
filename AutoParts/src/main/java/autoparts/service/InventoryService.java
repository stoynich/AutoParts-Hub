package autoparts.service;

import autoparts.logger.Logger;
import autoparts.model.*;
import autoparts.exception.*;

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
    
    // TODO: занятие 2 - приёмка партии с размещением в зону
    public PartBatch receiveBatch(AutoPart autoPart, int quantity, String zoneId, 
                                 String certificateNumber) 
            throws autoparts.exception.CertificateExpiredException {
        // TODO: найти зону по zoneId
        // TODO: проверить вместимость зоны
        // TODO: создать PartBatch, добавить в batches
        // TODO: обновить загрузку зоны
        // TODO: залогировать
        return null;
    }
    
    public void relocateBatch(String batchId, String toZoneId) {
        // TODO: занятие 2 - перемещение партии между зонами
    }
    
    // TODO: занятие 2 - резервирование товара (FEFO - сначала старые)
    public void reserveParts(AutoPart autoPart, int amount) 
            throws InsufficientStockException {
        // TODO: найти подходящие партии (сортировка по receivedDate)
        // TODO: зарезервировать amount штук (можно частично из разных партий)
    }
    
    public void releaseReservation(AutoPart autoPart, int amount) {
        // TODO: занятие 2 - освободить резерв
    }
    
    public void confirmShipment(AutoPart autoPart, int amount) {
        // TODO: занятие 2 - подтвердить отгрузку, списать с остатка
    }
    
    public AutoPart findByOem(String oemNumber) {
        // TODO: занятие 2 - поиск в parts по oemNumber
        return null;
    }
    
    public List<AutoPart> findByCrossNumber(String crossNumber) {
        // TODO: занятие 2 - фильтрация по hasCrossNumber(crossNumber)
        return new ArrayList<>();
    }
    
    public List<AutoPart> findCompatibleByVin(String vinCode, PartCategory category) {
        // TODO: занятие 2 - фильтрация по isCompatibleWithVin(vinCode) и category
        return new ArrayList<>();
    }
    
    public int getStockByZone(String zoneId) {
        // TODO: занятие 2 - суммировать quantity по всем партиям в зоне
        return 0;
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
