package ss.week4;

/**
 * Function;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.0
 */

public interface Function {	
	/**
	 * Executes the function to an argument of type double.
	 */
	public double apply (int apply);
	
	/**
	 * Returns the Function object that is the derivative of the current object.
	 */
	public Function derivative ();
	
	/**
	 * Returns a nice string representation of the function.
	 */
	public String toString();
	
	public String print();
	
}
