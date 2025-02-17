package Delivery;

import Delivery.exceptions.FragileItemDistanceExceededException;
import Delivery.strategies.*;
import Delivery.validation.DeliveryValidator;

import java.util.Arrays;
import java.util.List;

public class DeliveryService {
  private final List<PricingStrategy> strategies;
  public static final int MIN_COST = 400;

  public DeliveryService() {
    this.strategies =
        Arrays.asList(
            new DistanceStrategy(),
            new FragilityPricing(),
            new CargoSizePricing(),
            new WorkloadPricing());
  }

  public int calculateDeliveryCost(DeliveryRequest request)
      throws FragileItemDistanceExceededException {
    DeliveryValidator.validate(request);
    int price = 0;
    for (PricingStrategy strategy : strategies) {
      price = strategy.calculate(price, request);
    }
    return Math.max(price, MIN_COST);
  }
}
