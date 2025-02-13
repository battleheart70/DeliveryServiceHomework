package Delivery;

public class Fragility {
  private boolean isFragile;

  public Fragility(boolean isFragile) {
    this.isFragile = isFragile;
  }

  public int getFragilityAddition() {
    if (isFragile) return 300;
    return 0;
  }

  public void checkDistance(int distance) throws FragileItemDistanceExceededException {
    if (isFragile && distance > 30) {
      throw new FragileItemDistanceExceededException(
          "Хрупкий груз нельзя перевозить дальше 30 км!");
    }
  }
}
