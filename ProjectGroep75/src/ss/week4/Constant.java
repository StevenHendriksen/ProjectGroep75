package ss.week4;

public class Constant implements Function{
	private double value;
	private Function derivative;
	
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
}
