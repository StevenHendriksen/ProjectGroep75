package ss.week4;

/**
 * Exponent
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Exponent implements Function {
	// ------------------ Instance variables ----------------
	private double n;
	
	// ------------------ Commands -----------------------
	/**
	 * @param n
	 *            the exponent.
	 */
	public Exponent(double n) {
		this.n = n;
	}

	/**
	 * Returns the exponent.
	 */
	public double apply(int apply) {
		return n;
	}

	/**
	 * Returns the derivative of a product of an exponent.
	 * 
	 * @param derivative
	 *            the derivative of a product of an exponent.
	 */
	public Function derivative() {
		Constant exp = new Constant(n + 1);
		Constant constant = new Constant(n);
		LinearProduct product = new LinearProduct(constant, exp);

		return product;
	}

	/**
	 * Returns the exponent.
	 */
	public double getPower() {
		return n;
	}

	/**
	 * Returns the anti-derivative of an exponent.
	 */
	public Function integrand() {
		return new Exponent(n - 1);
	}

	/**
	 * Returns a nice string representation of an exponent.
	 */
	public String print() {
		return "x^" + n;
	}
}