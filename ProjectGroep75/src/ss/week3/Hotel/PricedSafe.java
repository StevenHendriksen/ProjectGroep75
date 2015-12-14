package ss.week3.Hotel;

/**
 * PricedSafe
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.0
 */

public class PricedSafe extends ss.week3.Hotel.Safe implements Bill.Item {
	private double sum;

	public PricedSafe(double price) {
		sum = price;
	}
	// ------------------ Commands ------------------------
	/**
	 * Returns the total amount per night of the Safe
	 */
	public double getAmount() {
		return sum;
	}

	/**
	 * Returns the total amount in String format
	 */
	
	public String toString() {
		return super.toString() + "Prijs van de Safe is: €" + sum;
	}

}