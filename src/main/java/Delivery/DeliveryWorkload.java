package Delivery;

public class DeliveryWorkload {
  private String workload;

  public DeliveryWorkload(String workload) {
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

  public double getWorkloadAddition() {
    return switch (workload) {
      case "very high" -> 1.6;
      case "high" -> 1.4;
      case "elevated" -> 1.2;
      default -> 1; // normal
    };
  }
}
