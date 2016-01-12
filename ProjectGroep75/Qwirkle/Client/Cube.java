package Client;

public class Cube {
	private Color color;
	private Shape shape;
	
	public Cube(Color color, Shape shape){
		this.color = color;
		this.shape = shape;
	}
	
	public Color hasColor(){
		return color;
	}
	
	public Shape hasShape(){
		return shape;
	}
	
	public void printCube(){
		System.out.println("color: " + color + " shape: " + shape);
	}
	public String toString(){
		return color.name().substring(0, 1) + shape.name().substring(0, 2);
	}
	
}
