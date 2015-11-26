package week3.pw;

public class BasicChecker implements Checker {
	private String passw;
	
	public static final String INITPASS = "A1234567890";
	
	public BasicChecker() {
		passw = INITPASS;
	}
	
	public boolean acceptable(String password){
		if (password.length() >= 6 && password.contains(" ") == false) {
			return true;
		} else {
			return false;
		}
	}
	public String generatePassword(){
		return passw;
	}
}
