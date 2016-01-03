package ss.week6;

public class WrongArgumentException extends Exception {
	private String message;
	public WrongArgumentException(String message) {
		this.message = message;
	}
	public WrongArgumentException() {
		this.message = "WrongArgumentException";
	}
	public String getMessage(){
		return message;
	}
}
