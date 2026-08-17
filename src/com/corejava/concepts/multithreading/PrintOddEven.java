package com.corejava.concepts.multithreading;

import java.util.Scanner;

class Printer {
	private boolean isEvenTurn = true;

	public synchronized void printEven(int n) {
		sleep();
		try {
			while (!isEvenTurn) {
				wait();
			}
			System.out.println(n);
			isEvenTurn = false;
			notify();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void sleep() {
		try {
//			System.out.println(Thread.currentThread().getName() + " is sleeping");
			Thread.sleep(500);
//			System.out.println(Thread.currentThread().getName() + " is awake");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void printOdd(int n) {
		sleep();
		try {
			while (isEvenTurn) {
				wait();
			}
			System.out.println(n);
			isEvenTurn = true;
			notify();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}

class OddPrintingThread extends Thread {
	private int n;
	private Printer printer;

	public OddPrintingThread(int n, Printer printer) {
		this.n = n;
		this.printer = printer;
	}

	@Override
	public void run() {
		for (int i = 1; i <= n; i = i + 2) {
			printer.printOdd(i);
		}
	}

}

class EvenPrintingThread extends Thread {
	private int n;
	private Printer printer;

	public EvenPrintingThread(int n, Printer printer) {
		this.n = n;
		this.printer = printer;
	}

	@Override
	public void run() {
		for (int i = 0; i <= n; i = i + 2) {
			printer.printEven(i);
		}
	}

}

public class PrintOddEven {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the range:");
		int n = sc.nextInt();
		Printer printer = new Printer();
		Thread t1 = new EvenPrintingThread(n, printer);
		Thread t2 = new OddPrintingThread(n, printer);

		t1.setName("Even Number Printing Thread");
		t2.setName("Odd Number Printing Thread");

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
