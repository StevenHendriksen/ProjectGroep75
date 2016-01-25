package Test;

import org.junit.Before;
import org.junit.Test;

import Server.Serverboard;
import Server.Bag;
import Server.Gamelogic;
import Server.Servertile;
import Server.Color;
import Server.Shape;
import Server.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class GamelogicTest {
  Serverboard board;
  Bag bag;
  Gamelogic gamelogic;
  Servertile tile;
  Servertile tile2;
  Servertile tile3;
  Player player;
  Player player2;

  @Before
  public void setUp() {
    board = new Serverboard();
    bag = new Bag();
    gamelogic = new Gamelogic(board, bag);
    tile = new Servertile(1);
    tile2 = new Servertile(Color.RED, Shape.CIRCLE);
    tile3 = new Servertile(2);
    player = new Player("Stan", bag);
    player2 = new Player("Steven", bag);
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

    assertEquals(tile.hasColor(), board.getTile(0, 0).hasColor());
    assertEquals(tile.hasShape(), board.getTile(0, 0).hasShape());
    assertEquals(1, player.hasScore());

    gamelogic.nextTurn();

    gamelogic.movePut(0, 1, 2);
    assertEquals(tile3.hasColor(), board.getTile(0, 1).hasColor());
    assertEquals(tile3.hasShape(), board.getTile(0, 1).hasShape());
    assertEquals(2, player2.hasScore());
  }

  @Test
  public void scoreTest() {
    gamelogic.putPlayer(player);
    gamelogic.putPlayer(player2);

    assertEquals(player.hasScore(), 0);

    gamelogic.movePut(0, 0, 1);

    assertEquals(player.hasScore(), 1);
  }

  public void moveTradeTest() {
    player.changeTiles(new Servertile(1), 0);
    player.changeTiles(new Servertile(2), 1);
    player.changeTiles(new Servertile(3), 2);
    player.changeTiles(new Servertile(4), 3);
    player.changeTiles(new Servertile(5), 4);
    player.changeTiles(new Servertile(6), 5);

    gamelogic.moveTrade(1);

    for (int i = 0; i < 6; i++) {
      assertFalse(player.hasTiles()[i] == new Servertile(1));
    }
  }
}
