package Server;

public class Bag {
	private Cube[] cubes;
	private Color color;
	private Shape shape;

	public Bag() {

	}

	public static void main(String[] args) {
		Bag bag = new Bag();

		bag.newBag();
	}

	public void newBag() {
		cubes = new Cube[108];
		color = Color.RED;
		shape = Shape.CIRCLE;
		for (int j = 0; j < 36; j++) {
			for (int i = 0; i < 3; i++) {
				cubes[i + j * 3] = new Cube(color, shape);
			}
			if (j % 6 == 0) {
				shape = shape.other();
			}
			color = color.other();
		}
	}

	public Cube takeCube() {
		int random = (int) Math.floor(Math.random() * 108);
		Cube randomcube = cubes[random];

		while (randomcube == null) {
			random = (int) Math.floor(Math.random() * 108);
		}

		randomcube = null;

		return randomcube;
	}

	public void getCube(Cube cube){
		boolean inBag = false;
		for(int i = 0; i < 108; i++){
			if(cubes[i] == cube){
				if(i % 3 == 0 && cubes[i + 1] == null){
					cubes[i + 1] = cube;
				}
				else if(i % 3 == 0 && cubes[i + 2] == null){
					cubes[i + 2] = cube;
				}
				else if(i % 3 == 1 && cubes[i - 1] == null){
					cubes[i - 1] = cube;
				}
				else if(i % 3 == 1 && cubes[i + 1] == null){
					cubes[i + 1] = cube;
				}
				else if(i % 3 == 2 && cubes[i - 2] == null){
					cubes[i - 2] = cube;
				}
				else if(i % 3 == 2 && cubes[i - 1] == null){
					cubes[i - 1] = cube;
				}
				inBag = true;
			}
		}
		if (!inBag){
			for(int i = 0; i < 108; i++){
				if(cubes[i] == null){
					cubes[i] = cube;
				}
			}
		}
	}

	public Bag deepCopy() {
		Bag bag = new Bag();
		for (int i = 0; i < 108; i++) {
			cubes[i] = this.cubes[i];
		}
		return bag;
	}

	public boolean emptyBag() {
		boolean empty = true;

		for (int i = 0; i < 108; i++) {
			if (cubes[i] != null) {
				empty = false;
			}
		}

		return empty;
	}

	public Cube[] cubesInBag() {
		return cubes;
	}
}
