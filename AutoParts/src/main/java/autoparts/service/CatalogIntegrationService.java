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
    
    // TODO: занятие 3 - синхронизация с TecDoc
    public void syncWithTecDoc() {
        // TODO: найти TecDocCatalogClient в catalogs, вызвать syncWithCatalog()
        logger.log("[SYNC] Синхронизация с TecDoc...");
    }
    
    // TODO: занятие 3 - синхронизация с Exist.ru
    public void syncWithExist() {
        // TODO: найти ExistCatalogClient в catalogs, вызвать syncWithCatalog()
        logger.log("[SYNC] Синхронизация с Exist.ru...");
    }
    
    // TODO: занятие 3 - обновление кросс-номеров
    public List<String> updateCrossNumbers(String oemNumber) {
        // TODO: запросить кросс-номера из каталогов
        return new java.util.ArrayList<>();
    }
    
    // TODO: занятие 3 - проверка совместимости с VIN через API
    public boolean checkVinCompatibility(String oemNumber, String vinCode) {
        // TODO: имитация API запроса
        return true;
    }
}
