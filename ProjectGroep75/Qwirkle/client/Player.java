package client;

public class Player {
  String name;
  Servertile[] tiles;
  Bag bag;

  public Player(String name) {
    this.name = name;
    bag = new Bag();
    tiles = new Servertile[6];
    for (int i = 0; i < 6; i++) {
      tiles[i] = bag.takeTile();
    }
  }

  public String hasName() {
    return name;
  }

  public Servertile[] hasTiles() {
    return tiles;
  }

  public void addTile(Servertile tile) {
    tiles[tiles.length] = tile;
  }

  public void removeTile(Servertile tile) {
    for (int j = 0; j < tiles.length; j++) {
      if (bag.tilesInBag()[j] == tile) {
        bag.tilesInBag()[j] = null;
      }
    }

  }
}
