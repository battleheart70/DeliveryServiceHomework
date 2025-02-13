package Delivery.strategies;

import Delivery.DeliveryRequest;

public class CargoSizePricing implements PricingStrategy {
  @Override
  public int calculate(int currentPrice, DeliveryRequest request) {
    return currentPrice + request.cargoSize().getCargoSizeAddition();
  }
}
