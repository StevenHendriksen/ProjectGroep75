package test;

import org.junit.Before;
import org.junit.Test;

import Shared.*;

import static org.junit.Assert.assertTrue;

public class TileTest {
  Color color;
  Shape shape;
  Tile tile;

  @Before
  public void setUp() {
    color = Color.RED;
    shape = Shape.CIRCLE;
    tile = new Tile(color, shape);
  }

  @Test
  public void TileintTest() {
    Tile tile2 = new Tile(0);
    
    assertTrue(tile.getColor() == tile2.getColor());
    assertTrue(tile.getShape() == tile2.getShape());
  }

  @Test
  public void hasColorTest() {
    assertTrue(tile.getColor() == Color.RED);
  }

  @Test
  public void hasShapeTest() {
    assertTrue(tile.getShape() == Shape.CIRCLE);
  }
  /*
  @Test
  public void intToColorTest() {
    tile.getColor().intToColor(2);
    this.color = Color.ORANGE;

    assertTrue(this.color == tile.getColor());
  }

  @Test
  public void intToShapeTest() {
    tile.getShape().intToShape(2);
    this.shape = Shape.CRISSCROSS;

    assertTrue(this.shape == tile.getShape());
  }

  @Test
  public void tileToIntTest() {
    this.color = Color.ORANGE;
    assertEquals(1, tile.tileToInt(tile));
  }
  */
}
