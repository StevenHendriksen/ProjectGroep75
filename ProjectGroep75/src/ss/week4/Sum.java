package ss.week4;

public class Sum implements Function{
	private Function functionf;
	private Function functiong;
	private Sum derivative;
	
	public Sum(Function f, Function g){
		functionf = f;
		functiong = g;
	}
	
	public double apply(int apply){
		return functionf.apply(apply) + functiong.apply(apply);
	}
	
	public Function derivative(){
		derivative = new Sum(functionf.derivative(), functiong.derivative());
		
		return derivative;
	}
	public Function integrand(){
		return new Sum(new Exponent(functiong.apply(0)) , new Exponent(functionf.apply(0)));
		}
}
