import Delivery.DeliveryRequest;
import Delivery.models.CargoSize;
import Delivery.models.Workload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DeliveryRequestTest {
    @Test
    @DisplayName("Проверка null и пустых значений для workload и cargoSize")
    void getDeliveryCost_NullOrEmptyWorkloadAndCargoSize_ThrowsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new DeliveryRequest(10, false, null, Workload.NORMAL));
        assertThrows(
                IllegalArgumentException.class,
                () -> new DeliveryRequest(10, false, CargoSize.SMALL, null));
    }

    @Test
    @DisplayName("Проверка отрицательного расстояния")
    void getDeliveryCost_NegativeDistance_ThrowsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new DeliveryRequest(-5, false, CargoSize.SMALL, Workload.NORMAL));
    }
}
