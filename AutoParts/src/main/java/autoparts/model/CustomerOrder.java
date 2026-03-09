package autoparts.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrder extends BaseEntity {
    private String externalOrderId;
    private String clientId;
    private OrderStatus status;
    private Priority priority;
    private LocalDateTime confirmedAt;
    private List<OrderLine> items;
    private String vinCode;
    private double totalWeight;
    private String trackingNumber;
    
    public CustomerOrder(String orderId, String externalOrderId, String clientId, 
                        String vinCode, Priority priority) {
        super(orderId);
        this.externalOrderId = externalOrderId;
        this.clientId = clientId;
        this.vinCode = vinCode;
        this.priority = priority;
        // TODO: занятие 1 - статус REGISTERED
        this.items = new ArrayList<>();
        this.totalWeight = 0.0;
    }
    
    // TODO: занятие 1 - добавить позицию с проверкой VIN-совместимости
    public void addItem(AutoPart autoPart, int quantity, double priceAtMoment) {
        // TODO: проверить isCompatibleWithVin(vinCode)
        // TODO: создать OrderLine и добавить в items
        // TODO: пересчитать totalWeight
    }
    
    public double getTotalAmount() {
        // TODO: занятие 1 - суммировать getLineTotal() по всем items
        return 0.0;
    }
    
    public boolean canChangeStatus(OrderStatus newStatus) {
        // TODO: занятие 4 - делегировать в status.canTransitionTo(newStatus)
        return false;
    }
    
    public void changeStatus(OrderStatus newStatus) {
        // TODO: занятие 4 - проверить canChangeStatus, обновить статус
        // TODO: если CONFIRMED, установить confirmedAt = LocalDateTime.now()
        // TODO: занятие 6 - добавить в историю с timestamp
    }
    
    public boolean isUrgent() {
        // TODO: занятие 1 - проверить priority == Priority.URGENT
        return false;
    }
    
    public boolean isOverdueForPicking() {
        // TODO: занятие 6 - для URGENT проверить 
        // TODO: Duration.between(confirmedAt, LocalDateTime.now()).toMinutes() > 30
        return false;
    }
    
    // TODO: занятие 6 - добавить поле для истории статусов: List<String> statusHistory
    
    // Геттеры/сеттеры...
    public String getExternalOrderId() { return externalOrderId; }
    public void setExternalOrderId(String externalOrderId) { this.externalOrderId = externalOrderId; }
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public LocalDateTime getConfirmedAt() { return confirmedAt; }
    public void setConfirmedAt(LocalDateTime confirmedAt) { this.confirmedAt = confirmedAt; }
    public List<OrderLine> getItems() { return items; }
    public String getVinCode() { return vinCode; }
    public void setVinCode(String vinCode) { this.vinCode = vinCode; }
    public double getTotalWeight() { return totalWeight; }
    public void setTotalWeight(double totalWeight) { this.totalWeight = totalWeight; }
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    
    @Override
    public String toString() {
        // TODO: занятие 1 - улучшить формат
        return "CustomerOrder[" + id + "]";
    }
}
