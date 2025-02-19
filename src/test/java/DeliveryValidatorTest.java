import Delivery.models.Urgency;
import Delivery.validation.DeliveryValidator;
import Delivery.DeliveryRequest;
import Delivery.exceptions.FragileItemDistanceExceededException;
import Delivery.models.CargoSize;
import Delivery.models.Workload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryValidatorTest {

  @Test
  @DisplayName("Валидация корректного запроса на доставку")
  void validate_ValidRequest_NoExceptionThrown() {
    DeliveryRequest request = new DeliveryRequest(10, false, CargoSize.SMALL, Workload.NORMAL, Urgency.SAME_DAY);
    assertDoesNotThrow(() -> DeliveryValidator.validate(request));
  }

  @Test
  @DisplayName("Валидация null запроса на доставку")
  void validate_NullRequest_ThrowsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> DeliveryValidator.validate(null));
  }


  @Test
  @DisplayName("Валидация хрупкого груза на допустимое расстояние")
  void validateFragileItemDistance_ValidDistance_NoExceptionThrown() {
    assertDoesNotThrow(() -> DeliveryValidator.validateFragileItemDistance(10, true));
  }

  @Test
  @DisplayName("Валидация хрупкого груза на недопустимое расстояние")
  void validateFragileItemDistance_ExceedsMaxDistance_ThrowsFragileItemDistanceExceededException() {
    assertThrows(
        FragileItemDistanceExceededException.class,
        () -> DeliveryValidator.validateFragileItemDistance(31, true));
  }
}
