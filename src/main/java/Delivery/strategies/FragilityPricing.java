package Delivery.strategies;

import Delivery.DeliveryRequest;

public class FragilityPricing implements PricingStrategy {
  @Override
  public int calculate(int currentPrice, DeliveryRequest request) {
    return request.isFragile() ? currentPrice + 300 : currentPrice;
  }
}
