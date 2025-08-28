package com.corejava.concepts.inheritance;

class Parent {
	
	Parent() {
		process();// we can interpret this method call as this.process() and In Java, the keyword
					// this is a reference variable that refers to the current object (the instance
					// of the class on which a method or constructor is being invoked).
	}

	public void process() {
		System.out.println("In Parent class process() method");
	}
}

class ChildA extends Parent {

	ChildA() {
		super();
	}

	@Override
	public void process() {
		System.out.println("In ChildA class process() method");
	}
}

public class CallingOverrideMethodInParentConstructor {
	public static void main(String[] args) {
		new Parent();
		new ChildA();

	}

}
