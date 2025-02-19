package Delivery.models;

import static Delivery.constants.DeliveryConstants.*;

public enum Workload {
  VERY_HIGH(WORKLOAD_VERY_HIGH_MULTIPLIER),
  HIGH(WORKLOAD_HIGH_MULTIPLIER),
  ELEVATED(WORKLOAD_ELEVATED_MULTIPLIER),
  NORMAL(WORKLOAD_NORMAL_MULTIPLIER);

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
