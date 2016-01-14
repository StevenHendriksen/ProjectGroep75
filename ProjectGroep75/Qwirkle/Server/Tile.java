package Server;

public class Tile {
	private Color color;
	private Shape shape;
	
	public Tile(Color color, Shape shape){
		this.color = color;
		this.shape = shape;
	}
	
	public Color hasColor(){
		return color;
	}
	
	public Shape hasShape(){
		return shape;
	}
	
	public Tile(int i) {
		shape = shape.CIRCLE;
		color = color.RED;
		int shapeNumber = i % 6;
		int colorNumber = (i / 6) + 1;
		if (shapeNumber == 0) {
			shapeNumber = 6;
		}
		intToColor(colorNumber);
		intToShape(shapeNumber);
	}
	
	public void intToColor(int i){
		for(int j = 1; j < i; j++){
			this.color = color.other();
		}
	}
	
	public void intToShape(int i){
		for(int j = 1; j < i; j++){
			this.shape = shape.other();
		}
	}
	
	public void printTile(){
		System.out.println("color: " + color + " shape: " + shape);
	}
	public String toString(){
		return color.name().substring(0, 1) + shape.name().substring(0, 2);
	}
	
}
