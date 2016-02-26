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

import java.io.PrintWriter;
import java.net.Socket;

//af
public class PlayerTest {
  Player player;
  Bag bag;

  @Before
  public void setUp() {
    bag = new Bag();
    player = new Player("Stan", bag, null);
  }

  @Test
  public void hasNameTest() {
    assertEquals(player.hasName(), "Stan");
  }

  @Test
  public void getTilesAndhasTilesTest() {
    boolean test1 = true;

    for (int i = 0; i < 6; i++) {
      if (player.hasTiles()[i] != null) {
        test1 = false;
      }
    }

    assertTrue(test1);

    player.getTiles();

    for (int j = 0; j < 6; j++) {
      if (player.hasTiles()[j] == null) {
        test1 = false;
      }
    }

    assertTrue(test1);
  }

  @Test
  public void changeTilesTest() {
    player.changeTiles(new Tile(1), 0);

    assertTrue(player.hasTiles()[0].hasColor() == Color.RED);
    assertTrue(player.hasTiles()[0].hasShape() == Shape.CIRCLE);
  }

  @Test
  public void hasScoreAndChangeScoreTest() {
    assertTrue(player.hasScore() == 0);

    player.changeScore(5);

    assertTrue(player.hasScore() == 5);
  }
}
