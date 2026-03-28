package autoparts.model;

public enum PartCategory {
    ENGINE("Двигатель", ZoneType.BULK_STORAGE),
    TRANSMISSION("Трансмиссия", ZoneType.BULK_STORAGE),
    SUSPENSION("Ходовая часть", ZoneType.BULK_STORAGE),
    BRAKES("Тормозная система", ZoneType.FAST_PICK),
    ELECTRICS("Электрика", ZoneType.FAST_PICK),
    BODY("Кузовные детали", ZoneType.BULK_STORAGE),
    INTERIOR("Салон", ZoneType.BULK_STORAGE),
    CONSUMABLES("Расходники", ZoneType.FAST_PICK);

    private final String description;
    private final ZoneType storageZoneType;

    PartCategory(String description, ZoneType storageZoneType) {
        this.description = description;
        this.storageZoneType = storageZoneType;
    }

    public String getDescription() {
        return description;
    }

    public ZoneType getStorageZoneType() {
        return storageZoneType;
    }
}