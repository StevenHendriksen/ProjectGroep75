package ss.week2;

/**
 * Guests, perhaps with a room.
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Guest {
	
	 // ------------------ Instance variables ----------------
	
	private String name;
	private Room room;
	
	 // ------------------ Constructor ------------------------
	
    /**
     * Creates a <code>Guest</code> with the given name, without a room.
     * @param n is the name of the <code>Guest</code>
     */
	public Guest(java.lang.String n){
		name = n;
	}
	
    // ------------------ Queries --------------------------
	
    /**
     * Returns the name of this <code>Guest</code>.
     */
	
	
	public java.lang.String getName(){
		return name;
	}
	
    /**
     * Returns the current room of the <code>Guest</code>.
     * @return the room of this <code>Guest</code>;
     *         <code>null</code> if this <code>Guest</code> 
     *         does not have a room.
     */
	
	public Room getRoom(){
		return room;
		
	}

    // ------------------ Commands --------------------------
	
    /**
     * Assigns a <code>Room</code> to this <code>Guest</code>.
     * @param r the room that the <code>Guest</code> is renting;
     *        if <code>null</code> is passed, this <code>Guest</code>
     *        does not have a room.
     */
	
	public boolean checkin(Room r){
		if (room == null && r.getGuest() == null){
			room = r;
			r.setGuest(this);
			return true;
		}
		else{
			return false;
		}

	}
	
    /**
     * Remove a <code>Room</code> from this <code>Guest</code>.
     */
	
	public boolean checkout(){
		if (room != null && room.getGuest() != null){
			room.setGuest(null);
			room = null;
			return true;
		}
		else{
			return false;
		}
	}
	

	public java.lang.String toString(){
		String summary = room + " " + name; 
		return summary;
	}
}
