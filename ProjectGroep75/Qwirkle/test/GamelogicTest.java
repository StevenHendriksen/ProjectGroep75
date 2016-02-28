package test;

import org.junit.Before;
import org.junit.Test;

import Shared.*;
import server.Gamelogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GamelogicTest {
  Board board;
  Bag bag;
  Gamelogic gamelogic;
  Tile tile;
  Tile tile2;
  Tile tile3;
  Player player;
  Player player2;

  @Before
  public void setUp() {
    board = new Board();
    bag = new Bag(108);
    gamelogic = new Gamelogic(board, bag);
    tile = new Tile(1);
    tile2 = new Tile(Color.RED, Shape.CIRCLE);
    tile3 = new Tile(2);
    player = new Player("Stan", null);
    player2 = new Player("Steven", null);
  }

  @Test
  public void drawTileTest() {

  }

  @Test
  public void moveOkTradeTest() {
    assertEquals("MOVEOK_TRADE", gamelogic.moveOkTrade());

    for (int i = 0; i < 108; i++) {
      bag.takeTile();
    }

    assertEquals("0", gamelogic.moveOkTrade());
  }

  @Test
  public void equalTest() {
    assertTrue(gamelogic.equal(tile, tile2));
  }

  @Test
  public void gameEndTest() {
    assertEquals("", gamelogic.gameEnd());

    for (int i = 0; i < 108; i++) {
      bag.takeTile();
    }

    assertEquals("GAMEEND", gamelogic.gameEnd());
  }

  @Test
  public void putPlayerTest() {
    Gamelogic gamelogic = new Gamelogic(board, bag);
    gamelogic.putPlayer(player);

    List<Player> players = new ArrayList<Player>();
    players.add(player);

    assertEquals(1, gamelogic.hasPlayers().size());
    assertEquals(players, gamelogic.hasPlayers());
  }

  @Test
  public void gameStartTest() {
    gamelogic.putPlayer(player);

    assertFalse(gamelogic.gameStart(1));

    gamelogic.putPlayer(player2);

    assertTrue(gamelogic.gameStart(2));
    assertFalse(gamelogic.gameStart(3));
  }

  @Test
  public void turnTest() {
    gamelogic.putPlayer(player);
    gamelogic.putPlayer(player2);

    assertEquals(player, gamelogic.turn());
  }

  @Test
  public void nextTurnTest() {
    gamelogic.putPlayer(player);
    gamelogic.putPlayer(player2);

    gamelogic.nextTurn();

    assertEquals(player2, gamelogic.turn());

    gamelogic.nextTurn();

    assertEquals(player, gamelogic.turn());
  }

  @Test
  public void movePutTest() {
    gamelogic.putPlayer(player);
    gamelogic.putPlayer(player2);

    gamelogic.movePut(0, 0, 1);

    assertEquals(tile.getColor(), board.getTile(0, 0).getColor());
    assertEquals(tile.getShape(), board.getTile(0, 0).getShape());
    gamelogic.score(0, 0, 1, player);
    assertEquals(1, player.hasScore());

    gamelogic.nextTurn();

    gamelogic.score(0, 1, 2, player2);
    gamelogic.movePut(0, 1, 2);
    assertEquals(tile3.getColor(), board.getTile(0, 1).getColor());
    assertEquals(tile3.getShape(), board.getTile(0, 1).getShape());
    assertEquals(2, player2.hasScore());
  }

  @Test
  public void scoreTest() {
    gamelogic.putPlayer(player);
    gamelogic.putPlayer(player2);

    gamelogic.movePut(0, 0, 1);
    gamelogic.movePut(0, 2, 3);
    gamelogic.movePut(0, 1, 2);
    gamelogic.score(0, 1, 2, player);
    assertEquals("Goes wrong because the moves aren't valid",player.hasScore(), 3);
    gamelogic.nextTurn();
    gamelogic.movePut(1, 0, 4);
    gamelogic.score(1, 0, 4, player2);
    assertEquals(player2.hasScore(), 2);

  }

  @Test
  public void moveTradeTest() {
    gamelogic.putPlayer(player);
    gamelogic.putPlayer(player2);
    player.changeTiles(new Tile(1), 0);
    player.changeTiles(new Tile(2), 1);
    player.changeTiles(new Tile(3), 2);
    player.changeTiles(new Tile(4), 3);
    player.changeTiles(new Tile(5), 4);
    player.changeTiles(new Tile(6), 5);
    System.out.println(player.hasTiles()[2]);
    gamelogic.moveTrade(5);
    assertTrue(gamelogic.equal(player.hasTiles()[5], new Tile(6)));

    for (int i = 1; i < 6; i++) {
      System.out.println("player has tile: " + player.hasTiles()[i]);
      assertFalse(gamelogic.equal(player.hasTiles()[i], new Tile(1)));

    }
  }
}