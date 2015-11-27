package week3.pw;

/**
 * Checker
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version $Revision: 1.0 $
 */

public interface Checker {
	/**
	 * These methods are all in BasicChecker and StrongChecker
	 */
	public boolean acceptable(String password);
	
	public String generatePassword();
}
