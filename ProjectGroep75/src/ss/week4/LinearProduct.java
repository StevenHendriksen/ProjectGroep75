package ss.week4;

public class LinearProduct extends Product{
	private Constant constantc;
	private Constant functiong;
	private LinearProduct product1;
	
	public LinearProduct(Constant n, Constant g){
		super(n, g);
		constantc = n;
		functiong = g;
	}
	
	public Function derivative(){
		product1 = new LinearProduct(new Constant(0), new Constant(0));	
		return product1;
	}
	
	public Constant getConstant() {
		return constantc;
	}
	
	public Function getFunction() {
		return functiong;
	}
	public Function integrand(){
		product1 = new LinearProduct(new Constant(constantc.apply(0) / (functiong.apply(0) + 1)), new Constant(functiong.apply(0) + 1.0));	
		return product1;
	}
	
	public double apply(int apply){
		double result = constantc.apply(apply)  * functiong.apply(apply) ;
		return result;
	}
	
	public String print(){
		String print = constantc.apply(0) + " * x^" + functiong.apply(0);
		return print;
	}
}
