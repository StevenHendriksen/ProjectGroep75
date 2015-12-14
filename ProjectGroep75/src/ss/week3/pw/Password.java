package ss.week3.pw;

/**
 * Passwords
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Password implements Checker{

	// ------------------ Instance variables ----------------
	private String passw;
	private Checker checker;
	private String factoryPassword;

	// ------------------ Initial ------------------------

	/**
	 * Creates a <code>INITIAL</code> with the string "123456".
	 */
	public static final String INITIAL = "A1234567890";

	// ------------------ Constructor ------------------------

	/**
	 * Creates a <code>Password</code> with the given initial.
	 * 
	 * @param passw
	 *            is the name of the <code>Password</code>
	 */
	public Password(Checker checker){
		this.checker = checker;
		passw = INITIAL;
		factoryPassword = passw;
	}
	
	public Password() {
		this(new BasicChecker());
	}

	// ------------------ Queries --------------------------

	/**
	 * Shows whether the <code>Password</code> is acceptable or not.
	 * 
	 * @param suggestion
	 *            the password which is given by a person;
	 */
	public boolean acceptable(String suggestion) {
		if (suggestion.length() >= 6 && suggestion.contains(" ") == false) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Shows whether the <code>Password</code> is equal to the current
	 * <code>Password</code>.
	 * 
	 * @param test
	 *            the password which is given by a person;
	 */
	public boolean testWord(String test) {
		if (test.equals(passw)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Set a new <code>Password</code> when the old password is the same as the
	 * current and the new password is acceptable.
	 * 
	 * @param oldpass
	 *            the current password which is given by a person;
	 * @param newpass
	 *            the new password which is given by a person;
	 */
	public boolean setWord(String oldpass, String newpass) {
		if (oldpass.equals(passw) && acceptable(newpass)) {
			passw = newpass;
			return true;
		} else {
			return false;
		}
	}	
	
	public Checker getChecker(){
		return checker;
	}
	
	public String getfactoryPassword(){
		return factoryPassword;
	}
	
	public String generatePassword(){
		return passw;
	}
}
	