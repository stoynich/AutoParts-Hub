package autoparts.exception;

import autoparts.model.OrderStatus;

public class InvalidOrderStatusException extends Exception {
    private final OrderStatus from;
    private final OrderStatus to;
    
    public InvalidOrderStatusException(String message, OrderStatus from, OrderStatus to) {
        super(message);
        this.from = from;
        this.to = to;
    }
    
    public OrderStatus getFrom() { return from; }
    public OrderStatus getTo() { return to; }
}
