package ss.week4;

/**
 * Product
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Product implements Function{
	// ------------------ Instance variables ----------------
	private Function functionf;
	private Function functiong;
	private Product product1;
	private Product product2;
	private Sum sum;
	
	// ------------------ Commands -----------------------
	/**
	 * @param functionf
	 *            the function f.
	 * @param functiong
	 *            the function g.
	 */
	public Product(Function f, Function g){
		functionf = f;
		functiong = g;
	}
	
	/**
	 * Returns the function f times the function g
	 */
	public double apply(int apply){
		return functionf.apply(apply) * functiong.apply(apply);
	}
	
	/**
	 * Returns the derivative of a product of two functions.
	 * 
	 * @param derivative
	 *            the derivative of a product of two functions.
	 */
	public Function derivative(){
		product1 = new Product(functionf.derivative(), functiong);
		product2 = new Product(functiong.derivative(), functionf);
		sum = new Sum(product1, product2);
		return sum;
	}
	
	/**
	 * Returns the anti-derivative of a product of two functions.
	 * 
	 * @param integrandable
	 *            the anti-derivative of a product of two functions.
	 */
	public Function integrand(){
		return null;
	}
	
	/**
	 * Returns a nice string representation of a product.
	 */
	public String print(){
		return functionf.print() + " * " + functiong.print();
	}
}
