import Delivery.DeliveryService;
import Delivery.FragileItemDistanceExceededException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryServiceTest {

  @ParameterizedTest
  @DisplayName("Проверка стоимости доставки")
  @Tag("Positive")
  @CsvSource({
          "high, 25, large, true, 980", // нормальный случай
          "normal, 5, small, true, 500", // нормальный случай
          "elevated, 10, small, true, 600", // нормальный случай
          "normal, 20, large, false, 400", // минимальная стоимость
          "normal, 0, small, false, 400", // доставка на нулевое расстояние
          "normal, 9999999, small, false, 400", // очень большое расстояние, минимальная стоимость
          "normal, 1, small, false, 400", // доставка на минимальное расстояние
          "very high, 15, large, true, 1120", // высокая загруженность
          "high, 30, large, true, 980", // максимальное расстояние с хрупким грузом
          "normal, 2, small, false, 400", // минимальное расстояние с малым грузом
          "normal, 10, small, true, 500", // расстояние до 10 км с хрупким грузом
          "normal, 31, small, false, 400", // расстояние чуть больше 30 км
          "normal, 10, large, false, 400", // расстояние до 10 км, большой груз
          "elevated, 2, large, true, 660", // малая дистанция с высоким коэффициентом загруженности
          "very high, 2, small, true, 720", // малая дистанция с очень высоким коэффициентом загруженности
          "very high, 2, large, false, 400" // малая дистанция, высокий коэффициент, не хрупкий груз
  })
  void getDeliveryCost_ValidInputs_ReturnsExpectedCost(String workload, int distance, String cargoSize, boolean isFragile, BigDecimal expectedCost)
          throws FragileItemDistanceExceededException {
    BigDecimal cost = DeliveryService.getDeliveryCost(workload, distance, cargoSize, isFragile);
    assertEquals(expectedCost, cost);
  }

  @ParameterizedTest
  @DisplayName("Проверка невалидных данных")
  @Tag ("Negative")
  @CsvSource({
          "invalid, 20, large, false", // некорректный workload
          "normal, -5, small, false", // отрицательное расстояние
          "normal, 10, null, false", // null для размера груза
          "normal, 10, '', false", // пустая строка для размера груза
          "normal, 10, invalid_size, false", // некорректный размер груза
          "null, 10, small, true", // null для workload
  })
  void getDeliveryCost_InvalidInputs_ThrowsIllegalArgumentException(String workload, int distance, String cargoSize, boolean isFragile) {
    assertThrows(IllegalArgumentException.class, () -> {
      DeliveryService.getDeliveryCost(workload, distance, cargoSize, isFragile);
    });
  }

  @Test
  @DisplayName("Проверка отказа на перевозку хрупкого груза на большое расстояние")
  @Tag ("CustomException")
  void getDeliveryCost_InvalidInputs_ThrowsFragileItemDistanceExceededException() {
    assertThrows(FragileItemDistanceExceededException.class, () -> {
      DeliveryService.getDeliveryCost("high", 35, "large", true);
    });
  }
}