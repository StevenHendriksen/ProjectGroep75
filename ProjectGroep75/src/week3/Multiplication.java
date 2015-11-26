package week3;

public class Multiplication implements OperatorWithIdentity{
	private int result;
	
	public int operate (int left, int right){
		result = left * right;
		return result;
	}
	
	public int identity (){
		return 1;
	}
}
