package week3.pw;

/**
 * StrongChecker;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.0
 */

public class StrongChecker extends BasicChecker implements Checker {
	// ------------------ Instance variables ----------------
	private String passw;
	
	// ------------------ Constructor ------------------------
	/**
	 * Creates a new password;
	 * 
	 * @param passw
	 *            the new password;
	 */	
	public StrongChecker() {
		passw = INITPASS;
	}
	
	// ------------------ Commands ------------------------
	/**
	 * Tests if the password is strong;
	 * returns boolean based on if the first character is a letter and the last character is a digit.
	 */
	public boolean acceptable(String password){
		if(java.lang.Character.isLetter(password.charAt(0)) && java.lang.Character.isDigit(password.charAt((password.length()) - 1))){
			return true;
		}
		else{
			return false;
		}
	}
	
	// ------------------ Query ------------------------
	/**
	 * Generate a password
	 * @param passw
	 * 				the generated password
	 */
	public String generatePassword(){
		return passw;
	}
}
