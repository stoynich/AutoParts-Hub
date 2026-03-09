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
        this.providedOem = autoPart.getOemNumber();
    }
    
    public double getLineTotal() {
        return quantity * priceAtMoment;
    }
    
    public boolean isSubstituted() {
        return !requestedOem.equals(providedOem);
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
        return String.format("%s x%d = %.2f руб.", autoPart.getName(), quantity, getLineTotal());
    }
}
