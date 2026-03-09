package autoparts.exception;

import java.time.LocalDate;

public class CertificateExpiredException extends Exception {
    private final String batchId;
    private final LocalDate expiryDate;
    
    public CertificateExpiredException(String message, String batchId, 
                                     LocalDate expiryDate) {
        super(message);
        this.batchId = batchId;
        this.expiryDate = expiryDate;
    }
    
    public String getBatchId() { return batchId; }
    public LocalDate getExpiryDate() { return expiryDate; }
}
