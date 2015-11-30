package week3.Hotel;

/**
 * PricedRoom;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.0
 */

public class PricedRoom extends Room implements week3.Hotel.Bill.Item {
	// ------------------ Instance variables ----------------
	private double rPrice;
	private double sPrice;
	PricedRoom room;
	Safe safe;
	
	public PricedRoom(int no, double roomPrice, double safePrice) {
		// ------------------ Constructor ------------------------
		/**
		 * Concstructs a new PricedSafe and sets the prices;
		 */	
		super(no);
		safe = new PricedSafe(safePrice);
		rPrice = roomPrice;
		sPrice = safePrice;
	}

	// ------------------ Commands ------------------------
	/**
	 * Returns the total amount per night of the room
	 */
	
	public double getAmount() {
		return rPrice + sPrice;
	}
	
	/**
	 * Returns the total amount in String format
	 */
	public String toString() {
		return "Total price: " + (rPrice + sPrice);
	}

}
