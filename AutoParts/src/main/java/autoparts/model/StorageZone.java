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

    public boolean canAccept(int itemsCount, double weightKg) {
        boolean enoughSpace = currentLoad + itemsCount <= capacity;
        boolean enoughWeight = currentWeightKg + weightKg <= maxWeightKg;
        return enoughSpace && enoughWeight;
    }
    
    public boolean isFull() {
        return currentLoad >= capacity;
    }
    
    public int getAvailableSpace() {
        return capacity - currentLoad;
    }

    public boolean isSuitableForPart(AutoPart part) {
        if (part == null || part.getAbcCategory() == null) {
            return false;
        }

        // обычное совпадение категории
        if (part.getAbcCategory() == this.abcCategory) {
            return true;
        }

        // особое правило для BULK_STORAGE
        if (this.zoneType == ZoneType.BULK_STORAGE) {
            return part.getAbcCategory() == ABCCategory.B ||
                    part.getAbcCategory() == ABCCategory.C;
        }

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
        return String.format("StorageZone{id='%s', name='%s', type=%s, abc=%s, load=%d/%d}", id, name, zoneType, abcCategory, currentLoad, capacity);
    }
}
