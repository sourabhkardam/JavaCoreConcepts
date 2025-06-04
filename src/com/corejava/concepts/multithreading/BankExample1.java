package com.corejava.concepts.multithreading;

class Bank {
	private int balance = 0;

	public void deposit(int amount) {
		System.out.println("Depositing: " + amount + ", Current Balance: " + balance);
		balance += amount;
		System.out.println("Deposited: " + amount + ", New Balance: " + balance);
	}

	public void withdraw(int amount) {
		if (amount <= balance) {
			System.out.println("Withdrawing: " + amount + ", Current Balance: " + balance);
			balance -= amount;
			System.out.println("Withdrown: " + amount + ", New Balance: " + balance);
		} else {
			System.err.println("Insufficent balance");
		}
	}

	public int getBalance() {
		return this.balance;
	}
}

public class BankExample1 {

	public static void main(String[] args) throws InterruptedException {
		Bank bank = new Bank();

		Runnable deposit = new Runnable() {
			@Override
			public void run() {
				bank.deposit(1000);
			}
		};

		Thread thread1 = new Thread(deposit);
		Thread thread2 = new Thread(deposit);
		Thread thread3 = new Thread(() -> {
			bank.deposit(500);
		});

		Thread thread4 = new Thread(() -> {
			bank.withdraw(100);
		});

		Thread thread5 = new Thread(() -> {
			bank.withdraw(700);
		});

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();

		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		thread5.join();

		System.out.print("Current Balance:" + bank.getBalance());

	}

}
