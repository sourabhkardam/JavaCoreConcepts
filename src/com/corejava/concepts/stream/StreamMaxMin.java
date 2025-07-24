package com.corejava.concepts.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Demonstrates how to find the maximum and minimum elements in a list using
 * Java Streams.
 * <p>
 * This class shows the usage of {@link java.util.stream.Stream#max(Comparator)}
 * and {@link java.util.stream.Stream#min(Comparator)} methods with both natural
 * and reverse order comparators.
 * </p>
 */
public class StreamMaxMin {

	/**
	 * The main method demonstrates finding the maximum and minimum values in a list
	 * of integers using Java Stream API with different comparators.
	 *
	 * @param args command-line arguments (not used)
	 */
	public static void main(String[] args) {
		// Create a list of integers
		List<Integer> list = Arrays.asList(2, 34, 17, 4, 9);

		// Find the maximum element using natural order comparator
		Optional<Integer> max1 = list.stream().max(Comparator.naturalOrder());
		System.out.println("Max element of list (natural order): " + max1.get());

		// Find the maximum element using reverse order comparator (actually gives the
		// minimum)
		Optional<Integer> max2 = list.stream().max(Comparator.reverseOrder());
		System.out.println("Max element of list (reverse order): " + max2.get());

		// Find the minimum element using natural order comparator
		Optional<Integer> min1 = list.stream().min(Comparator.naturalOrder());
		System.out.println("Min element of list (natural order): " + min1.get());

		// Find the minimum element using reverse order comparator (actually gives the
		// maximum)
		Optional<Integer> min2 = list.stream().min(Comparator.reverseOrder());
		System.out.println("Min element of list (reverse order): " + min2.get());

	}

}
