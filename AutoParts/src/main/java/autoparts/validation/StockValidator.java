package autoparts.validation;

import autoparts.exception.InsufficientStockException;
import autoparts.exception.CertificateExpiredException;
import autoparts.model.AutoPart;
import autoparts.model.PartBatch;
import autoparts.model.StorageZone;

public class StockValidator {

    public void validateReservation(AutoPart autoPart, int amount)
            throws InsufficientStockException {

        if (amount <= 0) {
            throw new IllegalArgumentException("Количество должно быть больше 0");
        }

        // пока без проверки остатков (нет доступа к партиям)
    }

    public void validateBatchCertificate(PartBatch batch)
            throws CertificateExpiredException {

        if (!batch.isCertificateValid()) {
            throw new CertificateExpiredException(
                    "Сертификат партии просрочен",
                    batch.getBatchId(),
                    batch.getCertificateExpiry()
            );
        }
    }

    public void validateZonePlacement(AutoPart part, StorageZone zone)
            throws autoparts.exception.InvalidZoneException {
        if (!zone.isSuitableForPart(part)) {
            throw new autoparts.exception.InvalidZoneException(
                    "Зона не подходит для данной запчасти"
            );
        }
    }
}
