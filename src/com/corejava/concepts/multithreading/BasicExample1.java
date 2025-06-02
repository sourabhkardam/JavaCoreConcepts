package com.corejava.concepts.multithreading;

class MathUtils {
	synchronized public void createTable(int number) {
		System.out.println("Creating multiplication table for: " + number);
		for (int i = 1; i <= 10; i++) {
			System.out.println(number + " * " + i + " = " + (number * i));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted: " + e.getMessage());
			}
		}
		System.out.println("Multiplication table for " + number + " created.");
	}
}

class TableFiveThread extends Thread {
	private MathUtils mathUtils;

	public TableFiveThread(MathUtils mathUtils) {
		this.mathUtils = mathUtils;
	}

	@Override
	public void run() {
		mathUtils.createTable(5);
	}
}

class TableSevenThread extends Thread {
	private MathUtils mathUtils;

	public TableSevenThread(MathUtils mathUtils) {
		this.mathUtils = mathUtils;
	}

	@Override
	public void run() {
		mathUtils.createTable(7);
	}
}

public class BasicExample1 {

	public static void main(String[] args) {
		MathUtils mathUtils = new MathUtils();

		Thread thread1 = new TableFiveThread(mathUtils);
		Thread thread2 = new TableSevenThread(mathUtils);
		thread1.start();
		thread2.start();

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

		System.out.println("Thread started: " + Thread.currentThread().getName());
	}

}
