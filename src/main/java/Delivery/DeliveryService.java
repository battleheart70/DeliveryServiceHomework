package Delivery;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DeliveryService {
  public static BigDecimal getDeliveryCost(
      String workload, int distance, String cargoSize, boolean isFragile)
      throws FragileItemDistanceExceededException {

    Distance distanceObj = new Distance(distance);
    CargoSize cargoSizeObj = new CargoSize(cargoSize);
    Fragility fragilityObj = new Fragility(isFragile);
    DeliveryWorkload deliveryWorkload = new DeliveryWorkload(workload);

    fragilityObj.checkDistance(distance);

    int deliveryCostBase =
        distanceObj.getDistanceAddition()
            + cargoSizeObj.getCargoSizeAddition()
            + fragilityObj.getFragilityAddition();
    BigDecimal deliveryCost =
        BigDecimal.valueOf(deliveryCostBase)
            .multiply(BigDecimal.valueOf(deliveryWorkload.getWorkloadAddition()))
            .setScale(0, RoundingMode.HALF_UP);

    return deliveryCost.max(BigDecimal.valueOf(400));
  }
}
