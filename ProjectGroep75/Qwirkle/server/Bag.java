package server;

/**
 * Client Bag
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Bag {
  private Servertile[] tiles = {};

  public Bag() {
    newBag();
  }

  /**
   * Main, used for testing purposes.
   * 
   * @param args
   *          (args provided in run config, not used in this case)
   */

  public static void main(String[] args) {
    Bag bag = new Bag();
    bag.tilesInBag();
  }

  /**
   * newBag, fills the bag with all the tiles.
   */

  public void newBag() {
    tiles = new Servertile[108];
    for (int j = 0; j < 36; j++) {
      for (int i = 0; i < 3; i++) {
        tiles[i + j * 3] = new Servertile(j + 1);
      }
    }
  }

  /**
   * Gives a random tile from the bag.
   * 
   * @return (random tile from bag)
   */

  public Servertile takeTile() {
    int random = (int) Math.floor(Math.random() * 108);
    Servertile randomtile = tiles[random];

    while (randomtile == null) {
      random = (int) Math.floor(Math.random() * 108);
    }

    tiles[random] = null;

    return randomtile;
  }
  /**
   * Puts a tile with int into the bag.
   * @param tile (the int of the tile to be added)
   */
  
  public void putTile(int tile) {
    for (int i = 0; i < 108; i++) {
      if (tiles[i] == null) {
        tiles[i] = new Servertile(tile);
        return;
      }
    }
  }

  /**
   * Checks if the tile is in the bag.
   * 
   * @param tile
   *          (tile to check if in bag)
   * @return (boolean)
   */

  public boolean getTile(Servertile tile) {
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
    return inBag;
  }
  
  /**
   * Used to check if the bag is empty.
   * @return (boolean of whether the bag is empty)
   */

  public boolean emptyBag() {
    boolean empty = true;

    for (int i = 0; i < 108; i++) {
      if (tiles[i] != null) {
        empty = false;
      }
    }

    return empty;
  }

  public Servertile[] tilesInBag() {
    return tiles;
  }
}
