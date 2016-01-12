package Server;

public class Bag {
	private Cube[] cubes;
	private Cube cube;
	private Color color;
	private Shape shape;
	
	public Bag(){
		
	}
	
	public static void main(String[] args){
		Bag bag = new Bag();
		
		bag.allCubes();
		bag.takeCube().printCube();
	}
	
	public void allCubes(){
		cubes = new Cube[108];
		color = Color.RED;
		shape = Shape.CIRCLE;
		for(int j = 0; j < 36; j++){
			for(int i = 0; i < 3; i++){
				cubes[i + j*3] = new Cube(color, shape);
			}
			if(j % 6 == 0){
				shape = shape.other();
			}
			color = color.other();
		}
	}
	
	public Cube takeCube(){
		return cubes[(int) Math.floor(Math.random() * 108)];
	}
}
