package client;

public class Player {
  String name;
  Tile[] tiles;
  Bag bag;

  public Player(String name) {
    this.name = name;
    bag = new Bag();
    tiles = new Tile[6];
    for (int i = 0; i < 6; i++) {
      tiles[i] = bag.takeTile();
    }
  }

  public String hasName() {
    return name;
  }

  public Tile[] hasTiles() {
    return tiles;
  }

  public void addTile(Tile tile) {
    tiles[tiles.length] = tile;
  }

  public void removeTile(Tile tile) {
    for (int j = 0; j < tiles.length; j++) {
      if (bag.tilesInBag()[j] == tile) {
        bag.tilesInBag()[j] = null;
      }
    }

  }
}
