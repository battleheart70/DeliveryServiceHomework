package Delivery.strategies;

import Delivery.DeliveryRequest;

public class DistanceStrategy implements PricingStrategy {

  @Override
  public int calculate(int currentPrice, DeliveryRequest request) {
    double distance = request.distance();

    if (distance > 30) return currentPrice + 300;
    if (distance > 10) return currentPrice + 200;
    if (distance > 2) return currentPrice + 100;
    return currentPrice + 50;
  }
}
