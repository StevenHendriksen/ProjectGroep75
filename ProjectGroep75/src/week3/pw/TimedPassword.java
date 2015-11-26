package week3.pw;

public class TimedPassword extends Password{
	private long validTime = 86400000;
	private long curTime;
	private String passw = INITIAL;
	
	public TimedPassword(){
		curTime = java.lang.System.currentTimeMillis() + validTime;
	}
	
	public TimedPassword(long valTime){
		curTime = java.lang.System.currentTimeMillis() + valTime;
	}
	
	public boolean isExpired() {
		return (java.lang.System.currentTimeMillis() > curTime);
	}

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