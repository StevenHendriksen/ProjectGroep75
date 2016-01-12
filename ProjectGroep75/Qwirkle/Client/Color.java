package Client;

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
}
