package Delivery.strategies;

import Delivery.models.DeliveryType;
import Delivery.models.Urgency;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class PricingStrategyFactory {
  public List<PricingStrategy> getStrategiesForType(DeliveryType type) {
    return switch (type) {
      case STANDARD ->
          Arrays.asList(
              new DistanceStrategy(),
              new FragilityPricing(),
              new CargoSizePricing(),
              new WorkloadPricing());
      case URGENT ->
          Arrays.asList(
              new DistanceStrategy(),
              new FragilityPricing(),
              new CargoSizePricing(),
              new UrgencyPricing(),
              new WorkloadPricing());
    };
  }
}
