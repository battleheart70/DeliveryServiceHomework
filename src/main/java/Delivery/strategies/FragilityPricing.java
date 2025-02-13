package Delivery.strategies;

import Delivery.DeliveryRequest;

public class FragilityPricing implements PricingStrategy {
  @Override
  public int calculate(int currentPrice, DeliveryRequest request) {
    return request.isFragile() ? currentPrice + 300 : currentPrice;
  }
  /*public FragilityPricing(boolean isFragile) {
    this.isFragile = isFragile;
  }

  public int getFragilityAddition() {
    if (isFragile) return 300;
    return 0;
  }*/

  /*public void checkDistance(int distance) throws FragileItemDistanceExceededException {
    if (isFragile && distance > 30) {
      throw new FragileItemDistanceExceededException(
          "Хрупкий груз нельзя перевозить дальше 30 км!");
    }
  }*/
}
