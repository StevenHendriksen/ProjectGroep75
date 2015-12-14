package ss.week4;

/**
 * Polynomial
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Polynomial implements Function {
	// ------------------ Instance variables ----------------
	private LinearProduct[] array;

	// ------------------ Commands -----------------------
	/**
	 * @param array
	 *            a list of LinearProducts with as and pows.
	 */
	public Polynomial(double[] as, double[] pows) {
		assert as.length == pows.length;
		array = new LinearProduct[as.length];
		for (int i = 0; i < as.length; i++) {
			array[i] = new LinearProduct(new Constant(as[i]), new Constant(pows[i]));
		}
	}

	/**
	 * @param sum
	 *            the sum of all LinearProducts;
	 * 
	 *            Returns the sum of all LinearProducts.
	 */
	public double apply(int apply) {
		double sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum = sum + array[i].apply(apply);
		}
		return sum;
	}

	/**
	 * Returns the derivative of a polynomial.
	 * 
	 * @param pol
	 *            the derivative of a polynomial.
	 */
	public Polynomial derivative() {
		double[] as = new double[array.length];
		double[] pows = new double[array.length];

		for (int i = 0; i < array.length; i++) {
			as[i] = ((Exponent) (array[i].getFunction())).getPower() * array[i].getConstant().apply(0);
			pows[i] = ((Exponent) (array[i].getFunction())).getPower() - 1;
		}

		Polynomial pol = new Polynomial(as, pows);
		return pol;
	}

	/**
	 * Returns the anti-derivative of a polynomial.
	 * 
	 * @param integrandable
	 *            the anti-derivative of a polynomial.
	 */
	public Function integrand() {
		double[] as2 = new double[array.length];
		double[] pows2 = new double[array.length];
		for (int i = 0; i < array.length; i++) {
			as2[i] = array[i].getConstant().apply(0) / (((Exponent) (array[i].getFunction())).getPower() + 1);
			pows2[i] = ((Exponent) (array[i].getFunction())).getPower() + 1;
		}
		return new Polynomial(as2, pows2);
	}

	/**
	 * Returns a nice string representation of an exponent.
	 */
	public String print() {
		String print = "\n";
		for (int i = 0; i < array.length; i = i + 2) {
			print = print + array[i].getFunction().apply(0) + "^" + array[i + 1] + "\n";
		}
		return "";
	}
}