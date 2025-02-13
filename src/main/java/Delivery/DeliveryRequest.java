package Delivery;

import Delivery.models.CargoSize;
import Delivery.models.Workload;

public record DeliveryRequest(int distance, boolean isFragile, CargoSize cargoSize, Workload workload) {
}
