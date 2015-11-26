package week3;

public class Multiplication implements OperatorWithIdentity {
	private Multiplication mult;
	private int result;
	
	public Multiplication(int first, int second){
		mult = new Multiplication(first, second);
		mult.result = first * second;
	}
	public int result(){
		return mult.result;
	}
}
