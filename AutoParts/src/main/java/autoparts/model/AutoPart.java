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
        // TODO: занятие 1 - по умолчанию abcCategory = ABCCategory.C
    }
    
    // TODO: занятие 1 - реализовать добавление кросс-номера
    public void addCrossNumber(String crossNumber) {
        // TODO: добавить в crossNumbers если не пустой
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
    
    // TODO: занятие 5 - обновить ABC-категорию
    public void updateABCCategory(ABCCategory category) {
        // TODO: обновить abcCategory
    }
    
    // TODO: занятие 1 - добавить геттеры/сеттеры с валидацией
    // TODO: занятие 1 - улучшить toString() через String.format()
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getOemNumber() { return oemNumber; }
    public void setOemNumber(String oemNumber) { this.oemNumber = oemNumber; }
    public PartCategory getCategory() { return category; }
    public void setCategory(PartCategory category) { this.category = category; }
    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public List<String> getCrossNumbers() { return crossNumbers; }
    public List<String> getCompatibleVins() { return compatibleVins; }
    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { 
        // TODO: занятие 1 - добавить проверку if (basePrice < 0) throw new IllegalArgumentException
        this.basePrice = basePrice; 
    }
    public double getWeightKg() { return weightKg; }
    public void setWeightKg(double weightKg) { this.weightKg = weightKg; }
    public ABCCategory getAbcCategory() { return abcCategory; }
    
    @Override
    public String toString() {
        // TODO: занятие 1 - сделать читаемый формат: "Тормозные колодки [OEM-12345] Bosch"
        return "AutoPart[" + id + "] " + name;
    }
}
