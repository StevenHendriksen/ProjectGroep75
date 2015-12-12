package ss.week4;

public class Exponent implements Function{
	private double n;
	
	public Exponent (double n){
		this.n = n;
	}
	
	public double apply (int apply){
		return n;
	}
	
	public Function derivative(){
		Exponent exp = new Exponent(n-1);
		Constant constant = new Constant(n);
		LinearProduct product = new LinearProduct(constant, exp);
		
		return product;
	}
	
	public double getPower(){
		return  n;
	}
	public Function integrand(){
		return new Exponent(n-1);
	}
	public String print(){
		return "nope";
	}
}
