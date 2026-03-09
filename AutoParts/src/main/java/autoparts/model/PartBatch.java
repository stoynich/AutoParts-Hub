package autoparts.model;

import java.time.LocalDate;

public class PartBatch {
    private String batchId;
    private AutoPart autoPart;
    private int quantity;
    private int reserved;
    private LocalDate receivedDate;
    private LocalDate expiryDate;
    private String certificateNumber;
    private LocalDate certificateExpiry;
    private String countryOfOrigin;
    private StorageZone zone;
    private boolean isBlocked;
    
    public PartBatch(String batchId, AutoPart autoPart, int quantity, 
                    LocalDate expiryDate, String certificateNumber, StorageZone zone) {
        this.batchId = batchId;
        this.autoPart = autoPart;
        this.quantity = quantity;
        this.reserved = 0;
        this.receivedDate = LocalDate.now();
        this.expiryDate = expiryDate;
        this.certificateNumber = certificateNumber;
        // TODO: занятие 4 - certificateExpiry = receivedDate + 1 год (если есть сертификат)
        this.certificateExpiry = null;
        this.countryOfOrigin = "";
        this.zone = zone;
        this.isBlocked = false;
    }
    
    public int getAvailable() {
        // TODO: занятие 2 - return quantity - reserved
        return 0;
    }
    
    // TODO: занятие 2 - реализовать резервирование с проверкой
    public void reserve(int amount) throws autoparts.exception.InsufficientStockException {
        // TODO: проверить getAvailable() >= amount
        // TODO: иначе бросить InsufficientStockException
        // TODO: увеличить reserved
    }
    
    public void releaseReservation(int amount) {
        // TODO: занятие 2 - уменьшить reserved
    }
    
    public void confirmShipment(int amount) {
        // TODO: занятие 2 - уменьшить quantity и reserved
    }
    
    public boolean isExpired() {
        // TODO: занятие 4 - проверить expiryDate != null && LocalDate.now().isAfter(expiryDate)
        return false;
    }
    
    public boolean isCertificateValid() {
        // TODO: занятие 4 - проверить certificateExpiry != null 
        // TODO: && LocalDate.now().isBefore(certificateExpiry)
        return false;
    }
    
    public void block(String reason) {
        // TODO: занятие 4 - установить isBlocked = true и залогировать
    }
    
    // Геттеры/сеттеры...
    public String getBatchId() { return batchId; }
    public AutoPart getAutoPart() { return autoPart; }
    public void setAutoPart(AutoPart autoPart) { this.autoPart = autoPart; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getReserved() { return reserved; }
    public void setReserved(int reserved) { this.reserved = reserved; }
    public LocalDate getReceivedDate() { return receivedDate; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public String getCertificateNumber() { return certificateNumber; }
    public void setCertificateNumber(String certificateNumber) { this.certificateNumber = certificateNumber; }
    public LocalDate getCertificateExpiry() { return certificateExpiry; }
    public void setCertificateExpiry(LocalDate certificateExpiry) { this.certificateExpiry = certificateExpiry; }
    public String getCountryOfOrigin() { return countryOfOrigin; }
    public void setCountryOfOrigin(String countryOfOrigin) { this.countryOfOrigin = countryOfOrigin; }
    public StorageZone getZone() { return zone; }
    public void setZone(StorageZone zone) { this.zone = zone; }
    public boolean isBlocked() { return isBlocked; }
    public void setBlocked(boolean blocked) { isBlocked = blocked; }
    
    @Override
    public String toString() {
        return String.format("PartBatch{id='%s', part='%s', available=%d/%d, expiry=%s}", batchId, autoPart.getName(), getAvailable(), quantity, expiryDate != null ? expiryDate : "none");
    }
}
