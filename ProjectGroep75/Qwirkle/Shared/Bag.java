package Shared;

import java.util.ArrayList;
import java.util.List;

/**
 * Client Bag
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Bag {
  private Tile[] tiles = {};
  int bagSize;
  List<Integer> list = new ArrayList<Integer>();

  public Bag(int numberOfTiles) {
    fillBag(numberOfTiles);
  }

  /**
   * Main, used for testing purposes.
   * 
   * @param args
   *          (args provided in run config, not used in this case)
   */

  public static void main(String[] args) {
    Bag bag = new Bag(108);
    bag.fillBag(108);
    bag.getBag();
    int count = 0;
    int count2 = 0;
    for (int i = 0; i < 108; i++) {
      if (bag.getBag()[i] == null) {
        count++;
      } else {
        count2++;
      }
    }
    System.out.println("Null count: " + count);
    System.out.println("Non-Null count: " + count2);
  }

  public void printBag() {
    for (int i = 0; i < 108; i++) {
      System.out.println(getBag()[i]);
    }
  }

  /**
   * newBag, fills the bag with all the tiles.
   */

  public void fillBag(int tileNumber) {
    tiles = new Tile[tileNumber];
    bagSize = tileNumber;
    for (int j = 0; j < 3; j++) {
      for (int i = 0; i < 36; i++) {
        tiles[j * 36 + i] = new Tile(i);
        // System.out.println(j + " " + i + " " + (j*36 + i));
        // System.out.println("tiles:" + tiles[j*36 + i]);
      }
    }
  }

  /**
   * Gives a random tile from the bag.
   * 
   * @return (random tile from bag)
   */

  public Tile takeTile() {
    
    while (true) {
      int random = (int) Math.floor(Math.random() * (getBag().length));

      if(tiles[random] instanceof Tile){
        Tile tile = tiles[random];
        tiles[random] = null;
        if(!list.contains(random)){
          list.add(random);
        }
        return tile;
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

  public boolean getTile(Tile tile) {
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
   * 
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

  public Tile[] getBag() {
    return tiles;
  }
  
  public int emptySpots() {
    int count = 0;
    for (int i = 0; i < 108; i++) {
      if (getBag()[i] == null) {
        count++;
      }
    }
    return count;
  }

  public void putTile(int tile) {
    for (int i = 0; i < 108; i++) {
      if (tiles[i] == null) {
        tiles[i] = new Tile(tile);
        return;
      }
    }
  }
}
