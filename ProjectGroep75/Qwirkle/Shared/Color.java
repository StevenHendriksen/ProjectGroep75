package Shared;

public enum Color {
  RED, ORANGE, YELLOW, BLUE, GREEN, PURPLE;

  /**
   * Used to cycle through the colors.
   * @return (next color)
   */
  
  public Color other() {
    if (this == RED) {
      return ORANGE;
    } else if (this == ORANGE) {
      return YELLOW;
    } else if (this == YELLOW) {
      return BLUE;
    } else if (this == GREEN) {
      return GREEN;
    } else if (this == BLUE) {
      return PURPLE;
    } else if (this == PURPLE) {
      return RED;
    } else {
      return null;
    }
  }
  /**
   * Used to convert the color to their corresponding int.
   * @return (int of the color)
   */


  
  public int colorToInt() {
    int i;
    switch (this) {
        case RED : i = 0;
                 break;
        case ORANGE: i = 1;
                 break;
        case YELLOW:  i = 2;
                 break;
        case GREEN:  i = 3;
                 break;
        case BLUE:  i = 4;
                 break;
        case PURPLE:  i = 5;
                 break;
        default: i = 0;
                 break;
    }
    return i;
  }

}
