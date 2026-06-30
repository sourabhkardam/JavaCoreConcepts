package com.corejava.concepts.stream.questions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Compartor.comparing(Function) returns a comparator which basically compare
 * two elements after applying the function(passed in argument of comparing
 * method) on these two elements.
 */
public class Comparator_SortByLastDigit {

	public static void main(String[] args) {
		List<Integer> inputList = Arrays.asList(92, 65, 81, 39, 47, 14, 73); // Op; 81, 92, 73, 14, 65, 47, 39
		List<Integer> result1 = inputList.stream().sorted((a, b) -> (a % 10) - (b % 10)).collect(Collectors.toList());

		System.out.println(result1);

		// Below implementation is short hand of above implementation
		List<Integer> result2 = inputList.stream().sorted(Comparator.comparing(ele -> ele % 10))
				.collect(Collectors.toList());

		System.out.println(result2);

//		List<Integer> result = inputList.stream().sorted().collect(Collectors.toList());
//
//		System.out.println(result);
	}

}
