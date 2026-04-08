package autoparts.model;

public enum Priority {
    REGULAR(240),
    URGENT(30);

    private final int maxPickingMinutes;

    Priority(int maxPickingMinutes) {
        this.maxPickingMinutes = maxPickingMinutes;
    }

    public int getMaxPickingMinutes() {
        return maxPickingMinutes;
    }
}