package server;

public class Servertile {
  private Color color;
  private Shape shape;

  public Servertile(Color color, Shape shape) {
    this.color = color;
    this.shape = shape;
  }
  
  /**
   * Constructor that creates a tile with a certain number.
   * @param tileNumber (number to create the tile with)
   */

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

  public Color hasColor() {
    return color;
  }

  public Shape hasShape() {
    return shape;
  }
  
  /**
   * changes the tile to the color with the value.
   * @param tileNumber (the value to change the tile's color to)
   */

  public void intToColor(int tileNumber) {
    for (int j = 1; j < tileNumber; j++) {
      this.color = color.other();
    }
  }
  
  /**
   * changes the tile to the shape with the value.
   * @param tileNumber (the value to change the tile's shape to)
   */

  public void intToShape(int tileNumber) {
    for (int j = 1; j < tileNumber; j++) {
      this.shape = shape.other();
    }
  }
  
  /**
   * converts the tile to an int representation.
   * @param tile (tile to be converted)
   * @return (
   */

  public int tileToInt(Servertile tile) {
    int result = 0;
    result = tile.hasColor().colorToInt() * 6 + tile.hasShape().shapeToInt();
    return result;
  }

  public void printTile() {
    System.out.println("color: " + color + " shape: " + shape);
  }

  public String toString() {
    return color.name().substring(0, 1) + shape.name().substring(0, 2);
  }

}
