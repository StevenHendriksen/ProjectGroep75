package Week2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoomTest {
    private Guest guest;
    private Room room;
	private Password pass;

    @Before
    public void setUp() {
        guest = new Guest("Jip");
        room = new Room(101);
        pass = new Password();
    }

    @Test
    public void testSetUp() {
        assertEquals(101, room.getNumber());
    }

    @Test
    public void testSetGuest() {
        room.setGuest(guest);
        assertEquals(guest, room.getGuest());
    }
    
    @Test
    public void testSafe() {
    	assertNotEquals(room.getSafe(), null);
        assertFalse("Safe openable with correct password", room.getSafe().open(pass.INITIAL));
}
}
