package autoparts.validation;

import autoparts.exception.InsufficientStockException;
import autoparts.exception.CertificateExpiredException;
import autoparts.model.AutoPart;
import autoparts.model.PartBatch;
import autoparts.model.StorageZone;

public class StockValidator {
    
    public void validateReservation(AutoPart autoPart, int amount) 
            throws InsufficientStockException {
        // TODO: занятие 4 - подсчитать доступное количество по всем партиям
        // TODO: бросить InsufficientStockException если недостаточно
    }
    
    public void validateBatchCertificate(PartBatch batch) 
            throws CertificateExpiredException {
        // TODO: занятие 4 - проверить batch.isCertificateValid()
        // TODO: бросить CertificateExpiredException если просрочен
    }
    
    public void validateZonePlacement(AutoPart part, StorageZone zone) 
            throws autoparts.exception.InvalidZoneException {
        // TODO: занятие 5 - проверить zone.isSuitableForPart(part)
    }
}
