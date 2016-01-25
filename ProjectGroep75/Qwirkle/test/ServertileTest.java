package test;

import Server.Player;
import Server.Bag;
import Server.Color;
import Server.Shape;
import Server.Servertile;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServertileTest {
	Color color;
	Shape shape;
	Servertile tile;
	
	@Before
	public void setUp(){
		color = Color.RED;
		shape = Shape.CIRCLE;
		tile = new Servertile(color, shape);
    }
	
	@Test
	public void ServertileintTest(){
		Servertile tile2 = new Servertile(1);
		
		assertTrue(tile.hasColor() == tile2.hasColor());
		assertTrue(tile.hasShape() == tile2.hasShape());
	}
	
	@Test
	public void hasColorTest(){
		assertTrue(tile.hasColor() == Color.RED);
	}
	
	@Test
	public void hasShapeTest(){
		assertTrue(tile.hasShape() == Shape.CIRCLE);
	}
	
	@Test
	public void intToColorTest(){
		tile.intToColor(2);
		this.color = Color.ORANGE;
		
		assertTrue(this.color == tile.hasColor());
	}
	
	@Test
	public void intToShapeTest(){
		tile.intToShape(2);
		this.shape = Shape.CRISSCROSS;
		
		assertTrue(this.shape == tile.hasShape());
	}
	
	@Test
	public void tileToIntTest(){
		this.color = Color.ORANGE;
		assertEquals(1, tile.tileToInt(tile));
	}
}
