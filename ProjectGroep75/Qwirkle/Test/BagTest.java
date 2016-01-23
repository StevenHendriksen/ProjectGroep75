package Test;

import Server.Bag;
import Server.Servertile;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BagTest {
	Bag bag;
	
	@Before
	public void setUp(){
    	Bag bag = new Bag();
    }
	
	@Test
	public void newBagTest(){
		
	}
	
	@Test
	public void takeTile(){
		Servertile tile = bag.takeTile();
		
		for(int i = 0; i < 108; i++){
			if(tile == bag.tilesInBag()[i]){
				assertEquals(tile, bag.tilesInBag()[i]);
			}
		}
	}
}
