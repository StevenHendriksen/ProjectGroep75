package ss.week2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SafeTest {
	private Safe safe;
	@Before
	public void setUp() throws Exception {
		safe = new Safe();
	}
	@Test
	public void testActivate() {
		assertTrue(safe.activate(Password.INITIAL));
		assertFalse(safe.activate("no nono"));
		assertFalse(safe.activate("12345"));
	}
	@Test
	public void testOpen() {
		safe.activate(Password.INITIAL);
		assertTrue(safe.open(Password.INITIAL));
		safe.close();
		assertFalse(safe.open("no nono"));
		safe.close();
		assertFalse(safe.open("12345"));
	}
	@Test
	public void testClose(){
		safe.activate(Password.INITIAL);
		safe.open(Password.INITIAL);
		safe.close();
		assertFalse(safe.isOpen());
		assertTrue(safe.isActive());		
	}
	@Test
	public void testDeactivate(){
		safe.activate(Password.INITIAL);
		safe.open(Password.INITIAL);
		safe.deactivate();
		assertFalse(safe.isActive());
		assertFalse(safe.isOpen());
	}
}
