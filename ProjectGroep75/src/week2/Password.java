package week2;

/**
 * Passwords
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public class Password {

	// ------------------ Instance variables ----------------
	private String passw;

	// ------------------ Initial ------------------------

	/**
	 * Creates a <code>INITIAL</code> with the string "123456".
	 */
	public static final String INITIAL = "123456";

	// ------------------ Constructor ------------------------

	/**
	 * Creates a <code>Password</code> with the given initial.
	 * 
	 * @param passw
	 *            is the name of the <code>Password</code>
	 */
	public Password() {
		passw = INITIAL;
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
	
}
	