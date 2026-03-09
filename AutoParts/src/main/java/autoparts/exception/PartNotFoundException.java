package autoparts.exception;

public class PartNotFoundException extends Exception {
    private final String oemNumber;
    
    public PartNotFoundException(String oemNumber) {
        super("Запчасть не найдена: " + oemNumber);
        this.oemNumber = oemNumber;
    }
    
    public String getOemNumber() { return oemNumber; }
}
