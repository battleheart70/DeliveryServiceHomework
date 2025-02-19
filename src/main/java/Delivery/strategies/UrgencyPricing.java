package Delivery.strategies;

import Delivery.DeliveryRequest;

public class UrgencyPricing implements PricingStrategy{

    @Override
    public int calculate(int currentPrice, DeliveryRequest request) {
        return currentPrice + request.urgency().getUrgencyAddition();
    }
}
