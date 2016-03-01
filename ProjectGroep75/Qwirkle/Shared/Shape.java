package Shared;

public enum Shape {
  CIRCLE, CRISSCROSS, DIAMOND, SQUARE, PLUS, STAR;

  /**
   * Used to cycle through the shapes.
   * 
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
   * 
   * @return int of the shape)
   */

  public int shapeToInt() {
    int i;
    switch (this) {
        case CIRCLE : i = 0;
                 break;
        case CRISSCROSS: i = 1;
                 break;
        case DIAMOND:  i = 2;
                 break;
        case SQUARE:  i = 3;
                 break;
        case PLUS:  i = 4;
                 break;
        case STAR:  i = 5;
                 break;
        default: i = 0;
                 break;
    }
    return i;
  }

  
}
