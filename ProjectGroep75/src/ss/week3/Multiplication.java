package ss.week3;

/**
 * Multiplication
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Multiplication implements OperatorWithIdentity{
	// ------------------ Instance variables ----------------
	private int result;
	
	// ------------------ Commands -----------------------
	/**
	 * Multiply the left integer to the right one.
	 * @param result
	 * 		the result of the multiplication.
	 */
	public int operate (int left, int right){
		result = left * right;
		return result;
	}
	
	/**
	 * Shows the identity
	 */
	public int identity (){
		return 1;
	}
}
