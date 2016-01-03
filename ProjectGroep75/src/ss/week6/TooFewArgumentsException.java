package ss.week6;

public class TooFewArgumentsException extends ss.week6.WrongArgumentException {

	public TooFewArgumentsException(String message) {
		super(message);
	}
	public TooFewArgumentsException() {
		super("TooFewArgumentsException");
	}

}
