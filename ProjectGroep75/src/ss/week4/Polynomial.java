package ss.week4;

public class Polynomial implements Function{
	private LinearProduct[] array;
	private Constant newConstant;
	private Exponent newExponent;
	
	public Polynomial(double[] as, double[] pows){
		assert as.length == pows.length;
		array = new LinearProduct[as.length];
		for(int i = 0; i < as.length; i++){
			newConstant = new Constant(as[i]);
			newExponent = new Exponent((int) pows[i]);
			array[i] = new LinearProduct(newConstant, newExponent);
		}
	}
	
	public double apply(int apply){
		double sum = 0;
		for(int i = 0; i < array.length; i++){
			double pow = Math.pow(apply, array[i].getFunction().apply(0));
			double product = array[i].getConstant().apply(0) * pow;
			sum += product;
		}
		return sum;
	}
	
	public Polynomial derivative(){
		double[] as = new double[array.length];
		double[] pows = new double[array.length];
		
		for(int i=0; i < array.length; i++){
			as[i] = ((Exponent) (array[i].getFunction())).getPower() * array[i].getConstant().apply(0);
			pows[i] = ((Exponent) (array[i].getFunction())).getPower() - 1;
		}
		
		Polynomial pol = new Polynomial(as, pows);
		
		return pol;
	}
}