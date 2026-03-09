package autoparts.exception;

public class InvalidVinException extends Exception {
    private final String vinCode;
    
    public InvalidVinException(String message, String vinCode) {
        super(message);
        this.vinCode = vinCode;
    }
    
    public String getVinCode() { return vinCode; }
}
