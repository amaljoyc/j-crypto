package test;

public class Key {
	public static void main(String[] args) {
		String key = "Abc";
		System.out.println(key);
		StringBuilder sb = new StringBuilder();
		for(int a: key.toCharArray()) {
			sb.append(a);
		}
		key = sb.reverse().toString();
	}
}
