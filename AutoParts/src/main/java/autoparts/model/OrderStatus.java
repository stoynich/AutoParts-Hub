package autoparts.model;

public enum OrderStatus {
    REGISTERED,
    CONFIRMED,
    PICKING,
    PACKED,
    SHIPPED,
    DELIVERED,
    CANCELLED;

    public boolean canTransitionTo(OrderStatus newStatus) {
        switch (this) {
            case REGISTERED:
                return newStatus == CONFIRMED || newStatus == CANCELLED;
            case CONFIRMED:
                return newStatus == PICKING || newStatus == CANCELLED;
            case PICKING:
                return newStatus == PACKED;
            case PACKED:
                return newStatus == SHIPPED;
            case SHIPPED:
                return newStatus == DELIVERED;
            default:
                return false;
        }
    }
}
