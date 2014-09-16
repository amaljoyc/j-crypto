package data.security;

public class Decryption {

	public String decrypt(String key, String cipherText) {

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

		// Getting ascii formated cipher text from string formated cipher text
		StringBuilder asciiCipherText = new StringBuilder();
		for (int c : cipherText.toCharArray()) {
			asciiCipherText.append(c);
		}

		// Converting ascii chars greater than 99 to less than 32
		StringBuilder filterdAsciiCipherText = new StringBuilder();
		for (int i = 0; i < asciiCipherText.length(); i++) {
			if (asciiCipherText.charAt(i) == '1') {
				filterdAsciiCipherText.append(asciiCipherText.charAt(++i));
				filterdAsciiCipherText.append(asciiCipherText.charAt(++i));
			} else {
				filterdAsciiCipherText.append(asciiCipherText.charAt(i));
				filterdAsciiCipherText.append(asciiCipherText.charAt(++i));
			}
		}

		// Finally getting the plain text
		String[] sixBlockSizedAsciiCipherText = filterdAsciiCipherText.toString().split("(?<=\\G.{6})");
		int keySizeCount = 0;
		StringBuilder plainText = new StringBuilder();
		for (int i = 0; i < sixBlockSizedAsciiCipherText.length; i++) {

			// getting ascii formated number without leading zeros
			int n = Integer.valueOf(sixBlockSizedAsciiCipherText[i]);

			// right shifting using a part of key
			int shiftedVal = n >> asciiKey[keySizeCount];
			plainText.append((char) shiftedVal);

			// check to reset key-count
			if (++keySizeCount == keySize) {
				keySizeCount = 0;
			}
		}

		return plainText.toString();
	}
}