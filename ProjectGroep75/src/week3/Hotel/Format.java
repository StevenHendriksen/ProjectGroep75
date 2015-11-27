package week3.Hotel;

/**
 * Format;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.0
 */

public class Format {
	// ------------------ Instance variables ----------------
	String print;
	Format format;
	// ------------------ Commands ------------------------
	/**
	 * returns the well formatted text and amount
	 */
	public static String printLine(String text, double amount) {
		if(text.length() < 50 && amount < 10000){
		return String.format("%-50s%8.2f%n",text,amount);
		}
		else
			return String.format("%-50s%8.2f%n","De tekst was te lang",amount);
	}
}
