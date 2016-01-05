package ss.week6;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class ArgumentLengthsDifferException extends ss.week6.WrongArgumentException{
	/**
	 * given 2 ints, it gives the message "ArgumentLengthsDifferException + i + j
	 * used to create an exception for different exceptions
	 */
	public ArgumentLengthsDifferException(int i , int j ) {
		super("ArgumentLengthsDifferException " + i + j + "");
	}

}
