package week3.Hotel;

/**
 * Safe;
 * 
 * @author Stan Peters en Steven Hendriksen
 * @version 1.0
 */


public class Safe {
	
	// ------------------ Instance variables ----------------
	//@ private invariant active == false && pass != null && open == false;
	private boolean active = false;
	private Password pass;
	private boolean open = false;
	
	// ------------------ Constructor ------------------------
	/**
	 * Creates a new password;
	 * 
	 * @param pass
	 *            the new password;
	 */	
	public Safe() {
		pass = new Password();
	}

	// ------------------ Commands ------------------------
	/**
	 * Activates the safe when the password is correct;
	 * 
	 * @param active
	 *            Activates the safe;
	 */
	//@ ensures isOpen() ==> isActive();
	public boolean activate(String password) {
		if(pass.testWord(password)){
			assert pass.testWord(password);
			active = true;
		}
		else{
			active = false;
		}
		assert pass.testWord(password);

		return active;
	}
	
	/**
	 * Deactivates the safe;
	 * 
	 * @param open
	 *            Opens the safe;
	 * @param active
	 * 			  Activates the safe;
	 */
	//@ ensures isOpen() == false && isActive() == false;
	public void deactivate(){
		open = false;
		active = false;
	}
	
	/**
	 * Opens the safe;
	 * 
	 * @param open
	 *            Opens the safe;
	 */
	//@ ensures isOpen() ==> isActive();
	public boolean open(String password){
		if(active && pass.testWord(password)){
			assert active;
			assert pass.testWord(password);
			open = true;
		}
		else{
			open = false;
		}
		return open;
	}
	
	/**
	 * Closes the safe;
	 * 
	 * @param open
	 *            Opens the safe;
	 */
	//@ ensures isOpen() == false;
	public void close(){
		open = false;
	}
	
	public static void main (String[] args){
		Safe safe = new Safe();
		safe.activate("56165156");
		System.out.println("234234");
	}
	
	// ------------------ Queries --------------------------
	
	/**
	 * Shows whether the <code>Safe</code> is active (true) or not (false);
	 * 
	 * @param active
	 *            Safe is active or not.;
	 */
	//@pure;
	public boolean isActive(){
		return active;
	}
	
	/**
	 * Shows whether the <code>Safe</code> is open (true) or not (false);
	 * 
	 * @param open
	 *            Safe is open or not.;
	 */
	//@pure;
	public boolean isOpen(){
		return open;
	}
	
	public Password getPass() {
		return this.pass;
	}
	
	/**
	 * Shows the current password of the safe;
	 * 
	 * @param passw
	 *            The password of the safe;
	 */
	//@pure;
}
