package ss.week6;

import org.apache.commons.codec.DecoderException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testprogram for DictionaryAttack.
 * Lab Exercise SoftwareSystems
 * @author Jip Spel
 * @version $Revision: 1.0 $
 */
public class DictionaryAttackTest {

    /** Testvariabele for a <tt>DictionaryAttack</tt> object. */
    private DictionaryAttack dictionaryAttack;

    /** Path to the text file */
    private static final String PATH = ""; //Your path to the test folder

    @Before
    public void setUp() {
        dictionaryAttack = new DictionaryAttack();
        try {
            dictionaryAttack.readPasswords(PATH + "LeakedPasswords.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Test for <tt>getPasswordHash</tt>
     * @throws NoSuchAlgorithmException 
     * @throws UnsupportedEncodingException 
     * @throws DecoderException 
     */
    @Test
    public void testGetPasswordHash() throws UnsupportedEncodingException, NoSuchAlgorithmException, DecoderException {
        assertEquals("5f4dcc3b5aa765d61d8327deb882cf99", dictionaryAttack.getPasswordHash("password"));
    }

    /**
     * Test for <tt>checkPassword</tt>
     * @throws DecoderException 
     * @throws NoSuchAlgorithmException 
     * @throws UnsupportedEncodingException 
     */
    @Test
    public void testCheckPassword() throws UnsupportedEncodingException, NoSuchAlgorithmException, DecoderException {
        assertTrue(dictionaryAttack.checkPassword("katrine", "spongebob"));
    }

}
