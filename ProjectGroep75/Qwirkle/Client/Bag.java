package Client;

public class Bag {
	private Tile[] tiles = {};

	public Bag() {
		newBag();
	}

	public static void main(String[] args) {
		Bag bag = new Bag();

		bag.newBag();
	}

	public void newBag() {
		tiles = new Tile[108];
		for (int j = 0; j < 36; j++) {
			for (int i = 0; i < 3; i++) {
				tiles[i + j * 3] = new Tile(j + 1);
			}
		}
	}

	public Tile takeTile() {
		int random = (int) Math.floor(Math.random() * 108);
		Tile randomtile = tiles[random];

		while (randomtile == null) {
			random = (int) Math.floor(Math.random() * 108);
		}

		tiles[random] = null;

		return randomtile;
	}

	public void getTile(Tile tile) {
		boolean inBag = false;
		for (int i = 0; i < 108; i++) {
			if (tiles[i] == tile) {
				if (i % 3 == 0 && tiles[i + 1] == null) {
					tiles[i + 1] = tile;
				} else if (i % 3 == 0 && tiles[i + 2] == null) {
					tiles[i + 2] = tile;
				} else if (i % 3 == 1 && tiles[i - 1] == null) {
					tiles[i - 1] = tile;
				} else if (i % 3 == 1 && tiles[i + 1] == null) {
					tiles[i + 1] = tile;
				} else if (i % 3 == 2 && tiles[i - 2] == null) {
					tiles[i - 2] = tile;
				} else if (i % 3 == 2 && tiles[i - 1] == null) {
					tiles[i - 1] = tile;
				}
				inBag = true;
			}
		}
		if (!inBag) {
			for (int i = 0; i < 108; i++) {
				if (tiles[i] == null) {
					tiles[i] = tile;
				}
			}
		}
	}

	public Bag deepCopy() {
		Bag bag = new Bag();
		for (int i = 0; i < 108; i++) {
			tiles[i] = this.tiles[i];
		}
		return bag;
	}

	public boolean emptyBag() {
		boolean empty = true;

		for (int i = 0; i < 108; i++) {
			if (tiles[i] != null) {
				empty = false;
			}
		}

		return empty;
	}

	public Tile[] tilesInBag() {
		return tiles;
	}
}
