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
        DeliveryRequest request = new DeliveryRequest(10, false, CargoSize.SMALL, Workload.NORMAL);
        assertDoesNotThrow(() -> DeliveryValidator.validate(request));
    }

    @Test
    @DisplayName("Валидация null запроса на доставку")
    void validate_NullRequest_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DeliveryValidator.validate(null));
    }

    @Test
    @DisplayName("Валидация отрицательного расстояния")
    void validateDistance_NegativeDistance_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DeliveryValidator.validateDistance(-1));
    }

    @Test
    @DisplayName("Валидация нулевого расстояния")
    void validateDistance_ZeroDistance_ThrowsIllegalArgumentException() {
        assertDoesNotThrow(() -> DeliveryValidator.validateDistance(0));
    }

    @Test
    @DisplayName("Валидация корректного размера груза")
    void validateCargoSize_ValidCargoSize_NoExceptionThrown() {
        assertDoesNotThrow(() -> DeliveryValidator.validateCargoSize(CargoSize.SMALL));
    }

    @Test
    @DisplayName("Валидация некорректного размера груза")
    void validateCargoSize_InvalidCargoSize_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DeliveryValidator.validateCargoSize(null));
    }

    @Test
    @DisplayName("Валидация корректной нагрузки")
    void validateWorkload_ValidWorkload_NoExceptionThrown() {
        assertDoesNotThrow(() -> DeliveryValidator.validateWorkload(Workload.NORMAL));
    }

    @Test
    @DisplayName("Валидация некорректной нагрузки")
    void validateWorkload_InvalidWorkload_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DeliveryValidator.validateWorkload(null));
    }

    @Test
    @DisplayName("Валидация хрупкого груза на допустимое расстояние")
    void validateFragileItemDistance_ValidDistance_NoExceptionThrown() {
        assertDoesNotThrow(() -> DeliveryValidator.validateFragileItemDistance(10, true));
    }

    @Test
    @DisplayName("Валидация хрупкого груза на недопустимое расстояние")
    void validateFragileItemDistance_ExceedsMaxDistance_ThrowsFragileItemDistanceExceededException() {
        assertThrows(FragileItemDistanceExceededException.class, () -> DeliveryValidator.validateFragileItemDistance(31, true));
    }
}