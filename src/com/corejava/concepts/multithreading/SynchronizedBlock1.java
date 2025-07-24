package com.corejava.concepts.multithreading;

class Processer {
	public synchronized void doTask1() {
		System.out.println(Thread.currentThread().getName() + " is doing task1");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException exception) {
			System.out.println("Error occured in doTask method:" + exception.getMessage());
		}
		System.out.println(Thread.currentThread().getName() + " has completed task1");
	}

	public void doTask2() {
		System.out.println(Thread.currentThread().getName() + " is doing task2");
		System.out.println(Thread.currentThread().getName() + " is doing something before synchronized block");
		System.out.println(Thread.currentThread().getName() + " is doing something before synchronized block");
		System.out.println(Thread.currentThread().getName() + " is doing something before synchronized block");
		try {
			// here one thread will acquire lock on this object and if during it's execution
			// if any other thread comes inside the method then it will wait until lock on
			// this object is released i.e. until first thread finishes executing below
			// synchronized block.
			synchronized (this) {
				// we can write the thread safe logic here. this way whole method won't be
				// synchronized instead this block will be.
				System.out.println(Thread.currentThread().getName() + " is inside synchronized block");
				for (int i = 0; i <= 200; i++) {
					System.out.println(Thread.currentThread().getName() + " will only do printing now....");
				}
				Thread.sleep(3000);
			}
		} catch (InterruptedException exception) {
			System.out.println("Error occured in doTask method:" + exception.getMessage());
		}

		System.out.println(Thread.currentThread().getName() + " is doing something after synchronized block");
		System.out.println(Thread.currentThread().getName() + " is doing something after synchronized block");
		System.out.println(Thread.currentThread().getName() + " is doing something after synchronized block");
		System.out.println(Thread.currentThread().getName() + " has completed task2");
	}
}

public class SynchronizedBlock1 {

	public static void main(String[] args) {
		Processer processer = new Processer();
		Thread thread1 = new Thread(() -> processer.doTask1());
		Thread thread2 = new Thread(() -> processer.doTask1());
//		thread1.start();
//		thread2.start();

		Thread thread3 = new Thread(() -> processer.doTask2());
		Thread thread4 = new Thread(() -> processer.doTask2());
		thread3.setName("Customer One");
		thread4.setName("Customer Second");
		thread3.start();
		thread4.start();
	}

}
