package domein;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import org.mindrot.jbcrypt.BCrypt;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;


public class WachtwoordController {

	
	// ----HASH----
	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
	public static String generatePassword() {
	    PasswordGenerator gen = new PasswordGenerator();
	    CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
	    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
	    lowerCaseRule.setNumberOfCharacters(2);

	    CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
	    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
	    upperCaseRule.setNumberOfCharacters(2);

	    CharacterData digitChars = EnglishCharacterData.Digit;
	    CharacterRule digitRule = new CharacterRule(digitChars);
	    digitRule.setNumberOfCharacters(2);

	    CharacterData specialChars = new CharacterData() {
	        public String getErrorCode() {
	            return "error";
	        }

	        public String getCharacters() {
	            return "!@#$%^&*()_+";
	        }
	    };
	    CharacterRule splCharRule = new CharacterRule(specialChars);
	    splCharRule.setNumberOfCharacters(2);

	    String password = gen.generatePassword(10, splCharRule, lowerCaseRule, 
	      upperCaseRule, digitRule);
	    return password;
	}

	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
	
	// ----PUBLIC----
	public static String generatePasswordHash(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}	
	
	public static boolean validatePassword(String originalPassword, String storedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		return BCrypt.checkpw(originalPassword, storedPassword);
	}
	


}
