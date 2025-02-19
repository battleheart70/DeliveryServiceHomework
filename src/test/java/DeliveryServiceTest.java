import Delivery.DeliveryService;
import Delivery.DeliveryRequest;
import Delivery.exceptions.FragileItemDistanceExceededException;
import Delivery.models.CargoSize;
import Delivery.models.Urgency;
import Delivery.models.Workload;
import Delivery.strategies.PricingStrategyFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryServiceTest {
  private final DeliveryService deliveryService = new DeliveryService(new PricingStrategyFactory());

  @ParameterizedTest
  @DisplayName("Проверка стоимости доставки")
  @Tag("Positive")
  @CsvSource({
          "NORMAL, 1, SMALL, false, STANDARD, 400",      // Минимальная сумма (50+100=150 < 400)
          "HIGH, 15, LARGE, true, EXPRESS, 900",        // Покрытие хрупкости и повышенной загруженности
          "VERY_HIGH, 35, LARGE, false, SAME_DAY, 1280",// Максимальная доплата за расстояние (>30 км) и хрупкость запрещена
          "HIGH, 2, SMALL, true, STANDARD, 630",        // Граница расстояния (2 км) + хрупкий груз
          "NORMAL, 10, LARGE, false, EXPRESS, 400",     // Сумма ниже минимума → 400 руб.
          "HIGH, 30, SMALL, true, SAME_DAY, 1260",      // Граница расстояния (30 км) + хрупкий
          "ANY, 30, SMALL, true, STANDARD, -1",         // Хрупкий груз при расстоянии >30 км запрещен
          "VERY_HIGH, 9.9, LARGE, false, EXPRESS, 560", // Граничное значение (10 км → 9.9 км → 100 руб.)
          "HIGH, 10.1, SMALL, true, SAME_DAY, 1080",    // Граничное значение (10 км → 10.1 км → 200 руб.)
          "NORMAL, 1.9, LARGE, false, STANDARD, 400",   // Граничное значение (2 км → 1.9 км → 50 руб.) + сумма ниже минимума
          "HIGH, 29.9, SMALL, true, EXPRESS, 910",      // Граничное значение (30 км → 29.9 км → 200 руб.) + хрупкий разрешен
          "NORMAL, 2, LARGE, false, SAME_DAY, 550"      // Минимальное расстояние + максимальная срочность
  })
  void getDeliveryCost_ValidInputs_ReturnsExpectedCost(
          Workload workload, int distance, CargoSize cargoSize, boolean isFragile, Urgency urgency, int expectedCost)
      throws FragileItemDistanceExceededException {
    DeliveryRequest request = new DeliveryRequest(distance, isFragile, cargoSize, workload, urgency);
    int cost = deliveryService.calculateDeliveryCost(request);
    assertEquals(expectedCost, cost);
  }

  @Test
  @DisplayName("Проверка отказа на перевозку хрупкого груза на большое расстояние")
  @Tag("CustomException")
  void getDeliveryCost_InvalidInputs_ThrowsFragileItemDistanceExceededException() {
    assertThrows(
        FragileItemDistanceExceededException.class,
        () -> {
          DeliveryRequest request = new DeliveryRequest(35, true, CargoSize.LARGE, Workload.HIGH, Urgency.EXPRESS);
          deliveryService.calculateDeliveryCost(request);
        });
  }
}
