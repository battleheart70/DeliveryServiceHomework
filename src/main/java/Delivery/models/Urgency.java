package Delivery.models;

public enum Urgency {
    STANDARD(0), EXPRESS(50), SAME_DAY(300);

    private final int urgencyAddition;

    Urgency(int urgencyAddition) {
        this.urgencyAddition = urgencyAddition;
    }

    public int getUrgencyAddition() {
        return urgencyAddition;
    }
}
