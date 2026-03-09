package autoparts.model;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    protected String id;
    protected LocalDateTime createdAt;
    
    public BaseEntity(String id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }
    public void setId(String id){
        if (id==null || id.trim().isEmpty()){
            throw new IllegalArgumentException("ID не может быть пустым");
        }
        this.id = id;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        if  (createdAt==null){
            throw new IllegalArgumentException("Дата создания не может быть пустой");
        }
        this.createdAt = createdAt;
    }
    // TODO: занятие 4 - заменить String даты на LocalDateTime где нужно
    
    
    @Override
    public String toString() {
        return "BaseEntity{id='" + id + "', createdAt=" + createdAt + "}";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return java.util.Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }
}
