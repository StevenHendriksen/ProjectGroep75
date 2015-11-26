package week3.pw;

public class StrongChecker extends BasicChecker implements Checker {
	public boolean acceptable(String password){
		if(java.lang.Character.isLetter(password.charAt(0)) && java.lang.Character.isDigit(password.charAt((password.length()) - 1))){
			return true;
		}
		else{
			return false;
		}
	}
}
