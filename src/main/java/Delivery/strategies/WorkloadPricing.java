package Delivery.strategies;

import Delivery.DeliveryRequest;

public class WorkloadPricing  implements PricingStrategy{
    @Override
    public int calculate(int currentPrice, DeliveryRequest request) {
        return (int) Math.ceil(currentPrice * request.workload().getMultiplier());
    }
}
