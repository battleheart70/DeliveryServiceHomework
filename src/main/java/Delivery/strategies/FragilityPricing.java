package Delivery.strategies;

import Delivery.DeliveryRequest;

import static Delivery.constants.DeliveryConstants.FRAGILE_SURCHARGE;

public class FragilityPricing implements PricingStrategy {

  @Override
  public boolean isApplicable(DeliveryRequest request) {
    return request.isFragile();
  }

  @Override
  public int calculate(int currentPrice, DeliveryRequest request) {
    return currentPrice + FRAGILE_SURCHARGE;
  }
}
