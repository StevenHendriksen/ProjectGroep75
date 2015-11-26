package week3;

import Week2.Password;

public class Adittion extends OperatorWithIdentity {
	private Adittion add;
	private int result;
	
	public Adittion(int first, int second){
		add = new Adittion(first, second);
		add.result = first + second;
	}
	public int result(){
		return add.result;
	}
}
