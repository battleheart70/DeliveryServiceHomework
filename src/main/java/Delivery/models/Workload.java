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

  @Override
  public String toString() {
    return "Для " + name() + " загрузки сервиса коэффициент: " + multiplier;
  }
}
