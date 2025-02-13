import Delivery.DeliveryService;
import Delivery.DeliveryRequest;
import Delivery.exceptions.FragileItemDistanceExceededException;
import Delivery.models.CargoSize;
import Delivery.models.Workload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryServiceTest {
  private final DeliveryService deliveryService = new DeliveryService();

  @ParameterizedTest
  @DisplayName("Проверка стоимости доставки")
  @Tag("Positive")
  @CsvSource({
          "HIGH, 25, LARGE, true, 980",
          "NORMAL, 5, SMALL, true, 500",
          "ELEVATED, 10, SMALL, true, 600",
          "NORMAL, 20, LARGE, false, 400",
          "NORMAL, 0, SMALL, false, 400",
          "NORMAL, 9999999, SMALL, false, 400",
          "NORMAL, 1, SMALL, false, 400",
          "VERY_HIGH, 15, LARGE, true, 1120",
          "HIGH, 30, LARGE, true, 980",
          "NORMAL, 2, SMALL, false, 400",
          "NORMAL, 10, SMALL, true, 500",
          "NORMAL, 31, SMALL, false, 400",
          "NORMAL, 10, LARGE, false, 400",
          "ELEVATED, 2, LARGE, true, 660",
          "VERY_HIGH, 2, SMALL, true, 720",
          "VERY_HIGH, 2, LARGE, false, 400"
  })
  void getDeliveryCost_ValidInputs_ReturnsExpectedCost(Workload workload, int distance, CargoSize cargoSize, boolean isFragile, int expectedCost)
          throws FragileItemDistanceExceededException {
    DeliveryRequest request = new DeliveryRequest(distance, isFragile, cargoSize, workload);
    int cost = deliveryService.calculateDeliveryCost(request);
    assertEquals(expectedCost, cost);
  }

  @ParameterizedTest
  @DisplayName("Проверка некорректного workload и cargoSize")
  @CsvSource({
          "INVALID", // Некорректное значение для workload и cargoSize
          "EMPTY",   // Пустая строка (заменено на "EMPTY" для проверки)
          "NULL"     // Некорректная строка "NULL", которая не является настоящим null
  })
  void getDeliveryCost_InvalidWorkloadOrCargoSize_ThrowsIllegalArgumentException(String value) {
    assertThrows(IllegalArgumentException.class, () -> {

      try {
        Workload workload = Workload.valueOf(value);
        DeliveryRequest request = new DeliveryRequest(10, false, CargoSize.SMALL, workload);
        deliveryService.calculateDeliveryCost(request);
      } catch (IllegalArgumentException e) {

        CargoSize cargoSize = CargoSize.valueOf(value);
        DeliveryRequest request = new DeliveryRequest(10, false, cargoSize, Workload.NORMAL);
        deliveryService.calculateDeliveryCost(request);
      }
    });
  }

  @Test
  @DisplayName("Проверка null и пустых значений для workload и cargoSize")
  void getDeliveryCost_NullOrEmptyWorkloadAndCargoSize_ThrowsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> {

      DeliveryRequest request = new DeliveryRequest(10, false, null, null);
      deliveryService.calculateDeliveryCost(request);
    });

    assertThrows(IllegalArgumentException.class, () -> {

      DeliveryRequest request = new DeliveryRequest(10, false, CargoSize.SMALL, null);
      deliveryService.calculateDeliveryCost(request);
    });
  }

  @Test
  @DisplayName("Проверка отрицательного расстояния")
  void getDeliveryCost_NegativeDistance_ThrowsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> {
      DeliveryRequest request = new DeliveryRequest(-5, false, CargoSize.SMALL, Workload.NORMAL);
      deliveryService.calculateDeliveryCost(request);
    });
  }

  @Test
  @DisplayName("Проверка отказа на перевозку хрупкого груза на большое расстояние")
  @Tag("CustomException")
  void getDeliveryCost_InvalidInputs_ThrowsFragileItemDistanceExceededException() {
    assertThrows(FragileItemDistanceExceededException.class, () -> {
      DeliveryRequest request = new DeliveryRequest(35, true, CargoSize.LARGE, Workload.HIGH);
      deliveryService.calculateDeliveryCost(request);
    });
  }
}
