package ss.week3;

/**
 * Addition
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Addition implements OperatorWithIdentity {
	// ------------------ Instance variables ----------------
	private int result;

	// ------------------ Commands -----------------------
		/**
		 * Adds the left integer to the right one.
		 * @param result
		 * 		the result of the addition.
		 */
	public int operate (int left, int right){
		result = left + right;
		return result;		
	}
	
	/**
	 * Shows the identity
	 */
	public int identity (){
		return 0;
	}
}