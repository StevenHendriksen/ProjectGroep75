package Test;

import org.junit.Before;
import org.junit.Test;

import Server.Serverboard;
import Server.Bag;
import Server.Gamelogic;

import static org.junit.Assert.assertEquals;

public class GamelogicTest {
   Serverboard board;
   Bag bag;
   Gamelogic gamelogic;
	
   @Before
	public void setUp(){
    	Serverboard board = new Serverboard();
    	Bag bag = new Bag();
    	Gamelogic gamelogic = new Gamelogic(board, bag);
    }
	
   @Test
	public void moveOkTradeTest(){
		for(int i = 0; i < 108; i++){
			bag.takeTile();
		}
		
		assertEquals("MOVEOK_TRADE", gamelogic.moveOkTrade());
	}
}
