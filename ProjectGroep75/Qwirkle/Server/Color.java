package Server;

public enum Color {
  RED, ORANGE, YELLOW, BLUE, GREEN, PURPLE;

  public Color other() {
    if (this == RED) {
      return ORANGE;
    } else if (this == ORANGE) {
      return YELLOW;
    } else if (this == YELLOW) {
      return BLUE;
    } else if (this == BLUE) {
      return GREEN;
    } else if (this == GREEN) {
      return PURPLE;
    } else if (this == PURPLE) {
      return RED;
    } else {
      return null;
    }
  }

  public int colorToInt() {
    if (this == RED) {
      return 0;
    } else if (this == ORANGE) {
      return 1;
    } else if (this == YELLOW) {
      return 2;
    } else if (this == GREEN) {
      return 3;
    } else if (this == BLUE) {
      return 4;
    } else if (this == PURPLE) {
      return 5;
    } else {
      return -1;
    }
  }
}
