package ss.week4;

public class LinearProduct extends Product{
	private Constant constantc;
	private Function functiong;
	private LinearProduct product1;
	
	public LinearProduct(Constant n, Function g){
		super(n, g);
		constantc = n;
		functiong = g;
	}
	
	public Function derivative(){
		product1 = new LinearProduct(constantc, functiong.derivative());
		
		return product1;
	}
	
	public Constant getConstant() {
		return constantc;
	}
	
	public Function getFunction() {
		return functiong;
	}
	public Function integrand(){
		return new LinearProduct(new Constant((constantc.apply(0)) / (functiong.apply(0) -1)), functiong);
	}
}
