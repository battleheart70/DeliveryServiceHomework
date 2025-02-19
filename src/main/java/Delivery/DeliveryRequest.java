package Delivery;

import Delivery.models.CargoSize;
import Delivery.models.DeliveryType;
import Delivery.models.Urgency;
import Delivery.models.Workload;

public record DeliveryRequest(
        int distance, boolean isFragile, CargoSize cargoSize, Workload workload, Urgency urgency) {
  public DeliveryRequest {
    if (distance < 0) throw new IllegalArgumentException("Расстояние должно быть ≥ 0");
    if (cargoSize == null) throw new IllegalArgumentException("CargoSize не может быть null");
    if (workload == null) throw new IllegalArgumentException("Workload не может быть null");
    if (urgency == null) throw new IllegalArgumentException("Urgency не может быть null");
  }
  public DeliveryType getDeliveryType() {
    return (urgency == Urgency.EXPRESS || urgency == Urgency.SAME_DAY) ? DeliveryType.URGENT : DeliveryType.STANDARD;
  }
}
