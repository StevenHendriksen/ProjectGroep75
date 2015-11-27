package week3.pw;

/**
 * BasicChecker;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.0
 */

public class BasicChecker implements Checker {
	// ------------------ Instance variables ----------------
	private String passw;
	
	public static final String INITPASS = "A1234567890";
	
	// ------------------ Constructor ------------------------
	/**
	 * Creates a new password;
	 * 
	 * @param passw
	 *            the new password;
	 */	
	public BasicChecker() {
		passw = INITPASS;
	}
	// ------------------ Commands ------------------------
	/**
	 * Tests if the password is acceptable;
	 * returns boolean based on if it is acceptable
	 */
	public boolean acceptable(String password){
		if (password.length() >= 6 && password.contains(" ") == false) {
			return true;
		} else {
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
