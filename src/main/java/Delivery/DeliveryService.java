package Delivery;

import Delivery.exceptions.FragileItemDistanceExceededException;
import Delivery.strategies.PricingStrategy;
import Delivery.strategies.PricingStrategyFactory;
import Delivery.validation.DeliveryValidatorUtil;

import java.util.List;

public class DeliveryService {
  private final PricingStrategyFactory strategyFactory;
  public static final int MIN_COST = 400;

  public DeliveryService(PricingStrategyFactory strategyFactory) {
    this.strategyFactory = strategyFactory;
  }

  public int calculateDeliveryCost(DeliveryRequest request)
          throws FragileItemDistanceExceededException {
    DeliveryValidatorUtil.validate(request);
    List<PricingStrategy> strategies = strategyFactory.getStrategiesForType(request.getDeliveryType());
    int price = 0;
    for (PricingStrategy strategy : strategies) {
      price = strategy.calculate(price, request);
    }
    return Math.max(price, MIN_COST);
  }
}