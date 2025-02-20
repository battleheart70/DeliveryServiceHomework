package Delivery;

import Delivery.exceptions.FragileItemDistanceExceededException;
import Delivery.strategies.PricingStrategy;
import Delivery.strategies.PricingStrategyFactory;
import Delivery.validation.DeliveryValidatorUtil;

import java.util.List;

import static Delivery.constants.DeliveryConstants.MIN_COST;

public class DeliveryService {
  private final List<PricingStrategy> strategies;

  public DeliveryService(PricingStrategyFactory strategyFactory) {
    this.strategies = strategyFactory.getStrategies();
  }

  public int calculateDeliveryCost(DeliveryRequest request) throws FragileItemDistanceExceededException {
    DeliveryValidatorUtil.validate(request);
    int price = 0;
    for (PricingStrategy strategy : strategies) {
      if (strategy.isApplicable(request)){
        price = strategy.calculate(price, request);
      }
    }
    return Math.max(price, MIN_COST);
  }
}
