package Delivery.models;

import static Delivery.constants.DeliveryConstants.LARGE_CARGO_SIZE_SURCHARGE;
import static Delivery.constants.DeliveryConstants.SMALL_CARGO_SIZE_SURCHARGE;

public enum CargoSize {
  LARGE(LARGE_CARGO_SIZE_SURCHARGE),
  SMALL(SMALL_CARGO_SIZE_SURCHARGE);

  private final int cargoSizeAddition;

  CargoSize(int cargoSizeAddition) {
    this.cargoSizeAddition = cargoSizeAddition;
  }

  public int getCargoSizeAddition() {
    return cargoSizeAddition;
  }

  @Override
  public String toString() {
    return "For " + name() + " cargo surcharge is " + cargoSizeAddition + " roubles";
  }
}
