package test;

import org.junit.Before;
import org.junit.Test;

import Shared.*;
import server.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GamelogicTest {
  Board board;
  Bag bag;
  Gamelogic gamelogic;
  Player stan;
  Player steven;

  @Before
  public void setUp() {
	board = new Board();
    bag = new Bag(108);
    gamelogic = new Gamelogic(board, bag);
    stan = new Player("Stan", null);
    steven = new Player("Steven" , null);
    bag.fillBag();
  }
  
  @Test
  public void moveOkTradeTest(){
	  gamelogic.putPlayer(stan);
	  gamelogic.putPlayer(steven);
	  assertEquals("0", gamelogic.moveOkTrade());
	  
	  gamelogic.nextTurn();
	  
	  assertEquals("0", gamelogic.moveOkTrade());
	  
	  gamelogic.nextTurn();
	  
	  assertEquals("MOVEOK_TRADE", gamelogic.moveOkTrade());
	  
	  for(int i = 0; i < 108; i++){
		  bag.takeTile();
	  }
	  
	  assertEquals("0", gamelogic.moveOkTrade());
  }
  
  @Test
  public void moveOkPutTest(){
	  gamelogic.putPlayer(stan);
	  gamelogic.putPlayer(steven);
	  
	  assertEquals("MOVEOK_PUT" , gamelogic.moveOkPut(1, 0, 0));
	  gamelogic.movePut(0, 0, 1);
	  
	  assertEquals("ERROR Invalid move" , gamelogic.moveOkPut(2, 0, 0));
	  assertEquals("ERROR Invalid move" , gamelogic.moveOkPut(2,5,5));
	  assertEquals("links" , gamelogic.moveOkPut(8, 1, 0));
	  assertEquals("links" , gamelogic.moveOkPut(1, 1, 0));
	  
	  assertEquals("MOVEOK_PUT" , gamelogic.moveOkPut(7, 1, 0));
	  assertEquals("MOVEOK_PUT" , gamelogic.moveOkPut(2, 1, 0));
	  gamelogic.movePut(1, 0, 2);
	  
	  assertEquals("ERROR Invalid move" , gamelogic.moveOkPut(3, 1, 0));
	  assertEquals("links" , gamelogic.moveOkPut(2, 2, 0));
	  assertEquals("links" , gamelogic.moveOkPut(8, 2, 0));
	  assertEquals("MOVEOK_PUT" , gamelogic.moveOkPut(8, 1, 1));
	  
	  assertEquals("MOVEOK_PUT" , gamelogic.moveOkPut(7, 0, 1));
	  gamelogic.movePut(0, 1, 7);

	  assertEquals("MOVEOK_PUT" , gamelogic.moveOkPut(8, 1, 1));
	  assertEquals("onder" , gamelogic.moveOkPut(9, 1, 1));
	  assertEquals("links", gamelogic.moveOkPut(3, 1, 1));
  }
  
  @Test
  public void equalTest(){
	  Tile tile = new Tile(0);
	  Tile tile2 = new Tile(Color.RED, Shape.CIRCLE);
	  Tile tile3 = new Tile(Color.BLUE, Shape.CRISSCROSS);
	  
	  assertTrue(gamelogic.equal(tile, tile2));
	  assertFalse(gamelogic.equal(tile, tile3));
  }
  
  @Test
  public void gameEnd(){
	  gamelogic.putPlayer(stan);
	  gamelogic.putPlayer(steven);
	  
	  assertEquals("", gamelogic.gameEnd());
	  
	  bag.fillBag();
	  
	  gamelogic.movePass(gamelogic.turn());
	  gamelogic.movePass(gamelogic.turn());
	  
	  assertEquals("", gamelogic.gameEnd());
	  
	  for(int i = 0; i < 108; i++){
		  bag.takeTile();
	  }
	  
	  assertEquals("GAMEEND", gamelogic.gameEnd());
	  
	  gamelogic.movePut(0, 0, 0);
	  
	  assertEquals("", gamelogic.gameEnd());
	  
	  gamelogic.movePut(0, 0, gamelogic.turn().getTiles().takeTile().tileToInt());
	  gamelogic.movePut(0, 1, gamelogic.turn().getTiles().takeTile().tileToInt());
	  gamelogic.movePut(0, 2, gamelogic.turn().getTiles().takeTile().tileToInt());
	  gamelogic.movePut(0, 3, gamelogic.turn().getTiles().takeTile().tileToInt());
	  gamelogic.movePut(0, 4, gamelogic.turn().getTiles().takeTile().tileToInt());
	  gamelogic.movePut(0, 5, gamelogic.turn().getTiles().takeTile().tileToInt());
	  
	  assertEquals("GAMEEND", gamelogic.gameEnd());
  }
  
  @Test
  public void movePassTest(){
	  
  }
}