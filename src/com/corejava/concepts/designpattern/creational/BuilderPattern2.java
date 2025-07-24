package com.corejava.concepts.designpattern.creational;

/**
 * Here employee object will only be created using EmployeeBuilder.
 */
class Employee {
	private long EmployeeId;

	@Override
	public String toString() {
		return "Employee [EmployeeId=" + EmployeeId + ", name=" + name + ", Designation=" + designation + "]";
	}

	private String name;
	private String designation;

	private Employee(EmployeeBuilder EmployeeBuilder) {
		this.EmployeeId = EmployeeBuilder.getEmployeeId();
		this.name = EmployeeBuilder.getName();
		this.designation = EmployeeBuilder.getDesignation();
	}

	static class EmployeeBuilder {
		private long EmployeeId;
		private String name;
		private String designation;

		public long getEmployeeId() {
			return EmployeeId;
		}

		public EmployeeBuilder setEmployeeId(long EmployeeId) {
			this.EmployeeId = EmployeeId;
			return this;
		}

		public String getName() {
			return name;
		}

		public EmployeeBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public String getDesignation() {
			return designation;
		}

		public EmployeeBuilder setDesignation(String courseName) {
			this.designation = courseName;
			return this;
		}

		public Employee buildEmployee() {
			return new Employee(this);
		}

	}
}

public class BuilderPattern2 {

	public static void main(String[] args) {
		Employee employee1 = new Employee.EmployeeBuilder().setEmployeeId(112253).setDesignation("Software Engineer")
				.buildEmployee();

		Employee employee2 = new Employee.EmployeeBuilder().setName("Abhishek").setDesignation("Sr. Software Engineer")
				.buildEmployee();

		System.out.println(employee1.toString());
		System.out.println(employee2.toString());
	}

}
