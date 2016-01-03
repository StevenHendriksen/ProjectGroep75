package ss.week6;

public class ArgumentLengthsDifferException extends ss.week6.WrongArgumentException{

	public ArgumentLengthsDifferException(int i , int j ) {
		super("ArgumentLengthsDifferExcetion " + i + j + "");
	}

}
