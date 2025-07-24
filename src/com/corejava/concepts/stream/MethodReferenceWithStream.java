package com.corejava.concepts.stream;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
	private String name;
	private String salary = "NA";
	private String designation = "NA";

	public Employee(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", designation=" + designation + "]";
	}

}

public class MethodReferenceWithStream {

	public static String prefixMr(String name) {
		return "Mr. " + name;
	}

	public String prefixShri(String name) {
		return "Shri. " + name;
	}

	public static void main(String[] args) {
		List<String> names = List.of("Bob", "Alice", "David", "Charlie", "Eve");
		List<String> sortedNames = names.stream().sorted().collect(Collectors.toList());
		System.out.println("Sorted Names: " + sortedNames);

		List<String> list1 = names.stream().filter(name -> name.length() > 3).map(name -> "Mr." + name)
				.collect(Collectors.toList());
		System.out.println(list1);

		/**
		 * Method Reference is used to replace a lambda expression which is calling an
		 * existing method or providing the functionality which already a method is
		 * providing. Here, map method of stream class takes java.util.function.Function
		 * as parameter which takes some parameter and also return something. In this
		 * example, prefixMr is taking String as parameter and return "Mr." + name;
		 */
		// Method Reference to a static method
		List<String> list2 = names.stream().filter(name -> name.length() > 3).map(MethodReferenceWithStream::prefixMr)
				.collect(Collectors.toList());
		System.out.println(list2);

		// Method Reference to a instance method of fixed object
		MethodReferenceWithStream methodReferenceWithStream = new MethodReferenceWithStream();
		List<String> list3 = names.stream().filter(name -> name.length() > 3).map(methodReferenceWithStream::prefixShri)
				.collect(Collectors.toList());

		System.out.println(list3);

		/**
		 * Here map(Employee::new) is equivalent to map(name -> new Employee(name)),
		 * it's a function which is taking name as input and returning an Employee
		 * object
		 */
		List<Employee> list4 = names.stream().filter(name -> name.length() > 3).map(Employee::new)
				.collect(Collectors.toList());

		list4.forEach(System.out::println);

		// Method Reference to a instance method of arbitrary object
		/**
		 * Here map(Employee::getName) is equivalent to map(emp -> emp.getName()). Here
		 * emp is an arbitrary object which is provided by Stream. So, every time
		 * getName() method is called on emp, it will be called on arbitrary employee
		 * object unlike how method reference to a instance method of a fixed object
		 * works where method is invoked on same object. map(String::toUpperCase) ==
		 * map(name -> name.toUpperCase())
		 */
		List<String> empNames = list4.stream().map(Employee::getName).map(String::toUpperCase)
				.collect(Collectors.toList());
		empNames.forEach(System.out::println);

		System.out.println("Without method reference");
		list1.forEach(x -> System.out.println(x));

		System.out.println("With method reference");
		list1.forEach(System.out::println);

		System.out.println("Method Reference to custom static method");
		list1.forEach(MethodReferenceWithStream::customPrintConsumer);

	}

	// Custom consumer which is taking name as parameter and just printing it, not
	// returning anything.
	public static void customPrintConsumer(String name) {
		System.out.print(name + ", ");
	}

}
