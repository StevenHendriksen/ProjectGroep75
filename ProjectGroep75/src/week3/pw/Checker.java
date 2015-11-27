package week3.pw;

/**
 * Checker
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public interface Checker {
	/**
	 * this class tests if the password is acceptable according to the checker
	 */
	public boolean acceptable(String password);
	/**
	 * Generates a password that is acceptable
	 */
	public String generatePassword();
}
