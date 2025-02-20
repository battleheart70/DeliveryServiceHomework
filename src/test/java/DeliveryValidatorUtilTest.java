import Delivery.models.Urgency;
import Delivery.validation.DeliveryValidatorUtil;
import Delivery.DeliveryRequest;
import Delivery.exceptions.FragileItemDistanceExceededException;
import Delivery.models.CargoSize;
import Delivery.models.Workload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Delivery.constants.DeliveryConstants.MAX_DELIVERY_DISTANCE;
import static org.junit.jupiter.api.Assertions.*;

class DeliveryValidatorUtilTest {

  @Test
  @DisplayName("Валидация корректного запроса на доставку")
  void validate_ValidRequest_NoExceptionThrown() {
    DeliveryRequest request =
        new DeliveryRequest(10, false, CargoSize.SMALL, Workload.NORMAL, Urgency.SAME_DAY);
    assertDoesNotThrow(() -> DeliveryValidatorUtil.validate(request));
  }

  @Test
  @DisplayName("Валидация null запроса на доставку")
  void validate_NullRequest_ThrowsIllegalArgumentException() {
    assertThrows(
        IllegalArgumentException.class,
        () -> DeliveryValidatorUtil.validate(null),
        "Запрос на доставку не может быть null!");
  }

  @Test
  @DisplayName("Валидация хрупкого груза на допустимое расстояние")
  void validateFragileItemDistance_ValidDistance_NoExceptionThrown() {
    assertDoesNotThrow(() -> DeliveryValidatorUtil.validateFragileItemDistance(10, true));
  }

  @Test
  @DisplayName("Валидация хрупкого груза на недопустимое расстояние")
  void validateFragileItemDistance_ExceedsMaxDistance_ThrowsFragileItemDistanceExceededException() {
    assertThrows(
        FragileItemDistanceExceededException.class,
        () -> DeliveryValidatorUtil.validateFragileItemDistance(31, true),
        "Хрупкий груз нельзя перевозить дальше 30 км!");
  }

  @Test
  @DisplayName("Валидация превышения максимального расстояния")
  void validate_ExceedsMaxDistance_ThrowsIllegalArgumentException() {
    DeliveryRequest request =
        new DeliveryRequest(100.1, false, CargoSize.SMALL, Workload.NORMAL, Urgency.SAME_DAY);
    assertThrows(
        IllegalArgumentException.class,
        () -> DeliveryValidatorUtil.validate(request),
            "Расстояние не может быть больше " + MAX_DELIVERY_DISTANCE);
  }
}
