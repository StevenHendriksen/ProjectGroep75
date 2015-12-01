package week3.Hotel;

/**
 * PricedRoom;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.0
 */

public class PricedRoom extends Room implements Bill.Item {
	// ------------------ Instance variables ----------------
	private double rPrice;
	PricedRoom room;
	PricedSafe safe;
	
	public PricedRoom(int no, double roomPrice, double safePrice) {
		// ------------------ Constructor ------------------------
		/**
		 * Concstructs a new PricedSafe and sets the prices;
		 */	
		super(no, new PricedSafe(safePrice));
		rPrice = roomPrice;
	}

	// ------------------ Commands ------------------------
	/**
	 * Returns the total amount per night of the room
	 */
	
	public double getAmount() {
		return rPrice;
	}
	
	/**
	 * Returns the total amount in String format
	 */
	public String toString() {
		return super.toString() + " price per night: " + (rPrice);
	}

}
