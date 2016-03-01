package Shared;

public class Tile {
  private Color color;
  private Shape shape;

  public Tile(Color color, Shape shape) {
    this.color = color;
    this.shape = shape;
  }

  public static void main(String[] args0) {
    for (int i = 0; i < 36; i++) {
      Tile tile = new Tile(i);
      //if(i != tile.tileToInt()){
        System.out.println("i " + i);
        System.out.println(tile.tileToInt());
        System.out.println(tile.getColor());
        System.out.println(tile.getShape());
      //}
    }
    System.out.println("Succes!");
  }

  /**
   * Constructor that creates a tile with a certain number.
   * 
   * @param tileNumber
   *          (number to create the tile with)
   */

  public Tile(int tileNumber) {
    int shapeNumber = tileNumber % 6;
    int colorNumber = (tileNumber / 6);
    //System.out.println("colorNumber " + colorNumber);
    //System.out.println("shapeNumber " + shapeNumber);
    color = intToColor(colorNumber);
    //System.out.println("color " + color);
    shape = intToShape(shapeNumber);
  }

  /**
   * changes the tile to the color with the value.
   * 
   * @param tileNumber
   *          (the value to change the tile's color to)
   */

  /**
   * changes the tile to the shape with the value.
   * 
   * @param tileNumber
   *          (the value to change the tile's shape to)
   */

  public void printTile() {
    System.out.println("color: " + color + " shape: " + shape);
  }
  
  public Shape intToShape(int tileNumber) {
    Shape shape;
    switch (tileNumber) {
        case 0:  shape = Shape.CIRCLE;
                 break;
        case 1:  shape = Shape.CRISSCROSS;
                 break;
        case 2:  shape = Shape.DIAMOND;
                 break;
        case 3:  shape = Shape.SQUARE;
                 break;
        case 4:  shape = Shape.PLUS;
                 break;
        case 5:  shape = Shape.STAR;
                 break;
        default: shape = null;
                 break;
    }
    return shape;
  }

  public Color intToColor(int tileNumber) {
    Color color;
    switch (tileNumber) {
        case 0:  color = Color.RED;
                 break;
        case 1:  color = Color.ORANGE;
                 break;
        case 2:  color = Color.YELLOW;
                 break;
        case 3:  color = Color.GREEN;
                 break;
        case 4:  color = Color.BLUE;
                 break;
        case 5:  color = Color.PURPLE;
                 break;
        default: color = null;
                 break;
    }
    return color;
  }
  
  public int tileToInt() {
    int result = 0;
    result = getColor().colorToInt() * 6 + getShape().shapeToInt();
    return result;
  }
  
  public Color getColor() {
    return color;
  }

  public Shape getShape() {
    return shape;
  }
  


  @Override
  public String toString() {
    return color.name().substring(0, 1) + shape.name().substring(0, 2);
  }

}
