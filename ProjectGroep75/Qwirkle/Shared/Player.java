package Shared;

public class Player {
  String name;
  Tile[] tiles;
  Bag bag;

  /**
   * Constructor of Player, creating the player with a name and filling their hand.
   * @param name (name of player)
   */
  
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
  
  public void setTiles(Tile[] tiles) {
    this.tiles = tiles;
  }

  public void addTile(Tile tile) {
    tiles[tiles.length - 1] = tile;
  }
  
  public void changeTiles(Tile tile, int num) {
    tiles[num] = tile;
  }

  /**
   * Removes a tile from the player's hand.
   * @param tile (tile to be removed)
   */
  
  public void removeTile(Tile tile) {
    for (int j = 0; j < tiles.length; j++) {
      if (bag.tilesInBag()[j] == tile) {
        bag.tilesInBag()[j] = null;
      }
    }

  }
}
