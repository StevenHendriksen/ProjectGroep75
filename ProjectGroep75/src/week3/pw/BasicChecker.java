package week3.pw;

public class BasicChecker implements Checker {
	public boolean acceptable(String password){
		if (password.length() >= 6 && password.contains(" ") == false) {
			return true;
		} else {
			return false;
		}
	}
}
