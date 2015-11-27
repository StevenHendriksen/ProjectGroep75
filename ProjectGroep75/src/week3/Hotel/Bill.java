package week3.Hotel;

/**
 * Bill;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.0
 */

public class Bill {
	// ------------------ Interface ----------------
	/**
	 * The Interface Item 
	*/
	public interface Item {
		public double getAmount();
	}
	// ------------------ Instance variables ----------------
	private double sum;
	java.io.PrintStream PS;
	Format format;
	String text;
	String txt = "";
	// ------------------ Constructor ------------------------
	/**
	 * Selects the TheOutStream;
	 * 
	 * @param PS
	 *            The output stream;
	 */	
	public Bill(java.io.PrintStream theOutStream) {
		PS = theOutStream;
	}
	// ------------------ Commands ------------------------
	/**
	 * Prints the Total of the bill and what costed how much;
	 */
	public void close() {
		PS.println("Totaal: " + sum);
		PS.println(txt);
	}
	/**
	 * Returns the sum of the bill.
	 * 
	 * @param sum
	 *            the sum of the bill;
	 */
	
	public double getSum() {
		return sum;
	}
	/**
	 * New item creates a new item with the specified text/amount;
	 * 
	 * @param text
	 *            formatted String + double
	 * @param txt
	 * 			  The printed text for the summarize of the bill
	 */
	public void newItem(Bill.Item item) {
		sum = sum + item.getAmount();
		text = Format.printLine(item.toString(), item.getAmount());
		txt = txt +  text;
	}

}
