package Delivery.models;

public enum CargoSize {
  LARGE(200),
  SMALL(100);

  private final int cargoSizeAddition;

  CargoSize(int cargoSizeAddition) {
    this.cargoSizeAddition = cargoSizeAddition;
  }

  public int getCargoSizeAddition() {
    return cargoSizeAddition;
  }
}
