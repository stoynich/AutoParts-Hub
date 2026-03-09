package autoparts.model;

public class PickLine {
    private AutoPart autoPart;
    private int quantity;
    private String zoneId;
    private String batchId;
    private boolean isPicked;
    
    public PickLine(AutoPart autoPart, int quantity, String zoneId, String batchId) {
        this.autoPart = autoPart;
        this.quantity = quantity;
        this.zoneId = zoneId;
        this.batchId = batchId;
        this.isPicked = false;
    }
    
    // Геттеры...
    public AutoPart getAutoPart() { return autoPart; }
    public int getQuantity() { return quantity; }
    public String getZoneId() { return zoneId; }
    public String getBatchId() { return batchId; }
    public boolean isPicked() { return isPicked; }
    public void setPicked(boolean picked) { isPicked = picked; }
    
    @Override
    public String toString() {
        return String.format("PickLine{part='%s', quantity=%d, zone='%s', batch='%s', picked=%s}", autoPart.getName(), quantity, zoneId, batchId, isPicked ? "да" : "нет");
    }
}
