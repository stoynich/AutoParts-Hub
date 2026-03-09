package autoparts.exception;

public class IncompatiblePartException extends Exception {
    private final String vinCode;
    private final String oemNumber;
    private final String reason;
    
    public IncompatiblePartException(String message, String vinCode, 
                                      String oemNumber, String reason) {
        super(message);
        this.vinCode = vinCode;
        this.oemNumber = oemNumber;
        this.reason = reason;
    }
    
    public String getVinCode() { return vinCode; }
    public String getOemNumber() { return oemNumber; }
    public String getReason() { return reason; }
}
