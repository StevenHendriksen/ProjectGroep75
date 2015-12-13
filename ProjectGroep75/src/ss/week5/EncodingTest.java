package ss.week5;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * A simple class that experiments with the Hex encoding
 * of the Apache Commons Codec library.
 *
 */
public class EncodingTest {
    public static void main(String[] args) throws DecoderException {
        String input = "Hello World";
        System.out.println(Base64.encodeBase64String(input.getBytes()));
        String input2 = "010203040506";
        byte[] bytes= Base64.decodeBase64(input2.getBytes());
        System.out.println(new String(bytes));
        String input3 = "U29mdHdhcmUgU3lzdGVtcw==";
        byte[] bytes2 = Base64.decodeBase64(input3.getBytes());
        System.out.println(new String(bytes2));
        String input4 = "";
        for(int i = 0; i <= 10; i++){
            input4 = input4 + "a";
            byte[] bytes3 = Base64.decodeBase64(input4.getBytes());
            System.out.println(new String(bytes3));
        }
    }
}
