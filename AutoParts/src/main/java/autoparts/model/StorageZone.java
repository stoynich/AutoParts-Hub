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
    
    // TODO: занятие 5 - проверить соответствие ABC-категории
    public boolean isSuitableForPart(AutoPart part) {
        // TODO: проверить part.getAbcCategory() == this.abcCategory 
        // TODO: или зона BULK_STORAGE принимает B и C
        return false;
    }

    public void addLoad(int items, double weight) {
        if (items <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Количество и вес должны быть положительными");
        }
        if (!canAccept(items, weight)) {
            throw new IllegalArgumentException("Превышение вместимости или грузоподъёмности зоны");
        }
        this.currentLoad += items;
        this.currentWeightKg += weight;
    }
    
    public void removeLoad(int items, double weight) {
        if (items <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Количество и вес должны быть положительными");
        }
        if (items > currentLoad) {
            throw new IllegalArgumentException("Нельзя удалить больше чем есть в зоне");
        }
        if (weight > currentWeightKg) {
            throw new IllegalArgumentException("Нельзя удалить больше веса чем есть в зоне");
        }
        this.currentLoad -= items;
        this.currentWeightKg -= weight;
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
