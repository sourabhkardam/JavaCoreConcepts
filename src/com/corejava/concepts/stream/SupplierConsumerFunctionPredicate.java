package com.corejava.concepts.stream;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SupplierConsumerFunctionPredicate {
	public static void main(String[] args) {
		Supplier<String> supplier = () -> "hello";
		System.out.println(supplier.get());

		Consumer<Integer> consumer = (num) -> System.out.println(num);
		consumer.accept(10);

		Function<Integer, Integer> function = (num) -> num + 10;
		System.out.println(function.apply(5));

		Predicate<Integer> predicate = (age) -> age > 18;
		System.out.println(predicate.test(16));
	}
}
