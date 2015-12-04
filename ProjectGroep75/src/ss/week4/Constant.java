package ss.week4;

public class Constant implements Function, Integrandable{
	private double value;
	private Function derivative;
	private Function integrandable;
	
	public Constant(double constant) {
		value = constant;
	}
	
	public double apply (int apply){
		return value;
	}
	
	public Function derivative(){
		derivative = new Constant(0);
		
		return derivative;
	}
	public Function integrand(){
		integrandable = new LinearProduct(new Constant(value), new Exponent(1));
		return integrandable;
	}
}
