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
    bag = new Bag();
  }

  @Test
  public void newBagTest() {
  }

  @Test
  public void takeTileTest() {
    Tile tile = bag.takeTile();

    assertFalse(tile == null);
  }

  @Test
  public void putTileTest() {
    boolean result = false;

    for (int i = 0; i < 108; i++) {
      bag.takeTile();
    }

    bag.putTile(1);

    for (int i = 0; i < 108; i++) {
      if (bag.tilesInBag()[i].hasColor() == Color.RED 
          && bag.tilesInBag()[i].hasShape() == Shape.CIRCLE) {
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
