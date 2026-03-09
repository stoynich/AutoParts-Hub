package autoparts.exception;

public class UrgentOrderOverdueException extends Exception {
    private final String orderId;
    private final long minutesOverdue;
    
    public UrgentOrderOverdueException(String orderId, long minutesOverdue) {
        super("Срочный заказ " + orderId + " просрочен на " + minutesOverdue + " минут");
        this.orderId = orderId;
        this.minutesOverdue = minutesOverdue;
    }
    
    public String getOrderId() { return orderId; }
    public long getMinutesOverdue() { return minutesOverdue; }
}
