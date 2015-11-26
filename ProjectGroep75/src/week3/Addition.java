package week3;

import week2.Password;

public class Addition implements OperatorWithIdentity {
	private Addition add;
	private int result;
	
	public Addition(int first, int second){
		add = new Addition(first, second);
		add.result = first + second;
	}
	public int result(){
		return add.result;
	}
}
