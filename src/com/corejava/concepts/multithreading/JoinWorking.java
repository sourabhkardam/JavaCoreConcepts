package com.corejava.concepts.multithreading;

/**
 * t.join() means that the thread calling t.join() blocks until the thread t has
 * finished its execution. If t has already finished when the current thread
 * calls t.join(), then the current thread does not stop and just keeps going.
 * 
 */
public class JoinWorking {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Main method started");

		Thread thread1 = new Thread(() -> {
			try {
				System.out.println(Thread.currentThread().getName() + " has started sleeping.");
				Thread.sleep(10000);
				System.out.println(Thread.currentThread().getName() + " has completed sleeping.");
			} catch (InterruptedException e) {
			}
		}, "FirstThread");

		Thread thread2 = new Thread(() -> {
			try {
				System.out.println(Thread.currentThread().getName() + " has started sleeping.");
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + " has completed sleeping.");
			} catch (InterruptedException e) {
			}
		}, "SecondThread");

		thread1.start();
		thread2.start();

		System.out.println("Main thread is waiting for " + thread1.getName() + " to complete.");
		thread1.join();
		System.out.println("Main thread has stopped waiting for " + thread1.getName() + " to complete.");

		System.out.println("Main thread is waiting for " + thread2.getName() + " to complete.");
		thread2.join();
		System.out.println("Main thread has stopped waiting for " + thread2.getName() + " to complete.");

		System.out.println("Main method ended");
	}

}
