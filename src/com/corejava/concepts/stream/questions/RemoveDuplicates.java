package com.corejava.concepts.stream.questions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RemoveDuplicates {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(10, 15, 18, 19, 18, 10).stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(e -> e.getValue() == 1).map(e -> e.getKey()).collect(Collectors.toList());

		System.out.println(list);
	}
}
