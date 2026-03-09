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
        this.status = OrderStatus.REGISTERED;
        this.items = new ArrayList<>();
        this.totalWeight = 0.0;
    }

    public void addItem(AutoPart autoPart, int quantity, double priceAtMoment) {
        if (!autoPart.isCompatibleWithVin(vinCode)){
            throw new IllegalArgumentException("Запчасть" + autoPart.getName() + "несовместима с VIN" + vinCode);
        }
        // Создаем позицию заказа
        OrderLine line = new OrderLine (autoPart, quantity,priceAtMoment, autoPart.getOemNumber());
        // Добавляем в список
        items.add(line);
        // Пересчитываем общий вес
        totalWeight += autoPart.getWeightKg()*quantity;
    }
    
    public double getTotalAmount() {
        double total = 0.0;
        for (OrderLine line : items) {
            total += line.getLineTotal();
        }
        return total;
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
        return priority == Priority.URGENT;
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
        return String.format("CustomerOrder{externalId='%s', clientId='%s', status=%s, priority=%s, items=%d, total=%.2f}",
                externalOrderId, clientId, status, priority, items.size(), getTotalAmount());
    }
}
