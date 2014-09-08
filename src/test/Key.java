package test;

import java.util.Arrays;
import java.util.Scanner;

public class Key {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String key = sc.next().trim();
		if(key.length()==0 || key.length()>4) throw new IllegalArgumentException("Key should be of size 16");
		int [] asciiKey = new int [4];
		for(int i =0; i< key.length();i++){
			asciiKey[i] = (int)key.charAt(i);
		}
		System.out.println(Arrays.toString(asciiKey));
		
		String text = sc.next();
		String [] textArr = text.split("(?<=\\G.{"+key.length()+"})");
		System.out.println(Arrays.toString(textArr));
		for(int j = 0; j<textArr.length ; j++){
			System.out.println("Iteration " + j);
			byte [] byteArrOfChunks = textArr[j].getBytes();
			System.out.println("ByteArrayOfchunks "+ Arrays.toString(byteArrOfChunks));
			for(byte b : byteArrOfChunks){
				//TODO shift ....I am not able to do it
			}
		}
		System.out.println(Arrays.toString(textArr));
	}
}
