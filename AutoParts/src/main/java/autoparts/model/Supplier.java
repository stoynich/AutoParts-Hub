package autoparts.model;

public class Supplier extends BaseEntity {
    private String companyName;
    private String inn;
    private String contactPhone;
    private String contactEmail;
    private PartCategory[] supplyCategories;
    private int deliveryDays;
    private double minOrderAmount;
    private int rating;
    private boolean isActive;
    
    public Supplier(String id, String companyName, String inn, String contactPhone, 
                   String contactEmail, PartCategory[] supplyCategories) {
        super(id);
        this.companyName = companyName;
        this.inn = inn;
        this.contactPhone = contactPhone;
        // TODO: занятие 1 - добавить валидацию email (contains "@")
        this.contactEmail = contactEmail;
        this.supplyCategories = supplyCategories;
        this.deliveryDays = 7;
        this.minOrderAmount = 0.0;
        this.rating = 3;
        this.isActive = true;
    }
    
    // TODO: занятие 2 - проверить может ли поставлять категорию
    public boolean canSupplyCategory(PartCategory category) {
        // TODO: проверить наличие category в supplyCategories
        return false;
    }
    
    // Геттеры/сеттеры...
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getInn() { return inn; }
    public void setInn(String inn) { this.inn = inn; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public PartCategory[] getSupplyCategories() { return supplyCategories; }
    public void setSupplyCategories(PartCategory[] supplyCategories) { 
        this.supplyCategories = supplyCategories; 
    }
    public int getDeliveryDays() { return deliveryDays; }
    public void setDeliveryDays(int deliveryDays) { this.deliveryDays = deliveryDays; }
    public double getMinOrderAmount() { return minOrderAmount; }
    public void setMinOrderAmount(double minOrderAmount) { this.minOrderAmount = minOrderAmount; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    
    @Override
    public String toString() {
        // TODO: занятие 1 - улучшить формат
        return "Supplier[" + id + "] " + companyName;
    }
}
