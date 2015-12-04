package ss.week4;

public class Polynomial implements Function{
	LinearProduct[] array;
	Constant constant1;
	Function constant2;
	
	public Polynomial(double[] as, double[] pows){
		for(int i = 0; i == as.length; i++){
			constant1 = new Constant(as[i]);
			constant2 = new Constant(pows[i]);
			array = new LinearProduct[constant1, constant2];
		}
	}
	
	public double apply(int apply){
		
	}
	
	public Function derivative(){
		
	}
}
