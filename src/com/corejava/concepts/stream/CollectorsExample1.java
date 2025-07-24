package com.corejava.concepts.stream;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsExample1 {

	public static void main(String[] args) {

		String input = "thisiswhatthisis";
		
		System.out.println("Using lambda expression");
		Map<String, Long> countOfEachChar1 = Arrays.stream(input.split(""))
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		countOfEachChar1.forEach((c, count) -> System.out.println(c + "=" + count));

		System.out.println("Using Function.identity()");
		Map<String, Long> countOfEachChar2 = Arrays.stream(input.split(""))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		countOfEachChar2.forEach((c, count) -> System.out.println(c + "=" + count));
	}

}
