package autoparts.model;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    protected String id;
    protected LocalDateTime createdAt;
    
    public BaseEntity(String id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }
    
    // TODO: занятие 1 - добавить геттеры/сеттеры, equals, hashCode
    // TODO: занятие 4 - заменить String даты на LocalDateTime где нужно
    
    
    @Override
    public String toString() {
        return "BaseEntity{id='" + id + "', createdAt=" + createdAt + "}";
    }
}
