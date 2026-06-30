package com.corejava.concepts.strings.stringpool;

public class StringPlusOperatorCompileTimeConstant {
	public static void main(String[] args) {
		String s1 = "Hello" + "World"; // Compile-time constant: "HelloWorld"
		System.out.println(s1 == "HelloWorld"); // true (s1 is in the pool)

		final String s2 = "Hello";
		final String s3 = "World";
		String s4 = s2 + s3; // Also a compile-time constant (s2 and s3 are final)
		System.out.println(s4 == "HelloWorld"); // true (pool object)
	}
}
