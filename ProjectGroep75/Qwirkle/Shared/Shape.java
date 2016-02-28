package Shared;

public enum Shape {
  CIRCLE, CRISSCROSS, DIAMOND, SQUARE, PLUS, STAR;

  /**
   * Used to cycle through the shapes.
   * @return (next shape)
   */
  
  public Shape other() {
    if (this == CIRCLE) {
      return CRISSCROSS;
    } else if (this == CRISSCROSS) {
      return DIAMOND;
    } else if (this == DIAMOND) {
      return SQUARE;
    } else if (this == SQUARE) {
      return PLUS;
    } else if (this == PLUS) {
      return STAR;
    } else if (this == STAR) {
      return CIRCLE;
    } else {
      return null;
    }
  }
  
  /**
   * Used to convert the shape to their corresponding int.
   * @return int of the shape)
   */

  public int shapeToInt() {
    if (this == CIRCLE) {
      return 1;
    } else if (this == CRISSCROSS) {
      return 2;
    } else if (this == DIAMOND) {
      return 3;
    } else if (this == SQUARE) {
      return 4;
    } else if (this == PLUS) {
      return 5;
    } else if (this == STAR) {
      return 6;
    } else {
      return 0;
    }
  }
  
  public Shape intToShape(int tileNumber) {
    Shape shape = this;
    for (int j = 1; j < tileNumber; j++) {
      shape = other();
    }
    return shape;
  }

}
