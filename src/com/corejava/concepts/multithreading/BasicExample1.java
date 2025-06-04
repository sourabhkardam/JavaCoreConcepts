package com.corejava.concepts.multithreading;

class MathUtils {
	synchronized public void createTable(int number) {
		System.out.println("Creating multiplication table for: " + number);
		for (int i = 1; i <= 10; i++) {
			System.out.println(number + " * " + i + " = " + (number * i));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted: " + e.getMessage());
			}
		}
		System.out.println("Multiplication table for " + number + " created.");
	}
}

class TableFiveThread extends Thread {
	private MathUtils mathUtils;

	public TableFiveThread(MathUtils mathUtils, String name) {
		super(name);
		this.mathUtils = mathUtils;
	}

	@Override
	public void run() {
		System.out.println("Thread started: " + Thread.currentThread().getName());
		mathUtils.createTable(5);
	}
}

class TableSevenThread extends Thread {
	private MathUtils mathUtils;

	public TableSevenThread(MathUtils mathUtils, String name) {
		super(name);
		this.mathUtils = mathUtils;
	}

	@Override
	public void run() {
		System.out.println("Thread started: " + Thread.currentThread().getName());
		mathUtils.createTable(7);
	}
}

public class BasicExample1 {

	public static void main(String[] args) {
		MathUtils mathUtils = new MathUtils();

		// Creating threads by extending Thread class which are using common resource
		// i.e. mathUtils
		Thread thread1 = new TableFiveThread(mathUtils, "Table Five Thread");
		Thread thread2 = new TableSevenThread(mathUtils, "Table Seven Thread");
		thread1.start();
		thread2.start();

		// Creating threads using anonymous inner classes which are using common
		// resource i.e. mathUtils
		Thread thread3 = new Thread() {
			@Override
			public void run() {
				mathUtils.createTable(10);
			}
		};

		Thread thread4 = new Thread() {
			@Override
			public void run() {
				mathUtils.createTable(12);
			}
		};

		thread3.start();
		thread4.start();

		// Creating a thread using a lambda expression which is using common resource
		// i.e. mathUtils
		Thread thread5 = new Thread(() -> {
			mathUtils.createTable(15);
		});

		Thread thread6 = new Thread(() -> {
			System.out.println("Thread started: " + Thread.currentThread().getName());
			mathUtils.createTable(20);
		});

		thread5.start();
		thread6.start();

		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			thread5.join();
			thread6.join();
		} catch (InterruptedException e) {
			throw new RuntimeException("Thread interrupted: " + e.getMessage());
		}

		// Thread.currentThread().getName() returns the name of the thread which
		// triggered the current method.
		// In this case, main method is triggered by the main thread so it will return
		// "main"
		System.out.println("Thread started: " + Thread.currentThread().getName());
	}

}
