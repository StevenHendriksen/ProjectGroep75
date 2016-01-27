package client;

public class Player {
  String name;
  Servertile[] tiles;
  Bag bag;

  /**
   * Constructor of Player, creating the player with a name and filling their hand.
   * @param name (name of player)
   */
  
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
  
  public void setTiles(Servertile[] tiles) {
    this.tiles = tiles;
  }

  public void addTile(Servertile tile) {
    tiles[tiles.length - 1] = tile;
  }
  
  public void changeTiles(Servertile tile, int num) {
    tiles[num] = tile;
  }

  /**
   * Removes a tile from the player's hand.
   * @param tile (tile to be removed)
   */
  
  public void removeTile(Servertile tile) {
    for (int j = 0; j < tiles.length; j++) {
      if (bag.tilesInBag()[j] == tile) {
        bag.tilesInBag()[j] = null;
      }
    }

  }
}
