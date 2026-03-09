package autoparts.model;

public class StorageZone extends BaseEntity {
    private String name;
    private ZoneType zoneType;
    private ABCCategory abcCategory;
    private int capacity;
    private int currentLoad;
    private double maxWeightKg;
    private double currentWeightKg;
    
    public StorageZone(String id, String name, ZoneType zoneType, 
                      ABCCategory abcCategory, int capacity, double maxWeightKg) {
        super(id);
        this.name = name;
        this.zoneType = zoneType;
        this.abcCategory = abcCategory;
        this.capacity = capacity;
        this.currentLoad = 0;
        this.maxWeightKg = maxWeightKg;
        this.currentWeightKg = 0.0;
    }
    
    // TODO: занятие 1 - реализовать проверку вместимости
    public boolean canAccept(int itemsCount, double weightKg) {
        // TODO: проверить currentLoad + itemsCount <= capacity 
        // TODO: AND currentWeightKg + weightKg <= maxWeightKg
        return false;
    }
    
    public boolean isFull() {
        // TODO: занятие 1 - проверить currentLoad >= capacity
        return false;
    }
    
    public int getAvailableSpace() {
        // TODO: занятие 1 - return capacity - currentLoad
        return 0;
    }
    
    // TODO: занятие 5 - проверить соответствие ABC-категории
    public boolean isSuitableForPart(AutoPart part) {
        // TODO: проверить part.getAbcCategory() == this.abcCategory 
        // TODO: или зона BULK_STORAGE принимает B и C
        return false;
    }
    
    // TODO: занятие 2 - реализовать добавление/удаление загрузки
    public void addLoad(int items, double weight) {
        // TODO: увеличить currentLoad и currentWeightKg
    }
    
    public void removeLoad(int items, double weight) {
        // TODO: уменьшить currentLoad и currentWeightKg
    }
    
    // Геттеры/сеттеры...
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ZoneType getZoneType() { return zoneType; }
    public void setZoneType(ZoneType zoneType) { this.zoneType = zoneType; }
    public ABCCategory getAbcCategory() { return abcCategory; }
    public void setAbcCategory(ABCCategory abcCategory) { this.abcCategory = abcCategory; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public int getCurrentLoad() { return currentLoad; }
    public void setCurrentLoad(int currentLoad) { this.currentLoad = currentLoad; }
    public double getMaxWeightKg() { return maxWeightKg; }
    public void setMaxWeightKg(double maxWeightKg) { this.maxWeightKg = maxWeightKg; }
    public double getCurrentWeightKg() { return currentWeightKg; }
    public void setCurrentWeightKg(double currentWeightKg) { this.currentWeightKg = currentWeightKg; }
    
    @Override
    public String toString() {
        // TODO: занятие 1 - улучшить формат
        return "StorageZone[" + id + "] " + name;
    }
}
