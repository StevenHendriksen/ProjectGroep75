package test;

import org.junit.Before;
import org.junit.Test;

import Shared.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BagTest {
  Bag bag;

  @Before
  public void setUp() {
    bag = new Bag(108);
    bag.fillBag(108);
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
    boolean result = false;

    for (int i = 0; i < 108; i++) {
      bag.takeTile();
    }

    bag.putTile(1);

    for (int i = 0; i < 108; i++) {
      if (bag.getBag()[i].getColor() == Color.RED 
          && bag.getBag()[i].getShape() == Shape.CIRCLE) {
        result = true;
        break;
      }
    }

    assertTrue(result);
  }

  @Test
  public void emptyBagTest() {
    for (int i = 0; i < 108; i++) {
      bag.takeTile();
    }
    assertTrue(bag.emptyBag());
  }
}
