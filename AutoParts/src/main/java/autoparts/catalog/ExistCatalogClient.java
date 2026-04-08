package autoparts.catalog;

public class ExistCatalogClient implements CatalogIntegrable {

    @Override
    public void syncWithCatalog() {
        System.out.println("Sync with Exist.ru...");
    }
    
    @Override
    public String getCatalogName() {
        return "Exist.ru";
    }
}
