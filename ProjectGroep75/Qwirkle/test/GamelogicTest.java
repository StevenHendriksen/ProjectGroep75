package test;

import org.junit.Before;
import org.junit.Test;

import Shared.*;
import server.*;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.Assert.assertEquals;

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
	  assertEquals("0" , gamelogic.moveOkTrade());
  }
  
  @Test
  public void moveOkPutTest(){
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
}
