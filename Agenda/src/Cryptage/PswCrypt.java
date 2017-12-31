/**
 * 
 */
package cryptage;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/** A class that contains everything necessary to crypt and uncrypt a text with a password using EAS encryption
 * @author Louis
 *
 */
public class PswCrypt {
	
	private static String SEPARATOR = "&@&";
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
	}
	
	
	
	/** Decrypt a text using a EAS key generated from the password
	 * 
	 * @param psw 
	 * 			The password used to crypt the text
	 * @param text 
	 * 			The text you want to decrypt
	 * @return A String that contains the decrypted text
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws NoSuchPaddingException 
	 */
	public static String pswUncrypt(String psw, String text) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException {
		String[] tmp=text.split(SEPARATOR);
		
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");		
		cipher.init(Cipher.DECRYPT_MODE, getEASKey(psw), new IvParameterSpec(tmp[0].getBytes("ISO-8859-1")));
		String result = new String(cipher.doFinal(tmp[1].getBytes("ISO-8859-1")), "ISO-8859-1");
		
		
		return result;
		
	}
	/** Crypt a text using a EAS key generated from the password
	 * 
	 * @param psw 
	 * 			The password you wish to crypt the text with
	 * @param text 
	 * 			The text you want to crypt
	 * @return A String that contains the crypted text
	 * 
	 * 
	 * @throws InvalidParameterSpecException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeySpecException 
	 */
	public static String pswCrypt(String psw, String text) throws InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeySpecException {
		
		SecretKey secret = getEASKey(psw);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		AlgorithmParameters params = cipher.getParameters();
		StringBuilder result = new StringBuilder();
		result.append(new String(params.getParameterSpec(IvParameterSpec.class).getIV(), "ISO-8859-1"));
		result.append(SEPARATOR);
		result.append(new String(cipher.doFinal(text.getBytes("ISO-8859-1")), "ISO-8859-1"));
		
		return result.toString();
		
	}

	private static SecretKey getEASKey(String psw) throws NoSuchAlgorithmException, InvalidKeySpecException {
		/* Derive the key, given password and salt. */
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(psw.toCharArray(), salt, 65536, 256);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
		return secret;
	}
	
	public static byte[] salt = {
	        (byte)0x5e, (byte)0xa8, (byte)0xee, (byte)0x59,
	        (byte)0xcc, (byte)0x7c, (byte)0x51, (byte)0x8c
	    };

}
