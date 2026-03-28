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

    public PickList createPickList(String orderId) {
        PickList pickList = new PickList(UUID.randomUUID().toString(), orderId);
        pickLists.put(pickList.getPickListId(), pickList);
        return pickList;
    }

    public List<String> optimizePickingRoute(String zoneId) {
        List<String> route = new ArrayList<>();
        route.add(ZoneType.FAST_PICK.name());
        route.add(ZoneType.BULK_STORAGE.name());
        return route;
    }

    public List<PickList> wavePicking(List<String> orderIds) {
        List<PickList> result = new ArrayList<>();

        for (String orderId : orderIds) {
            PickList pickList = createPickList(orderId);
            result.add(pickList);
        }

        return result;
    }

    public void substitutePart(String orderId, String requestedOem, String substituteOem) {
        throw new UnsupportedOperationException("Замена аналога пока не реализована: PickingService не имеет доступа к заказам");
    }
    
    public void confirmPicking(String pickListId, String pickerName) {
        PickList pickList = pickLists.get(pickListId);

        if (pickList == null) {
            throw new IllegalArgumentException("PickList не найден: " + pickListId);
        }

        pickList.complete(pickerName);
    }
}
