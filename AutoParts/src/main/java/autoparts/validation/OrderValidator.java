package autoparts.validation;

import autoparts.exception.InvalidOrderStatusException;
import autoparts.model.CustomerOrder;
import autoparts.model.AutoPart;
import autoparts.model.OrderStatus;

public class OrderValidator {
    
    public void validateOrderCreation(String externalOrderId) {
        // TODO: занятие 4 - проверить уникальность externalOrderId
    }
    
    public void validateStatusTransition(CustomerOrder order, OrderStatus newStatus) 
            throws InvalidOrderStatusException {
        // TODO: занятие 4 - проверить order.canChangeStatus(newStatus)
        // TODO: бросить InvalidOrderStatusException если нельзя
    }
    
    public void validateCancellation(CustomerOrder order) 
            throws InvalidOrderStatusException {
        // TODO: занятие 4 - отмена только до PICKING
        if (order.getStatus() == OrderStatus.PICKING ||
                order.getStatus() == OrderStatus.PACKED ||
                order.getStatus() == OrderStatus.SHIPPED ||
                order.getStatus() == OrderStatus.DELIVERED) {
            throw new InvalidOrderStatusException(
                    "Отмена возможна только до начала комплектации",
                    order.getStatus(),
                    OrderStatus.CANCELLED
            );
        }
    }
    
    public void validateVinCompatibility(CustomerOrder order, AutoPart part) 
            throws autoparts.exception.IncompatiblePartException {
        // TODO: занятие 4 - проверить part.isCompatibleWithVin(order.getVinCode())
    }
    
    public void validateUrgentPriority(CustomerOrder order) {
        if (order.isUrgent() && order.getItems().size() > 50) {
            throw new IllegalArgumentException("Срочный заказ не может содержать более 50 позиций");
        }
    }
}
