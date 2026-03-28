package autoparts.service;

import autoparts.exception.*;
import autoparts.logger.Logger;
import autoparts.model.*;
import autoparts.validation.OrderValidator;

import java.util.*;

public class OrderProcessingService {
    private final Map<String, CustomerOrder> orders;
    private final InventoryService inventoryService;
    private final OrderValidator orderValidator;
    private final Logger logger;
    private final PickingService pickingService;

    public OrderProcessingService(InventoryService inventoryService,
                                  OrderValidator orderValidator,
                                  PickingService pickingService,
                                  Logger logger) {
        this.orders = new HashMap<>();
        this.inventoryService = inventoryService;
        this.orderValidator = orderValidator;
        this.pickingService = pickingService;
        this.logger = logger;
    }
    
    // TODO: занятие 5 - создание заказа
    public CustomerOrder createOrder(String externalOrderId, String clientId, 
                                    String vinCode, Priority priority) 
            throws InvalidVinException {
        // TODO: валидация VIN через VinValidator
        // TODO: создать CustomerOrder, сохранить, залогировать
        return null;
    }
    
    // TODO: занятие 5 - добавление позиции с поиском по OEM или кросс-номеру
    public void addItemToOrder(String orderId, String oemNumber, int quantity) 
            throws OrderNotFoundException, PartNotFoundException, 
                   IncompatiblePartException {
        // TODO: найти заказ, найти запчасть (findByOem или findByCrossNumber)
        // TODO: проверить VIN-совместимость
        // TODO: добавить позицию с ценой на момент заказа
    }
    
    public void confirmOrder(String orderId) throws OrderNotFoundException {
        // TODO: занятие 5 - проверить доступность, сменить статус на CONFIRMED
    }
    
    public void reserveForOrder(String orderId) 
            throws OrderNotFoundException, InsufficientStockException {
        // TODO: занятие 5 - зарезервировать товары под заказ
    }

    public PickList startPicking(String orderId)
            throws OrderNotFoundException {
        CustomerOrder order = orders.get(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Комплектацию можно начать только для CONFIRMED заказа");
        }

        PickList pickList = pickingService.createPickList(orderId);
        order.changeStatus(OrderStatus.PICKING);

        logger.log("[PICKING] Создано задание на комплектацию для заказа: " + orderId);
        return pickList;
    }

    public void packOrder(String orderId, double actualWeight)
            throws OrderNotFoundException {
        CustomerOrder order = orders.get(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        if (actualWeight <= 0) {
            throw new IllegalArgumentException("Вес упаковки должен быть больше 0");
        }

        order.setTotalWeight(actualWeight);
        order.changeStatus(OrderStatus.PACKED);

        logger.log("[PACK] Заказ упакован: " + orderId + ", вес=" + actualWeight);
    }

    public void shipOrder(String orderId, String trackingNumber)
            throws OrderNotFoundException {
        CustomerOrder order = orders.get(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        if (trackingNumber == null || trackingNumber.isBlank()) {
            throw new IllegalArgumentException("Трек-номер не может быть пустым");
        }

        order.setTrackingNumber(trackingNumber);

        for (OrderLine line : order.getItems()) {
            inventoryService.confirmShipment(line.getAutoPart(), line.getQuantity());
        }

        order.changeStatus(OrderStatus.SHIPPED);

        logger.log("[SHIP] Заказ отгружен: " + orderId + ", tracking=" + trackingNumber);
    }

    public void deliverOrder(String orderId) throws OrderNotFoundException {
        CustomerOrder order = orders.get(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        order.changeStatus(OrderStatus.DELIVERED);
        logger.log("[DELIVERY] Заказ доставлен: " + orderId);
    }

    public void cancelOrder(String orderId)
            throws OrderNotFoundException, InvalidOrderStatusException {
        CustomerOrder order = orders.get(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        orderValidator.validateCancellation(order);

        for (OrderLine line : order.getItems()) {
            inventoryService.releaseReservation(line.getAutoPart(), line.getQuantity());
        }

        order.changeStatus(OrderStatus.CANCELLED);
        logger.log("[CANCEL] Заказ отменен: " + orderId);
    }
    
    public CustomerOrder getOrderById(String orderId) {
        // TODO: занятие 2 - поиск в orders
        return orders.get(orderId);
    }
    
    public List<CustomerOrder> getOrdersByStatus(OrderStatus status) {
        // TODO: занятие 5 - фильтрация по статусу
        return new ArrayList<>();
    }
    
    public List<CustomerOrder> getUrgentOverdue() {
        // TODO: занятие 5 - срочные заказы с isOverdueForPicking() = true
        return new ArrayList<>();
    }
}
