package client;

public class Servertile {
  private Color color;
  private Shape shape;

  public Servertile(Color color, Shape shape) {
    this.color = color;
    this.shape = shape;
  }

  public Color hasColor() {
    return color;
  }

  public Shape hasShape() {
    return shape;
  }

  public Servertile(int tileNumber) {
    shape = shape.CIRCLE;
    color = color.RED;
    int shapeNumber = tileNumber % 6;
    int colorNumber = (tileNumber / 6) + 1;
    if (shapeNumber == 0) {
      shapeNumber = 6;
    }
    intToColor(colorNumber);
    intToShape(shapeNumber);
  }

  public void intToColor(int tileNumber) {
    for (int j = 1; j < tileNumber; j++) {
      this.color = color.other();
    }
  }

  public void intToShape(int tileNumber) {
    for (int j = 1; j < tileNumber; j++) {
      this.shape = shape.other();
    }
  }

  public void printTile() {
    System.out.println("color: " + color + " shape: " + shape);
  }

  public String toString() {
    return color.name().substring(0, 1) + shape.name().substring(0, 2);
  }

}
