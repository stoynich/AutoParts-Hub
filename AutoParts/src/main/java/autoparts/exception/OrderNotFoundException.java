package autoparts.exception;

public class OrderNotFoundException extends Exception {
    private final String orderId;
    
    public OrderNotFoundException(String orderId) {
        super("Заказ не найден: " + orderId);
        this.orderId = orderId;
    }
    
    public String getOrderId() { return orderId; }
}
