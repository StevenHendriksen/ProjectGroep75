package Server;

public enum Shape {
	CIRCLE, CRISSCROSS, DIAMOND, SQUARE, PLUS, STAR;
	
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
}
