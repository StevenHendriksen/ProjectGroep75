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
        System.out.println("5.12");  	
    	String input0 = "4d6f64756c652032";    
    	byte[] bytes4 = Hex.decodeHex(input0.toCharArray());
    	System.out.println(new String(bytes4)+ "\n");
        System.out.println("5.13.1");
        String input = "Hello World";
        System.out.println(Base64.encodeBase64String(input.getBytes())+ "\n");
        System.out.println("5.13.2");
        String input2 = "010203040506";
        byte[] bytes =  Hex.decodeHex(input2.toCharArray());
        byte[] temp = Base64.encodeBase64(bytes);
        System.out.println(new String(temp)+ "\n");
        System.out.println("5.13.3");
        String input3 = "U29mdHdhcmUgU3lzdGVtcw==";
        byte[] bytes2 = Base64.decodeBase64(input3.getBytes());
        System.out.println(new String(bytes2) + "\n");
        System.out.println("5.13.4");
        String input4 = "";
        for(int i = 0; i <= 10; i++){
            input4 = input4 + "a";
            byte[] bytes3 = Base64.encodeBase64(input4.getBytes());
            System.out.println(new String(bytes3));
        }
    }
}
