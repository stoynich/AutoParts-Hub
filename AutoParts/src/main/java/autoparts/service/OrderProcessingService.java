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
    
    public OrderProcessingService(InventoryService inventoryService, 
                                 OrderValidator orderValidator, 
                                 Logger logger) {
        this.orders = new HashMap<>();
        this.inventoryService = inventoryService;
        this.orderValidator = orderValidator;
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
