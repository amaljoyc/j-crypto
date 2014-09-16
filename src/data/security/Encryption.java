package data.security;

public class Encryption {

	public String encrypt(String key, String plainText) {

		// Converting key in text-format to key in ascii-format
		StringBuilder asciiKeyString = new StringBuilder();
		for (int a : key.toCharArray()) {
			asciiKeyString.append(a);
		}
		int keySize = asciiKeyString.length();
		int[] asciiKey = new int[keySize];
		for (int i = 0; i < keySize; i++) {
			asciiKey[i] = Character.getNumericValue(asciiKeyString.charAt(i));
		}

		// Left Shifting ascii formated plain-text using the ascii formated key
		byte[] asciiPlainText = plainText.getBytes();
		int keySizeCount = 0;
		StringBuilder asciiCipherText = new StringBuilder();
		for (int b : asciiPlainText) {

			// left shifting using a part of key
			int shiftedVal = b << asciiKey[keySizeCount];
			char[] shiftedValInCharArray = String.valueOf(shiftedVal).toCharArray();

			// appending leading zeros to make a block of size 6
			for (int j = 0; j < (6 - shiftedValInCharArray.length); j++) {
				asciiCipherText.append("0");
			}

			for (char c : shiftedValInCharArray) {
				asciiCipherText.append(c);
			}

			// check to reset key-count
			if (++keySizeCount == keySize) {
				keySizeCount = 0;
			}
		}

		// Converting ascii formated cipher text into string formated cipher
		// text
		String[] doubleBlockSizedAsciiCipherText = asciiCipherText.toString().split("(?<=\\G.{2})");
		StringBuilder cipherText = new StringBuilder();
		int cipherBlockInNumberFormat = 0;
		for (String s : doubleBlockSizedAsciiCipherText) {
			cipherBlockInNumberFormat = Integer.parseInt(s);

			// Since ascii chars below 32 are non-printable, converting it to
			// ascii chars above 99
			if (cipherBlockInNumberFormat < 32) {
				cipherBlockInNumberFormat += 100;
			}
			cipherText.append((char) cipherBlockInNumberFormat);
		}

		return cipherText.toString();
	}
}