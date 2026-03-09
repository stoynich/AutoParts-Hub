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
    }
    
    public void validateVinCompatibility(CustomerOrder order, AutoPart part) 
            throws autoparts.exception.IncompatiblePartException {
        // TODO: занятие 4 - проверить part.isCompatibleWithVin(order.getVinCode())
    }
    
    public void validateUrgentPriority(CustomerOrder order) {
        // TODO: занятие 6 - срочный заказ не более 50 позиций
    }
}
