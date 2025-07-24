package com.corejava.concepts.multithreading;

public class ThreadInterrupt {

	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(() -> {
			try {
				System.out.println(Thread.currentThread().getName() + " has started sleeping");
				Thread.sleep(5000);// sleeps for 10 seconds
				System.out.println(Thread.currentThread().getName() + " has completed sleeping");
			} catch (InterruptedException e) {
				// As thread was interrupted, so after InterruptedException is thrown the
				// interrupt flag will be cleaned.
				System.out.println(Thread.currentThread().getName() + " has been interrupted");
			}

			// As interrupt flag was cleaned, it will return false.
			System.out.println(
					Thread.currentThread().getName() + " was interruped:" + Thread.currentThread().isInterrupted());
		});
		thread1.setName("Thread 1");

		Thread thread2 = new Thread(() -> {
			try {
				System.out.println(Thread.currentThread().getName() + " has started sleeping");
				Thread.sleep(5000);// sleeps for 10 seconds
				System.out.println(Thread.currentThread().getName() + " has completed sleeping");
			} catch (InterruptedException e) {
				// As thread was interrupted, so after InterruptedException is thrown the
				// interrupt flag will be cleaned. So preserving the flag by setting it again
				// using Thread.currentThread().interrupt()

				System.out.println(Thread.currentThread().getName() + " has been interrupted");
				Thread.currentThread().interrupt();

			}

			// As interrupt flag was preserved, it will return false.
			System.out.print(
					Thread.currentThread().getName() + " was interruped:" + Thread.currentThread().isInterrupted());
		});
		thread2.setName("Thread 2");

		thread1.start();
		thread2.start();

		// Main thread will wait for 2 seconds then will interrupt thread1
		Thread.sleep(2000);

		// thread1 has been interrupted and as Thread 1 is sleeping so immediately
		// InterruptedException will be thrown
		System.out.println(Thread.currentThread().getName() + " has interrrupted " + thread1.getName());
		thread1.interrupt();

		// thread2 has been interrupted and as Thread 1 is sleeping so immediately
		// InterruptedException will be thrown
		System.out.println(Thread.currentThread().getName() + " has interrrupted " + thread2.getName());
		thread2.interrupt();
	}

}
