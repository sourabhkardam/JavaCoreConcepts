package com.corejava.concepts.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Demonstrates the use of reduction operations in Java Streams.
 * <p>
 * A reduction operation (also known as a fold) takes a sequence of input
 * elements and combines them into a single summary result by repeatedly
 * applying a combining operation. In Java Streams, the {@code reduce} method
 * processes elements in the encounter order, applying the provided binary
 * operator to the current accumulated value and the next stream element. The
 * result of each operation becomes the new accumulated value, which is then
 * combined with the next element, and so on, until all elements are processed.
 * The final accumulated value is returned as an {@code Optional}, or as a plain
 * value if an identity is provided.
 * </p>
 * <p>
 * Internally, for sequential streams, reduction is performed in a single pass
 * from the first to the last element. For parallel streams, the stream may be
 * partitioned, and partial results are computed in parallel and then combined
 * using the same operator.
 * </p>
 * <p>
 * This class shows how to use the {@code reduce} method to perform operations
 * such as finding the maximum element, summing elements, and performing
 * subtraction on a list of integers.
 * </p>
 */

public class ReductionOperations {

	/**
	 * The main method demonstrates various reduction operations on a list of
	 * integers.
	 *
	 * @param args command-line arguments (not used)
	 */
	public static void main(String[] args) {
		// Create a list of integers
		List<Integer> list = Arrays.asList(2, 34, 17, 4, 9);

		// Find the maximum element using reduce.
		// The lambda compares two elements and returns the greater one.
		Optional<Integer> max = list.stream().reduce((a, b) -> (a - b) >= 0 ? a : b);
		System.out.println("Maximum element of list: " + max.get());

		// Calculate the sum of all elements using reduce.
		// The lambda adds two elements together.
		Optional<Integer> sum = list.stream().reduce((a, b) -> (a + b));
		System.out.println("Sum of elements: " + sum.get());

		// Calculate the result of subtracting all elements from left to right.
		// The lambda subtracts the second element from the first.
		Optional<Integer> subtraction = list.stream().reduce((a, b) -> (a - b));
		System.out.println("Subtraction of elements: " + subtraction.get());
	}
}
