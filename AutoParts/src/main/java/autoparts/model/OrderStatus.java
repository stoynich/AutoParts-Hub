package autoparts.model;

public enum OrderStatus {
    REGISTERED,
    CONFIRMED,
    PICKING,
    PACKED,
    SHIPPED,
    DELIVERED,
    CANCELLED;
    
    // TODO: занятие 4 - реализовать логику переходов:
    // REGISTERED -> CONFIRMED -> PICKING -> PACKED -> SHIPPED -> DELIVERED
    // REGISTERED -> CANCELLED (до CONFIRMED)
    // CONFIRMED -> CANCELLED (до PICKING)
    public boolean canTransitionTo(OrderStatus newStatus) {
        // TODO: реализовать проверку допустимости перехода
        return false;
    }
}
