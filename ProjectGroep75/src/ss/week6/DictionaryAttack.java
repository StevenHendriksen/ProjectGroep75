package ss.week6;

import java.util.Map;
import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DictionaryAttack {
	private Map<String, String> passwordMap;
	private Map<String, String> loginMap;
	private Map<String, String> hashDictionary;
	private Map<String, String> foundPasswords;	
	private static final String PATH = ""; // Your path to the test folder

	/**
	 * Reads a password file. Each line of the password file has the form:
	 * username: encodedpassword
	 * 
	 * After calling this method, the passwordMap class variable should be
	 * filled withthe content of the file. The key for the map should be the
	 * username, and the password hash should be the content.
	 * 
	 * @param filename
	 */
	public void readPasswords(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(PATH + filename));
		Scanner sorter = new Scanner(reader);
		passwordMap = new HashMap<String, String>();
		loginMap = new HashMap<String, String>();
		while (sorter.hasNextLine()) {
			String str = sorter.nextLine();
			Scanner spatie = new Scanner(str);
			String login = spatie.next();
			String strippedLogin = login.replaceAll(":", "");
			String password = spatie.next();
			passwordMap.put(strippedLogin, password);
			loginMap.put(password, strippedLogin);
			spatie.close();
		}
		sorter.close();
	}

	/**
	 * Given a password, return the MD5 hash of a password. The resulting hash
	 * (or sometimes called digest) should be hex-encoded in a String.
	 * 
	 * @param password
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws DecoderException
	 */
	public String getPasswordHash(String password)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, DecoderException {
		String result = null;
		MessageDigest md = MessageDigest.getInstance("MD5");
		;
		byte[] passwordbytes = md.digest(password.getBytes());
		char[] hexbytes = Hex.encodeHex(passwordbytes);
		result = new String(hexbytes);
		return result;
	}

	/**
	 * Checks the password for the user the password list. If the user does not
	 * exist, returns false.
	 * 
	 * @param user
	 * @param password
	 * @return whether the password for that user was correct.
	 * @throws DecoderException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public boolean checkPassword(String user, String password)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, DecoderException {
		return passwordMap.get(user).equals(getPasswordHash(password));
	}

	/**
	 * Reads a dictionary from file (one line per word) and use it to add
	 * entries to a dictionary that maps password hashes (hex-encoded) to the
	 * original password.
	 * 
	 * @param filename
	 *            filename of the dictionary.
	 * @throws FileNotFoundException
	 * @throws DecoderException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public void addToHashDictionary(String filename)
			throws FileNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException, DecoderException {
		BufferedReader reader = new BufferedReader(new FileReader(PATH + filename));
		Scanner sorter = new Scanner(reader);
		hashDictionary = new HashMap<String, String>();
		while (sorter.hasNextLine()) {
			String str = sorter.nextLine();
			hashDictionary.put(getPasswordHash(str), str);
		}
		sorter.close();
	}

	/**
	 * Do the dictionary attack.
	 */
	public void doDictionaryAttack() {
		foundPasswords = new HashMap<String, String>();
		for(String k : hashDictionary.keySet()){
			if(passwordMap.containsValue(k)){
				loginMap.get(k);
				foundPasswords.put(loginMap.get(k), hashDictionary.get(k));

			}
		}
		System.out.println(foundPasswords);
		System.out.println("Passwors found: " + foundPasswords.size());
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, DecoderException, IOException {
		DictionaryAttack da = new DictionaryAttack();
		da.addToHashDictionary("25mostcommonpasswords.txt");
		da.addToHashDictionary("linuxwords.txt");
		da.readPasswords("LeakedPasswords.txt");
		da.doDictionaryAttack();
	}

	public Map<String, String> get() {
		return passwordMap;
	}

}
