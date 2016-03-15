package test;

import org.junit.Before;
import org.junit.Test;

import Shared.*;

import static org.junit.Assert.assertTrue;

public class BagTest {
  Bag bag;

  @Before
  public void setUp() {
    bag = new Bag(108);
    bag.fillBag();
  }
  
/*
  @Test
  public void newBagTest() {
  }
*/
  
  @Test
  public void takeTileTest() {
    Tile tile = bag.takeTile();
    System.out.println("empty spots:" + bag.emptySpots());
    assertTrue(bag.emptySpots() == 1);
    assertTrue(tile != null);
  }

  @Test
  public void putTileTest() {
    for (int i = 0; i < 108; i++) {
      bag.takeTile();
    }
    bag.putTile(1);
    bag.putTile(2);
    bag.putTile(3);
    bag.putTile(4);
    bag.putTile(5);
    bag.putTile(6);
    assertTrue(bag.emptySpots() == 102);
  }
 
  
  @Test
  public void fillBag() {
    assertTrue(bag.emptySpots() == 0);
    
    bag = new Bag(6);
    bag.fillBag();
    bag.printBag();
    assertTrue(bag.emptySpots() == 0);
  }

  @Test
  public void emptyBagTest() {
    for (int i = 0; i < 108; i++) {
      bag.takeTile();
    }
    assertTrue(bag.emptySpots() == 108);
  }
}
