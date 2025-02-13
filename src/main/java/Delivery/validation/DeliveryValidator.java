package Delivery.validation;

import Delivery.DeliveryRequest;
import Delivery.exceptions.FragileItemDistanceExceededException;
import Delivery.models.CargoSize;
import Delivery.models.Workload;

import java.util.EnumSet;

public class DeliveryValidator {
    private static final int MAX_FRAGILE_DISTANCE = 30;

    // Можно сделать константы для допустимых значений перечислений
    private static final EnumSet<CargoSize> VALID_CARGO_SIZES = EnumSet.allOf(CargoSize.class);
    private static final EnumSet<Workload> VALID_WORKLOADS = EnumSet.allOf(Workload.class);

    // Стандартные сообщения об ошибках
    private static final String INVALID_CARGO_SIZE_MESSAGE = "Размер груза некорректный! Доступные значения: SMALL, LARGE";
    private static final String INVALID_WORKLOAD_MESSAGE = "Нагрузка некорректная! Доступные значения: VERY_HIGH, HIGH, ELEVATED, NORMAL";
    private static final String INVALID_FRAGILE_DISTANCE_MESSAGE = "Хрупкий груз нельзя перевозить дальше " + MAX_FRAGILE_DISTANCE + " км!";

    public static void validate(DeliveryRequest deliveryRequest) throws FragileItemDistanceExceededException {
        if (deliveryRequest == null) {
            throw new IllegalArgumentException("Запрос на доставку не может быть null!");
        }

        validateCargoSize(deliveryRequest.cargoSize());
        validateWorkload(deliveryRequest.workload());
        validateFragileItemDistance(deliveryRequest.distance(), deliveryRequest.isFragile());
    }


    public static void validateCargoSize(CargoSize cargoSize) {
        if (!VALID_CARGO_SIZES.contains(cargoSize)) {
            throw new IllegalArgumentException(INVALID_CARGO_SIZE_MESSAGE);
        }
    }

    public static void validateWorkload(Workload workload) {
        if (!VALID_WORKLOADS.contains(workload)) {
            throw new IllegalArgumentException(INVALID_WORKLOAD_MESSAGE);
        }
    }

    public static void validateFragileItemDistance(int distance, boolean isFragile) throws FragileItemDistanceExceededException {
        if (isFragile && distance > MAX_FRAGILE_DISTANCE) {
            throw new FragileItemDistanceExceededException(INVALID_FRAGILE_DISTANCE_MESSAGE);
        }
    }
}
