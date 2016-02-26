package test;

import org.junit.Before;
import org.junit.Test;

import server.Bag;
import server.Color;
import server.Player;
import server.Tile;
import server.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServertileTest {
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
  public void ServertileintTest() {
    Tile tile2 = new Tile(1);

    assertTrue(tile.hasColor() == tile2.hasColor());
    assertTrue(tile.hasShape() == tile2.hasShape());
  }

  @Test
  public void hasColorTest() {
    assertTrue(tile.hasColor() == Color.RED);
  }

  @Test
  public void hasShapeTest() {
    assertTrue(tile.hasShape() == Shape.CIRCLE);
  }

  @Test
  public void intToColorTest() {
    tile.intToColor(2);
    this.color = Color.ORANGE;

    assertTrue(this.color == tile.hasColor());
  }

  @Test
  public void intToShapeTest() {
    tile.intToShape(2);
    this.shape = Shape.CRISSCROSS;

    assertTrue(this.shape == tile.hasShape());
  }

  @Test
  public void tileToIntTest() {
    this.color = Color.ORANGE;
    assertEquals(1, tile.tileToInt(tile));
  }
}
