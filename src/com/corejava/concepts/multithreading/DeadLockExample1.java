package com.corejava.concepts.multithreading;

//No deadlock as Java's synchronized methods are reentrant, so nested calls from the same thread are safe
class Test {
	synchronized public void read() {
		System.out.println(Thread.currentThread().getName() + " completed reading");
		write();
	}

	synchronized public void write() {
		System.out.println(Thread.currentThread().getName() + " completed writing");
	}
}

public class DeadLockExample1 {

	public static void main(String[] args) {
		new Test().read();
	}

}
