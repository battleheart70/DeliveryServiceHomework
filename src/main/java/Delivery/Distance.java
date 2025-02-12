package Delivery;

public class Distance {
  private int distance;

  public Distance(int distance) {
    if (distance
        < 0) { // not <= 0 because the distance can be 0 -> free money for delivery service :)
      throw new IllegalArgumentException("Расстояние должно быть больше 0!");
    }
    this.distance = distance;
  }

  public int getDistance() {
    return distance;
  }

  public int getDistanceAddition() {
    if (distance > 30) return 300;
    if (distance > 10) return 200;
    if (distance > 2) return 100;
    return 50;
  }
}
