package Server;

import Client.Color;
import Client.Shape;

public class Tile {
	private Color color;
	private Shape shape;
	private int number;

	public Tile(int number, Color color, Shape shape) {
		this.color = color;
		this.shape = shape;
		this.number = number;
	}

	public Tile(int number) {
		this.number = number;
		this.getTile(number);
	}

	public void getTile(int number) {
		this.color = Color.RED;
		this.shape = Shape.CIRCLE;

		for (int i = 0; i < 36; i++) {
			if (number == i + 1){
				return;
			}
			if ((i+1) % 6 == 0 && i != 0) {
				this.shape = shape.other();
			}
			this.color = color.other();
		}

	}

	public Color hasColor() {
		return color;
	}

	public Shape hasShape() {
		return shape;
	}

	public int hasNumber() {
		return number;
	}

	public void printTile() {
		System.out.println("color: " + color + " shape: " + shape);
	}

	public String toString() {
		return color.name().substring(0, 1) + shape.name().substring(0, 2);
	}

}