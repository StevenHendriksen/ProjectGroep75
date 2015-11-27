package week3.Hotel;

/**
 * Basic item;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.0
 */

public class BasicItem implements Bill.Item{
	// ------------------ Instance variables ----------------
	private double am;
	private String txt;
	Bill bill;
	// ------------------ Constructor ------------------------
	/**
	 * Creates a new Item;
	 * 
	 * @param am
	 *            the amount for the new item;
	 * @param text
	 *            the text for the new item;
	 */	
	public BasicItem(String text, double amount ) {
		bill = new Bill(System.out);
		am = amount;
		txt = text;
	}
	// ------------------ Commands ------------------------
	/**
	 * returns the amount for the item
	 * @param am
	 *           the amount for the item;
	 */
	public double getAmount(){
		return am;
	}
	/**
	 * returns the text for the item
	 * @param txt
	 *           the amount for the item;
	 */
	public String toString(){
		return txt;
	}

}
