package autoparts.model;

public class OrderLine {
    private AutoPart autoPart;
    private int quantity;
    private double priceAtMoment;
    private String requestedOem;
    private String providedOem;
    
    public OrderLine(AutoPart autoPart, int quantity, double priceAtMoment, String requestedOem) {
        this.autoPart = autoPart;
        this.quantity = quantity;
        this.priceAtMoment = priceAtMoment;
        this.requestedOem = requestedOem;
        // TODO: занятие 1 - providedOem = autoPart.getOemNumber() (изначально тот же)
    }
    
    public double getLineTotal() {
        // TODO: занятие 1 - return quantity * priceAtMoment
        return 0.0;
    }
    
    public boolean isSubstituted() {
        // TODO: занятие 1 - проверить !requestedOem.equals(providedOem)
        return false;
    }
    
    // Геттеры...
    public AutoPart getAutoPart() { return autoPart; }
    public int getQuantity() { return quantity; }
    public double getPriceAtMoment() { return priceAtMoment; }
    public String getRequestedOem() { return requestedOem; }
    public String getProvidedOem() { return providedOem; }
    public void setProvidedOem(String providedOem) { this.providedOem = providedOem; }
    
    @Override
    public String toString() {
        // TODO: занятие 1 - улучшить формат
        return "OrderLine: " + autoPart.getName();
    }
}
