package week3.pw;

/**
 * TimePassword;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.0
 */

public class TimedPassword extends Password{
	// ------------------ Instance variables ----------------
	private long validTime = 86400000;
	private long curTime;
	private String passw = INITIAL;
	// ------------------ Constructor ------------------------
	/**
	 * Without arguments it uses the standard valid time;
	 * 
	 * @param curTime
	 *            the the the password will expire;
	 */		
	public TimedPassword(){
		curTime = java.lang.System.currentTimeMillis() + validTime;
	}
	/**
	 * With arguments it uses the given valid time(ms);
	 * 
	 * @param curTime
	 *            the the the password will expire;
	 */	
	public TimedPassword(long valTime){
		curTime = java.lang.System.currentTimeMillis() + valTime;
		validTime = valTime;
	}
	// ------------------ Commands ------------------------
	/**
	 * Checks if the password is expired
	 * returns boolean
	 */
	public boolean isExpired() {
		return (java.lang.System.currentTimeMillis() > curTime);
	}
	/**
	 * sets a new password and checks if it is acceptable
	 * returns a boolean
	 */
	public boolean setWord(String oldpass, String newpass) {
		if (oldpass.equals(passw) && acceptable(newpass)) {
			passw = newpass;
			curTime = java.lang.System.currentTimeMillis() + validTime;
			return true;
		} 	else {
			return false;
		}
	}	
}