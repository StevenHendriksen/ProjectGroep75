package ss.week4;

/**
 * Sum
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Sum implements Function {
	// ------------------ Instance variables ----------------
	private Function functionf;
	private Function functiong;
	private Sum derivative;

	// ------------------ Commands -----------------------
	/**
	 * @param functionf
	 *            the function f.
	 * @param functiong
	 *            the function g.
	 */
	public Sum(Function f, Function g) {
		functionf = f;
		functiong = g;
	}

	/**
	 * Returns the function f plus the function g
	 */
	public double apply(int apply) {
		return functionf.apply(apply) + functiong.apply(apply);
	}

	/**
	 * Returns the derivative of a sum of two functions.
	 * 
	 * @param derivative
	 *            the derivative of a sum of two functions.
	 */
	public Function derivative() {
		derivative = new Sum(functionf.derivative(), functiong.derivative());

		return derivative;
	}

	/**
	 * Returns the anti-derivative of a sum of two functions.
	 * 
	 * @param integrandable
	 *            the anti-derivative of a sum of two functions.
	 */
	public Function integrand() {
		return new Sum(new Exponent(functiong.apply(0)), new Exponent(functionf.apply(0)));
	}

	/**
	 * Returns a nice string representation of a sum.
	 */
	public String print() {
		return functionf.print() + " + " + functiong.print();
	}
}
