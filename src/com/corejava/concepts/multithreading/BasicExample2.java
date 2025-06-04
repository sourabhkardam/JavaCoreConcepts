package com.corejava.concepts.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Counter {
	int count = 0;

	public void increment() {
		count++;
	}

	/**
	 * using synchronized keyword with method signature means that this method
	 * [increment()] will acquire the lock of the object which is calling this
	 * method i.e. counter object and let's say this method invocation is triggered
	 * by some thread i.e. thread1, then it means thread1 acquires the lock on the
	 * counter object (resource) and that means no other thread will be able to lock
	 * the counter object till the thread1 complete processing increment method. So,
	 * basically by synchronizing the increment method, we ensure that only one
	 * thread can execute this method at a time, which prevents the race condition
	 */
//	synchronized public void increment() {
//		count++;
//	}

//	private final Lock lock = new ReentrantLock();
//
//	public void increment() {
//		lock.lock();
//		count++;
//		lock.unlock();
//	}
}

class MyThread extends Thread {
	Counter counter;

	MyThread(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		for (int i = 0; i < 1000; i++) {
			counter.increment();
		}
	}
}

public class BasicExample2 {
	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter();

		MyThread thread1 = new MyThread(counter);
		MyThread t2 = new MyThread(counter);

		thread1.start();
		t2.start();

		thread1.join();
		t2.join();

		System.out.println("Final count: " + counter.count);
	}
}
