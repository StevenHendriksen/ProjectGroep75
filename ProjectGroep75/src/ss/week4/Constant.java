package ss.week4;

/**
 * Addition
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Constant implements Function, Integrandable{
	// ------------------ Instance variables ----------------
	private double value;
	private Function derivative;
	private Function integrandable;
	
	// ------------------ Commands -----------------------
	/**
	 * @param value
	 * 		the constant.
	 */
	public Constant(double constant) {
		value = constant;
	}
	
	/**
	 * Returns the constant.
	 */
	public double apply (int apply){
		return value;
	}
	
	/**
	 * Returns the derivative of a constant.
	 * 
	 * @param derivative
	 * 		the derivative of a constant.
	 */
	public Function derivative(){
		derivative = new Constant(0);
		
		return derivative;
	}
	
	/**
	 * Returns the anti-derivative of a constant.
	 * 
	 * @param integrandable
	 * 		the anti-derivative of a constant.
	 */
	public Function integrand(){
		integrandable = new LinearProduct(new Constant(value), new Constant(1));
		return integrandable;
	}
	
	/**
	 * Returns a nice string representation of a constant.
	 */
	public String print() {
		return value + " * x";
	}
}
