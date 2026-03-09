package autoparts.service;

import autoparts.model.*;
import autoparts.validation.StockValidator;

import java.util.*;

public class PickingService {
    private final Map<String, PickList> pickLists;
    private final InventoryService inventoryService;
    private final StockValidator stockValidator;
    
    public PickingService(InventoryService inventoryService, 
                         StockValidator stockValidator) {
        this.pickLists = new HashMap<>();
        this.inventoryService = inventoryService;
        this.stockValidator = stockValidator;
    }
    
    // TODO: занятие 6 - создание задания на комплектацию с маршрутом
    public PickList createPickList(String orderId) {
        // TODO: получить заказ, для каждой позиции найти партию (FEFO)
        // TODO: создать PickList с PickLine
        return null;
    }
    
    // TODO: занятие 6 - оптимальный маршрут по стеллажам (A-категория ближе)
    public List<String> optimizePickingRoute(String zoneId) {
        // TODO: вернуть порядок обхода зон (FAST_PICK -> BULK_STORAGE)
        return new ArrayList<>();
    }
    
    // TODO: занятие 6 - групповая комплектация
    public List<PickList> wavePicking(List<String> orderIds) {
        // TODO: создать PickList для каждого заказа с группировкой по зонам
        return new ArrayList<>();
    }
    
    // TODO: занятие 6 - замена на аналог с фиксацией в заказе
    public void substitutePart(String orderId, String requestedOem, String substituteOem) {
        // TODO: найти заказ, заменить в OrderLine requestedOem -> substituteOem
        // TODO: обновить providedOem
    }
    
    public void confirmPicking(String pickListId, String pickerName) {
        // TODO: занятие 6 - установить pickerName, отметить выполненным
    }
}
