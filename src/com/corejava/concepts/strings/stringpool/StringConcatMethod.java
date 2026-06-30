package com.corejava.concepts.strings.stringpool;

public class StringConcatMethod {
	public static void main(String[] args) {
		// Example 1: Basic concatenation
		String s1 = "Hello";
		String s2 = "World";
		String s3 = s1.concat(s2);
		System.out.println(s3); // "HelloWorld" (new Heap object)
		System.out.println(s3 == "HelloWorld"); // false (s3 is not in the pool)

		// Example 2: Empty string argument
		String s4 = s1.concat("");
		System.out.println(s4 == s1); // true (returns original s1, no new object)

		// Example 3: Null argument (throws NPE)
		String s5 = s1.concat(null); // Throws NullPointerException
	}

}
