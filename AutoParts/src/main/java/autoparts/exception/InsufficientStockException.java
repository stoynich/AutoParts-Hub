package autoparts.exception;

public class InsufficientStockException extends Exception {
    private final String partId;
    private final int requested;
    private final int available;
    
    public InsufficientStockException(String message, String partId, 
                                     int requested, int available) {
        super(message);
        this.partId = partId;
        this.requested = requested;
        this.available = available;
    }
    
    public String getPartId() { return partId; }
    public int getRequested() { return requested; }
    public int getAvailable() { return available; }
}
