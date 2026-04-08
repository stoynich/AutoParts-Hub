package autoparts.model;

public enum ZoneType {
    FAST_PICK(true, true),
    BULK_STORAGE(false, true),
    RETURNS(false, false),
    QUARANTINE(false, false),
    CROSS_DOCKING(true, false);

    private final boolean isFastAccess;
    private final boolean requiresCertificate;

    ZoneType(boolean isFastAccess, boolean requiresCertificate) {
        this.isFastAccess = isFastAccess;
        this.requiresCertificate = requiresCertificate;
    }

    public boolean isFastAccess() {
        return isFastAccess;
    }

    public boolean requiresCertificate() {
        return requiresCertificate;
    }
}