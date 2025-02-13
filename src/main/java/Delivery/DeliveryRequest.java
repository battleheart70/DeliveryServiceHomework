package Delivery;

import Delivery.models.CargoSize;
import Delivery.models.Workload;

public record DeliveryRequest(int distance, boolean isFragile, CargoSize cargoSize, Workload workload) {
    public DeliveryRequest {
        if (distance < 0) throw new IllegalArgumentException("Расстояние должно быть ≥ 0");
        if (cargoSize == null) throw new IllegalArgumentException("CargoSize не может быть null");
        if (workload == null) throw new IllegalArgumentException("Workload не может быть null");
    }
}
