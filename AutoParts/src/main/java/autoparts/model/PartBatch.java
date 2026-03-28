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
        this.certificateExpiry = (certificateNumber != null && !certificateNumber.isBlank())
                ? receivedDate.plusYears(1)
                : null;
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
        return expiryDate != null && LocalDate.now().isAfter(expiryDate);
    }
    
    public boolean isCertificateValid() {
        return certificateExpiry != null && !LocalDate.now().isAfter(certificateExpiry);
    }
    
    public void block(String reason) {
        isBlocked = true;
        System.out.println("Партия " + batchId + " заблокирована: " + reason);
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
