package com.corejava.concepts.stream;

import java.util.List;
import java.util.stream.Collectors;

public class BasicStreamExample1 {

	public static void main(String[] args) {
		List<String> names = List.of("Bob", "Alice", "David", "Charlie", "Eve");
		List<String> sortedNames = names.stream().sorted().collect(Collectors.toList());
		System.out.println("Sorted Names: " + sortedNames);
	}

}
