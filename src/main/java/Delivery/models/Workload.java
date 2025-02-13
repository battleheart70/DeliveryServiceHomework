package Delivery.models;

public enum Workload {
  VERY_HIGH(1.6),
  HIGH(1.4),
  ELEVATED(1.2),
  NORMAL(1);

  private final double multiplier;

  Workload(double multiplier) {
    this.multiplier = multiplier;
  }

  public double getMultiplier() {
    return multiplier;
  }


 /* public Workload(String workload) {
    if (workload == null
        || !workload.equals("very high")
            && !workload.equals("high")
            && !workload.equals("elevated")
            && !workload.equals("normal")) {
      throw new IllegalArgumentException(
          "Загруженность службы доставки может быть только 'very high', 'high', 'elevated' or 'normal'");
    }
    this.workload = workload;
  }

  public double getMultiplier() {
    return switch (workload) {
      case "very high" -> 1.6;
      case "high" -> 1.4;
      case "elevated" -> 1.2;
      default -> 1; // normal
    };
  }*/
}
