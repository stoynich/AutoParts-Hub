package autoparts.service;

import autoparts.exception.*;
import autoparts.logger.Logger;
import autoparts.model.*;
import autoparts.validation.OrderValidator;
import autoparts.validation.VinValidator;

import java.util.*;

public class OrderProcessingService {
    private final Map<String, CustomerOrder> orders;
    private final InventoryService inventoryService;
    private final OrderValidator orderValidator;
    private final Logger logger;
    private final VinValidator vinValidator;

    public OrderProcessingService(InventoryService inventoryService,
                                  OrderValidator orderValidator,
                                  VinValidator vinValidator,
                                  Logger logger) {
        this.orders = new HashMap<>();
        this.inventoryService = inventoryService;
        this.orderValidator = orderValidator;
        this.vinValidator = vinValidator;
        this.logger = logger;
    }

    public CustomerOrder createOrder(String externalOrderId, String clientId,
                                     String vinCode, Priority priority)
            throws InvalidVinException {
        orderValidator.validateOrderCreation(externalOrderId);
        vinValidator.validateVinFormat(vinCode);
        vinValidator.validateVinExists(vinCode);

        String orderId = UUID.randomUUID().toString();
        CustomerOrder order = new CustomerOrder(orderId, externalOrderId, clientId, vinCode, priority);

        orders.put(orderId, order);
        logger.log("[ORDER] Создан заказ: " + externalOrderId + ", id=" + orderId);

        return order;
    }

    public void addItemToOrder(String orderId, String oemNumber, int quantity)
            throws OrderNotFoundException, PartNotFoundException,
            IncompatiblePartException {
        CustomerOrder order = orders.get(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        AutoPart part = inventoryService.findByOem(oemNumber);

        if (part == null) {
            List<AutoPart> analogs = inventoryService.findByCrossNumber(oemNumber);
            if (analogs.isEmpty()) {
                throw new PartNotFoundException(oemNumber);
            }
            part = analogs.get(0);
        }

        orderValidator.validateVinCompatibility(order, part);
        order.addItem(part, quantity, part.getBasePrice());

        logger.log("[ORDER] В заказ " + orderId + " добавлена позиция: " + part.getName() + ", qty=" + quantity);
    }

    public void confirmOrder(String orderId) throws OrderNotFoundException {
        CustomerOrder order = orders.get(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        order.changeStatus(OrderStatus.CONFIRMED);
        logger.log("[ORDER] Заказ подтвержден: " + orderId);
    }

    public void reserveForOrder(String orderId)
            throws OrderNotFoundException, InsufficientStockException {
        CustomerOrder order = orders.get(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        for (OrderLine line : order.getItems()) {
            inventoryService.reserveParts(line.getAutoPart(), line.getQuantity());
        }

        logger.log("[ORDER] Товары зарезервированы для заказа: " + orderId);
    }
    
    // TODO: занятие 6 - создание задания на комплектацию
    public PickList startPicking(String orderId) 
            throws OrderNotFoundException {
        // TODO: проверить статус CONFIRMED
        // TODO: создать PickList через PickingService
        // TODO: статус -> PICKING
        return null;
    }
    
    public void packOrder(String orderId, double actualWeight) 
            throws OrderNotFoundException {
        // TODO: занятие 6 - статус -> PACKED, проверка веса
    }
    
    public void shipOrder(String orderId, String trackingNumber) 
            throws OrderNotFoundException {
        // TODO: занятие 6 - статус -> SHIPPED, вызвать confirmShipment
    }
    
    public void deliverOrder(String orderId) throws OrderNotFoundException {
        // TODO: занятие 6 - статус -> DELIVERED
    }
    
    public void cancelOrder(String orderId) 
            throws OrderNotFoundException, InvalidOrderStatusException {
        // TODO: занятие 6 - только до PICKING, освободить резерв
    }
    
    public CustomerOrder getOrderById(String orderId) {
        // TODO: занятие 2 - поиск в orders
        return orders.get(orderId);
    }

    public List<CustomerOrder> getOrdersByStatus(OrderStatus status) {
        List<CustomerOrder> result = new ArrayList<>();

        for (CustomerOrder order : orders.values()) {
            if (order.getStatus() == status) {
                result.add(order);
            }
        }

        return result;
    }

    public List<CustomerOrder> getUrgentOverdue() {
        List<CustomerOrder> result = new ArrayList<>();

        for (CustomerOrder order : orders.values()) {
            if (order.isUrgent() && order.isOverdueForPicking()) {
                result.add(order);
            }
        }

        return result;
    }
}
