package test;

import java.util.Arrays;
import java.util.Scanner;

public class Key {
	public static void main(String[] args) {
		System.out.println("Enter the key: ");
		Scanner sc = new Scanner(System.in);
		String key = sc.next().trim();
		if (key.length() == 0 || key.length() > 4) {
			throw new IllegalArgumentException("Key should be of size 4 or less");
		}

		StringBuilder sb = new StringBuilder();
		for (int a : key.toCharArray()) {
			sb.append(a);
		}
		int keySize = sb.length();
		int[] asciiKey = new int[keySize];
		for (int i = 0; i < keySize; i++) {
			asciiKey[i] = Character.getNumericValue(sb.charAt(i));
		}
		System.out.println(Arrays.toString(asciiKey));

		System.out.println("Enter the text: ");
		String text = sc.next();
		byte[] byteText = text.getBytes();
		System.out.println(Arrays.toString(byteText));

		int i = 0;
		for (int b : byteText) {
			System.out.println("AsciiKey: " + asciiKey[i]);
			System.out.println("Actual Byte: " + b);
			int x = b >> asciiKey[i];
			int y = x << asciiKey[i];
			if (++i == keySize) {
				i = 0;
			}
			System.out.println("After Shifting: " + x);
			System.out.println("Reverse Shifting: " + y);
		}

		// int splitSize = (int) Math.ceil((float) text.length() / keySize);
		// String[] textArr = text.split("(?<=\\G.{" + splitSize + "})");
		// System.out.println(Arrays.toString(textArr));

		// for (int j = 0; j < textArr.length; j++) {
		// System.out.println("Iteration " + j);
		// byte[] byteArrOfChunks = textArr[j].getBytes();
		// System.out.println("ByteArrayOfchunks " +
		// Arrays.toString(byteArrOfChunks));
		// for (byte b : byteArrOfChunks) {
		// // TODO shift ....I am not able to do it
		// }
		// }
		// System.out.println(Arrays.toString(textArr));
	}
}
