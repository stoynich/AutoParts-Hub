package autoparts.validation;

import autoparts.exception.InvalidOrderStatusException;
import autoparts.model.CustomerOrder;
import autoparts.model.AutoPart;
import autoparts.model.OrderStatus;

public class OrderValidator {

    public void validateOrderCreation(String externalOrderId) {
        if (externalOrderId == null || externalOrderId.isBlank()) {
            throw new IllegalArgumentException("externalOrderId не может быть пустым");
        }
    }
    
    public void validateStatusTransition(CustomerOrder order, OrderStatus newStatus) 
            throws InvalidOrderStatusException {
        if (!order.canChangeStatus(newStatus)) {
            throw new InvalidOrderStatusException(
                    "Недопустимый переход статуса",
                    order.getStatus(),
                    newStatus
            );
        }
    }
    
    public void validateCancellation(CustomerOrder order) 
            throws InvalidOrderStatusException {
        if (order.getStatus() != OrderStatus.REGISTERED &&
                order.getStatus() != OrderStatus.CONFIRMED) {
            throw new InvalidOrderStatusException(
                    "Отмена невозможна",
                    order.getStatus(),
                    OrderStatus.CANCELLED
            );
        }
    }

    public void validateVinCompatibility(CustomerOrder order, AutoPart part)
            throws autoparts.exception.IncompatiblePartException {
        if (!part.isCompatibleWithVin(order.getVinCode())) {
            throw new autoparts.exception.IncompatiblePartException(
                    "Запчасть несовместима с VIN",
                    order.getVinCode(),
                    part.getOemNumber(),
                    "Проверка совместимости не пройдена"
            );
        }
    }
    
    public void validateUrgentPriority(CustomerOrder order) {
        // TODO: занятие 6 - срочный заказ не более 50 позиций
    }
}
