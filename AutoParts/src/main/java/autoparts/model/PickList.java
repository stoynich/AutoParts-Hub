package autoparts.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PickList {
    private String pickListId;
    private String orderId;
    private List<PickLine> lines;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private String pickerName;
    private boolean isCompleted;
    
    public PickList(String pickListId, String orderId) {
        this.pickListId = pickListId;
        this.orderId = orderId;
        this.lines = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.isCompleted = false;
    }
    
    public void addLine(AutoPart part, int quantity, String zoneId, String batchId) {
        // TODO: занятие 6 - создать PickLine и добавить в lines
    }
    
    public void complete(String pickerName) {
        // TODO: занятие 6 - установить completedAt, pickerName, isCompleted = true
    }
    
    // Геттеры...
    public String getPickListId() { return pickListId; }
    public String getOrderId() { return orderId; }
    public List<PickLine> getLines() { return lines; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public String getPickerName() { return pickerName; }
    public boolean isCompleted() { return isCompleted; }
    
    @Override
    public String toString() {
        return String.format("PickList{id='%s', order='%s', items=%d, completed=%s}", pickListId, orderId, lines.size(), isCompleted ? "да" : "нет");
    }
}
