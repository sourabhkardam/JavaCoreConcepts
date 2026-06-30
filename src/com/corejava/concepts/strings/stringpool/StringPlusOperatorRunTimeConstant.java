package com.corejava.concepts.strings.stringpool;

public class StringPlusOperatorRunTimeConstant {
	public static void main(String[] args) {
		// Example 1: Concatenating non-constants (uses StringBuilder)
		String s1 = "Hello";
		String s2 = "World";
		String s3 = s1 + s2; // Compiled to: new StringBuilder().append(s1).append(s2).toString()
		System.out.println(s3 == "HelloWorld"); // false (new Heap object)

		// Example 2: Mixed data types
		int num = 42;
		String s4 = "The answer is: " + num;
		System.out.println(s4); // "The answer is: 42" (StringBuilder used)

		// Example 3: Null handling (converts to "null")
		String s5 = "Hello" + null;
		System.out.println(s5); // "Hellonull" (no NPE)

		// Example 4: Compile-time constant (pool optimization)
		String s6 = "a" + "b" + "c"; // Compile-time: "abc" (pool object)
		System.out.println(s6 == "abc"); // true
	}

}
