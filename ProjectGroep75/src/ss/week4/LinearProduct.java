package ss.week4;

public class LinearProduct extends Product{
	private Constant constantc;
	private Exponent functiong;
	private LinearProduct product1;
	
	public LinearProduct(Constant n, Exponent g){
		super(n, g);
		constantc = n;
		functiong = g;
	}
	
	public Function derivative(){
		product1 = new LinearProduct(new Constant(constantc.apply(0) * (functiong.apply(0))), new Exponent(functiong.apply(0) - 1.0));	
		return product1;
	}
	
	public Constant getConstant() {
		return constantc;
	}
	
	public Function getFunction() {
		return functiong;
	}
	public Function integrand(){
		product1 = new LinearProduct(new Constant(constantc.apply(0) / (functiong.apply(0) + 1)), new Exponent(functiong.apply(0) + 1.0));	
		return product1;
	}
	
	public double apply(int apply){
		double result = constantc.apply(0)  * Math.pow(apply, functiong.apply(1)) ;
		return result;
	}
	
	public String print(){
		String print = constantc.apply(0) + " * x^" + functiong.apply(0);
		return print;
	}
}
