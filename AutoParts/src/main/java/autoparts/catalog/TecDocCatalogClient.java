package autoparts.catalog;

public class TecDocCatalogClient implements CatalogIntegrable {

    @Override
    public void syncWithCatalog() {
        System.out.println("Sync with TecDoc...");
    }
    
    @Override
    public String getCatalogName() {
        return "TecDoc";
    }
}
