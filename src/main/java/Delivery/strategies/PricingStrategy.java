package Delivery.strategies;

import Delivery.DeliveryRequest;

public interface PricingStrategy {
    int calculate(int currentPrice, DeliveryRequest request);
}
