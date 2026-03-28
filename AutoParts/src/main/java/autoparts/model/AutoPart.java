package autoparts.model;

import java.util.ArrayList;
import java.util.List;

public class AutoPart extends BaseEntity {
    private String name;
    private String oemNumber;
    private PartCategory category;
    private String manufacturer;
    private List<String> crossNumbers;
    private List<String> compatibleVins;
    private double basePrice;
    private double weightKg;
    private ABCCategory abcCategory;
    
    public AutoPart(String id, String name, String oemNumber, PartCategory category, 
                   String manufacturer, double basePrice, double weightKg) {
        super(id);
        this.name = name;
        this.oemNumber = oemNumber;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
        this.weightKg = weightKg;
        this.crossNumbers = new ArrayList<>();
        this.compatibleVins = new ArrayList<>();
        this.abcCategory = ABCCategory.C;
    }

    public void addCrossNumber(String crossNumber) {
        if (crossNumbers != null && !crossNumbers.isEmpty()) {
            crossNumbers.add(crossNumber);
        }
    }
    
    // TODO: занятие 2 - проверить наличие кросс-номера
    public boolean hasCrossNumber(String crossNumber) {
        // TODO: проверить contains в crossNumbers
        return false;
    }
    
    // TODO: занятие 2 - проверить совместимость с VIN
    public boolean isCompatibleWithVin(String vinCode) {
        // TODO: проверить compatibleVins.isEmpty() (универсальная) 
        // TODO: или contains(vinCode)
        return false;
    }

    public void updateABCCategory(ABCCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("ABC категория не может быть null");
        }
        this.abcCategory = category;
    }

    
    public String getName() { return name; }
    public void setName(String name){
        if (name==null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Название запчасти не может быть пустым");
        }
        this.name = name;
    }
    public String getOemNumber() { return oemNumber; }
    public void setOemNumber(String oemNumber){
        if (oemNumber == null || oemNumber.trim().isEmpty()){
            throw new IllegalArgumentException("OEM номер не может быть пустым");
        }
        this.oemNumber = oemNumber;
    }
    public PartCategory getCategory() { return category; }
    public void setCategory(PartCategory category){
        if (category == null){
            throw new IllegalArgumentException("Категория запчастей не может быть пустая");
        }
        this.category = category;
    }
    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer){
        if (manufacturer == null || manufacturer.trim().isEmpty()){
            throw new IllegalArgumentException("Производитель не может быть пустым");
        }
        this.manufacturer = manufacturer;
    }
    public List<String> getCrossNumbers() { return crossNumbers; }
    public List<String> getCompatibleVins() { return compatibleVins; }
    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) {
        if (basePrice < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной:" + basePrice);
        }
        this.basePrice = basePrice;
    }
    public double getWeightKg() { return weightKg; }
    public void setWeightKg(double weightKg) { this.weightKg = weightKg; }
    public ABCCategory getAbcCategory() { return abcCategory; }
    
    @Override
    public String toString() {
        return String.format("%s [%s] %s",name,oemNumber,manufacturer);
    }
}
