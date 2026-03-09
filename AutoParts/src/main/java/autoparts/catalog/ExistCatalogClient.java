package autoparts.catalog;

public class ExistCatalogClient implements CatalogIntegrable {
    
    @Override
    public void syncWithCatalog() {
        // TODO: занятие 3 - имитация API Exist.ru
    }
    
    @Override
    public String getCatalogName() {
        return "Exist.ru";
    }
}
