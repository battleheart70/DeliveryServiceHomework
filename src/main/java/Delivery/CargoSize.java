package Delivery;

public class CargoSize {
  private String cargoSize;

  public CargoSize(String cargoSize) {
    if (cargoSize == null || !cargoSize.equals("large") && !cargoSize.equals("small")) {
      throw new IllegalArgumentException(
          "Размер груза некорекктный! Может быть только 'large' или 'small'");
    }
    this.cargoSize = cargoSize;
  }

  public int getCargoSizeAddition() {
    if (cargoSize.equals("large")) return 200;
    if (cargoSize.equals("small")) return 100;
    return 0;
  }
}
