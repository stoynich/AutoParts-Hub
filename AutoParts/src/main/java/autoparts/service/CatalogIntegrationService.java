package autoparts.service;

import autoparts.catalog.CatalogIntegrable;
import autoparts.logger.Logger;

import java.util.List;

public class CatalogIntegrationService {
    private final List<CatalogIntegrable> catalogs;
    private final Logger logger;
    
    public CatalogIntegrationService(Logger logger) {
        this.catalogs = new java.util.ArrayList<>();
        this.logger = logger;
    }
    
    public void addCatalog(CatalogIntegrable catalog) {
        catalogs.add(catalog);
    }

    public void syncWithTecDoc() {
        for (CatalogIntegrable catalog : catalogs) {
            if (catalog.getClass().getSimpleName().equals("TecDocCatalogClient")) {
                catalog.syncWithCatalog();
                logger.log("[SYNC] Синхронизация с TecDoc выполнена");
            }
        }
    }

    public void syncWithExist() {
        for (CatalogIntegrable catalog : catalogs) {
            if (catalog.getClass().getSimpleName().equals("ExistCatalogClient")) {
                catalog.syncWithCatalog();
                logger.log("[SYNC] Синхронизация с Exist.ru выполнена");
            }
        }
    }

    public List<String> updateCrossNumbers(String oemNumber) {
        List<String> result = new java.util.ArrayList<>();
        for (CatalogIntegrable catalog : catalogs) {
            catalog.syncWithCatalog();
            result.add("CROSS-" + oemNumber);
        }
        return result;
    }

    public boolean checkVinCompatibility(String oemNumber, String vinCode) {
        logger.log("[VIN] Проверка совместимости " + oemNumber + " с VIN " + vinCode);
        return vinCode != null && vinCode.length() == 17;
    }
}
