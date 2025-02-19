package Delivery.validation;

import Delivery.DeliveryRequest;
import Delivery.exceptions.FragileItemDistanceExceededException;

public class DeliveryValidatorUtil {
  private static final int MAX_FRAGILE_DISTANCE = 30;

  private static final String INVALID_FRAGILE_DISTANCE_MESSAGE =
      "Хрупкий груз нельзя перевозить дальше " + MAX_FRAGILE_DISTANCE + " км!";

  private DeliveryValidatorUtil() {
    throw new UnsupportedOperationException("Это утилитарный класс, невозможно создать экземпляр!");
  }

  public static void validate(DeliveryRequest deliveryRequest)
      throws FragileItemDistanceExceededException {
    if (deliveryRequest == null) {
      throw new IllegalArgumentException("Запрос на доставку не может быть null!");
    }
    validateFragileItemDistance(deliveryRequest.distance(), deliveryRequest.isFragile());
  }

  public static void validateFragileItemDistance(double distance, boolean isFragile)
      throws FragileItemDistanceExceededException {
    if (isFragile && distance > MAX_FRAGILE_DISTANCE) {
      throw new FragileItemDistanceExceededException(INVALID_FRAGILE_DISTANCE_MESSAGE);
    }
  }
}
