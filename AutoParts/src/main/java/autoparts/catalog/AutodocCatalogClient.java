package autoparts.catalog;

public class AutodocCatalogClient implements CatalogIntegrable {

    @Override
    public void syncWithCatalog() {
        System.out.println("Sync with Autodoc...");
    }
    
    @Override
    public String getCatalogName() {
        return "Autodoc";
    }
}
