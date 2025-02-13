package Delivery.strategies;

import Delivery.DeliveryRequest;

public class DistanceStrategy implements PricingStrategy {


  /*public Distance(int distance) {
    if (distance
        < 0) { // not <= 0 because the distance can be 0 -> free money for delivery service :)
      throw new IllegalArgumentException("Расстояние должно быть больше 0!");
    }
    this.distance = distance;
  }*/
  @Override
  public int calculate(int currentPrice, DeliveryRequest request) {
    int distance = request.distance();

    if (distance > 30) return currentPrice + 300;
    if (distance > 10) return currentPrice + 200;
    if (distance > 2) return currentPrice + 100;
    return currentPrice + 50;
  }
}
