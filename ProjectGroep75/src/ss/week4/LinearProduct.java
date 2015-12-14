package ss.week4;

/**
 * Linearproduct
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class LinearProduct extends Product {
	// ------------------ Instance variables ----------------
	private Constant constantc;
	private Constant functiong;
	private LinearProduct product1;

	// ------------------ Commands -----------------------
	/**
	 * @param constantc
	 *            the constant n.
	 * @param functiong
	 *            the constant g.
	 */
	public LinearProduct(Constant n, Constant g) {
		super(n, g);
		constantc = n;
		functiong = g;
	}

	/**
	 * Returns the derivative of a product of two constants.
	 * 
	 * @param derivative
	 *            the derivative of a product of two constants.
	 */
	public Function derivative() {
		product1 = new LinearProduct(new Constant(0), new Constant(0));
		return product1;
	}

	/**
	 * Returns constant c.
	 */
	public Constant getConstant() {
		return constantc;
	}

	/**
	 * Returns the function g
	 */
	public Function getFunction() {
		return functiong;
	}

	/**
	 * Returns the anti-derivative of a product of two constants.
	 * 
	 * @param integrandable
	 *            the anti-derivative of a product of two constants.
	 */
	public Function integrand() {
		product1 = new LinearProduct(new Constant(constantc.apply(0) / (functiong.apply(0) + 1)),
				new Constant(functiong.apply(0) + 1.0));
		return product1;
	}

	/**
	 * Returns the product of the two constants.
	 * 
	 * @param result
	 *            the product of the two constants.
	 */
	public double apply(int apply) {
		double result = constantc.apply(apply) * functiong.apply(apply);
		return result;
	}

	/**
	 * Returns a nice string representation of a linear product.
	 */
	public String print() {
		String print = constantc.apply(0) + " * x^" + functiong.apply(0);
		return print;
	}
}
