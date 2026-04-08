package autoparts.model;

public enum ABCCategory {
    A(100, Integer.MAX_VALUE, ZoneType.FAST_PICK),
    B(20, 100, ZoneType.BULK_STORAGE),
    C(0, 20, ZoneType.BULK_STORAGE);

    private final int minTurnover;
    private final int maxTurnover;
    private final ZoneType zoneType;

    ABCCategory(int minTurnover, int maxTurnover, ZoneType zoneType) {
        this.minTurnover = minTurnover;
        this.maxTurnover = maxTurnover;
        this.zoneType = zoneType;
    }

    public int getMinTurnover() {
        return minTurnover;
    }

    public int getMaxTurnover() {
        return maxTurnover;
    }

    public ZoneType getZoneType() {
        return zoneType;
    }
}

