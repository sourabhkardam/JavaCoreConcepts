package com.corejava.concepts.stream.questions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

record EmployeeDto(String name, String department, int age, String gender) {
}

public class Exercise2 {
	public static void main(String[] args) {
		EmployeeDto employee1 = new EmployeeDto("SRK", "ECE", 31, "Male");
		EmployeeDto employee2 = new EmployeeDto("Salman", "CS", 44, "Male");
		EmployeeDto employee3 = new EmployeeDto("Katrina", "ECE", 21, "Female");
		EmployeeDto employee4 = new EmployeeDto("Kareena", "CS", 34, "Female");
		EmployeeDto employee5 = new EmployeeDto("Hrithik", "EEE", 30, "Male");
		EmployeeDto employee6 = new EmployeeDto("Aish", "EEE", 25, "Female");

		List<EmployeeDto> list = new ArrayList<>();
		list.add(employee1);
		list.add(employee2);
		list.add(employee3);
		list.add(employee4);
		list.add(employee5);
		list.add(employee6);

//		1.Find the names of all Employees in the CS department, sorted by age in descending order
		List<EmployeeDto> result1 = list.stream().filter(emp -> emp.department().equals("CS"))
				.sorted(Comparator.comparing(EmployeeDto::age).reversed()).toList();

		System.out.println("1. Result: " + result1);

//		2. Group Employees by department and count how many Employees are in each department
		Map<String, Long> result2 = list.stream()
				.collect(Collectors.groupingBy(EmployeeDto::department, Collectors.counting()));

		System.out.println("2. Result: " + result2);

//		3.Find the youngest female Employee.
		EmployeeDto result3 = list.stream().min(Comparator.comparing(EmployeeDto::age)).orElse(null);

		System.out.println("3. Result: " + result3);

//		4. Create a map of department -> list of Employee names.
		Map<String, List<String>> result4 = list.stream().collect(Collectors.groupingBy(EmployeeDto::department,
				Collectors.mapping(EmployeeDto::name, Collectors.toList())));

		System.out.println("4. Result: " + result4);

//		5. Find the average age of Employees in each department.
		Map<String, Double> result5 = list.stream()
				.collect(Collectors.groupingBy(EmployeeDto::department, Collectors.averagingInt(EmployeeDto::age)));

		System.out.println("5. Result: " + result5);

//		6. Get a list of unique departments, Employees belong to
		HashSet<String> seen = new HashSet<>();

		List<String> result6 = list.stream().filter(e -> seen.add(e.department())).map(e -> e.department()).toList();
		List<String> result6_1 = list.stream().map(emp -> emp.department()).distinct().toList();

		System.out.println("6. Result: " + result6 + ", 6_1. Result: " + result6_1);

//		7. Partition Employees into male and female groups, then list their names.
		Map<String, List<String>> result7 = list.stream().collect(Collectors.groupingBy(e -> {
			if (e.gender() == "Female") {
				return "Female";
			} else if (e.gender() == "Male") {
				return "Male";
			}
			return "";
		}, Collectors.mapping(e -> e.name(), Collectors.toList())));

		System.out.println("7. Result: " + result7);

//		8. Group employees by department, then within each department find the oldest employee
		Map<String, Optional<EmployeeDto>> result8 = list.stream().collect(Collectors
				.groupingBy(EmployeeDto::department, Collectors.maxBy(Comparator.comparingInt(EmployeeDto::age))));

		System.out.println("8. Result: " + result8);

//		9. Build a map of gender with average age of employees sorted by average age descending
		Map<String, Double> result9 = list.stream()
				.collect(Collectors.groupingBy(EmployeeDto::gender, Collectors.averagingDouble(EmployeeDto::age)));

		System.out.println("9. Result: " + result9);

//		10. For each department, find the youngest employee, but instead of returning the employee
//		object, return only their name in uppercase.
		Map<String, String> result10 = list.stream()
				.collect(Collectors.groupingBy(EmployeeDto::department,
						Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingInt(EmployeeDto::age)),
								e -> e.get().name().toUpperCase())));

		System.out.println("10. Result: " + result10);

//		11. Return a map where keys will be first letter of the name and value will the set of names
//		starting with that letter, no solution provided, try on your own. 
		Map<Character, List<String>> result11 = list.stream().collect(Collectors.groupingBy(
				emp -> (Character) emp.name().charAt(0), Collectors.mapping(emp -> emp.name(), Collectors.toList())));

		System.out.println("11. Result: " + result11);
	}
}
