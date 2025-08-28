package com.corejava.concepts.collections.hashmap;

import java.util.HashMap;
import java.util.Objects;

/**
 * If we want our hashmap to store only employees with unique employee id and
 * name then our Employee class must override both method i.e. hashCode() and
 * equals() of Object class. If any of the method isn't override, it won't work.
 * 
 * Because let's say if we just override hashCode() then employees with same id
 * and name will be added in same bucket as they will be having same hashcode
 * but as equals() isn't overridden in Employee class then parent equals() will
 * compare the address of those employee but as address will different for each
 * employee object, duplicate employee will be also be added to the bucket. 
 * 
 * And if only equals() is overridden then employees with same id and name will be
 * added in different buckets as parent hashCode() will use object address to 
 * generate hashcode and object address will different for each employee object.
 */

/**
 * Note: To understand above comment you should know the internal working of
 * hashmap and how it uses hashCode() to find the hashValue of object and using
 * this hashValue to find index of bucket and how it uses equals() to compare
 * the keys (in case of collision) in the bucket.
 */
class Employee {
	private long empId;
	private String empName;

	public Employee(long empId, String empName) {
		this.empId = empId;
		this.empName = empName;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(empId, empName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return empId == other.empId && Objects.equals(empName, other.empName);
	}

}

public class HashCodeWithEquals {

	public static void main(String[] args) {
		Employee emp1 = new Employee(101, "Sourabh");
		Employee emp2 = new Employee(102, "Rohit");
		Employee emp3 = new Employee(103, "Aashish");
		Employee emp4 = new Employee(101, "Sourabh");

		HashMap<Employee, Long> empSalaryMap = new HashMap<Employee, Long>();
		empSalaryMap.put(emp1, 10000l);
		empSalaryMap.put(emp2, 20000l);
		empSalaryMap.put(emp3, 30000l);
		empSalaryMap.put(emp4, 40000l);

		System.out.println("Total Employees:" + empSalaryMap.size());
		System.out.println(emp1.getEmpName() + " Salary:" + empSalaryMap.get(emp1));
		System.out.println(emp4.getEmpName() + " Salary:" + empSalaryMap.get(emp4));
	}

}
