package Shared;

import java.util.ArrayList;
import java.util.List;

import server.Servertile;

/**
 * Client Bag
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Bag {
  private Tile[] tiles = {};

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
    bag.tilesInBag();
    int count = 0;
    int count2 = 0;
    for(int i = 0; i < 108; i++){
    System.out.println(bag.tilesInBag()[i]);
    if(bag.tilesInBag()[i] == null){
      count++;
    } else{
      count2++;
    }
    }
    System.out.println("count: " + count);
    System.out.println("count2: " + count2);
  }

  /**
   * newBag, fills the bag with all the tiles.
   */

  public void fillBag(int tileNumber) {
    tiles = new Tile[tileNumber];
    for (int j = 0; j < 3; j++) {
      for (int i = 1; i < 36; i++) {
        tiles[j*36 + i] = new Tile(i);
        System.out.println(j + " " + i);
        System.out.println("tiles:" + tiles[j*3 + i]);
      }
    }
  }

  /**
   * Gives a random tile from the bag.
   * 
   * @return (random tile from bag)
   */

  public Servertile takeTile() {    
	    List<Servertile> list = new ArrayList<Servertile>();
	    
	    for(int i = 0; i < 108; i++){
	    	if (tiles[i] != null){
	    		list.add(tiles[i]);
	    	}
	    }
	    
	    int random = (int) Math.floor(Math.random() * list.size());
	    
	    return list.get(random);
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

  public Tile[] tilesInBag() {
    return tiles;
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
