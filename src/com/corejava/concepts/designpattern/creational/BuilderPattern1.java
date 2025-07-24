package com.corejava.concepts.designpattern.creational;

/**
 * As student class has a constructor (one with the data fields), this way of
 * writing Builder pattern will also allow to create Student object
 * independently which shouldn't be allowed. Student object should only be
 * created using StudentBuilder.
 */
class Student {
	private long studentId;

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", courseName=" + courseName + "]";
	}

	private String name;
	private String courseName;

	public Student(StudentBuilder studentBuilder) {
		this.studentId = studentBuilder.getStudentId();
		this.name = studentBuilder.getName();
		this.courseName = studentBuilder.getCourseName();
	}

	public Student(long studentId, String name, String courseName) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.courseName = courseName;
	}

}

class StudentBuilder {
	private long studentId;
	private String name;
	private String courseName;

	public long getStudentId() {
		return studentId;
	}

	public StudentBuilder setStudentId(long studentId) {
		this.studentId = studentId;
		return this;
	}

	public String getName() {
		return name;
	}

	public StudentBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public String getCourseName() {
		return courseName;
	}

	public StudentBuilder setCourseName(String courseName) {
		this.courseName = courseName;
		return this;
	}

	public Student buildStudent() {
		return new Student(this);
//		return new Student(studentId, name, courseName);
	}

}

public class BuilderPattern1 {

	public static void main(String[] args) {
		Student student1 = new StudentBuilder().setStudentId(101).setName("Sourabh").buildStudent();
		Student student2 = new StudentBuilder().setName("Anamika").setCourseName("B.Tech").buildStudent();

		System.out.println(student1);
		System.out.println(student2);
	}

}
