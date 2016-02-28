package Shared;

public class Tile {
  private Color color;
  private Shape shape;

  public Tile(Color color, Shape shape) {
    this.color = color;
    this.shape = shape;
  }

  public Color getColor() {
    return color;
  }

  public Shape getShape() {
    return shape;
  }
  
  /**
   * Constructor that creates a tile with a certain number.
   * @param tileNumber (number to create the tile with)
   */

  public Tile(int tileNumber) {
    shape = shape.CIRCLE;
    color = color.RED;
    int shapeNumber = tileNumber % 6;
    int colorNumber = (tileNumber / 6) + 1;
    if (shapeNumber == 0) {
      shapeNumber = 6;
    }
    color = color.intToColor(colorNumber);
    shape = shape.intToShape(shapeNumber);
    System.out.println(this);
  }
  
  /**
   * changes the tile to the color with the value.
   * @param tileNumber (the value to change the tile's color to)
   */
  
  /**
   * changes the tile to the shape with the value.
   * @param tileNumber (the value to change the tile's shape to)
   */

  public void printTile() {
    System.out.println("color: " + color + " shape: " + shape);
  }
  
  public int tileToInt(Tile tile) {
    int result = 0;
    result = tile.getColor().colorToInt() * 6 + tile.getShape().shapeToInt();
    return result;
  }

  @Override
  public String toString() {
    return color.name().substring(0, 1) + shape.name().substring(0, 2);
  }

}
