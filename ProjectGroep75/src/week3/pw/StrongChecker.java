package week3.pw;

public class StrongChecker extends BasicChecker implements Checker {
	private String passw;
	
	public StrongChecker() {
		passw = INITIAL;
	}
	
	public boolean acceptable(String password){
		if(java.lang.Character.isLetter(password.charAt(0)) && java.lang.Character.isDigit(password.charAt((password.length()) - 1))){
			return true;
		}
		else{
			return false;
		}
	}
	
	public String generatePassword(){
		return passw;
	}
}
