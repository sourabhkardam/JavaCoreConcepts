package com.corejava.concepts.multithreading;

class Factory {
	private int count = 0;
	private boolean isProduced = false;

	synchronized public void produce() {
		// As this method is synchronized so the moment the producer thread enters this
		// method, this method will acquire lock on factory object and will block
		// consumer thread and will lock until this method completion.

		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (isProduced) {
			try {
				// will release the lock on factory object and will wait/sleep till consumer
				// thread notify
				System.out.println("Producer thread is waiting for consumer thread to consume the item");
				wait();
			} catch (InterruptedException e) {
			}
		}
		
		isProduced = true;
		count++;
		System.out.println("Produced " + count + " item");

		// will notify consumer thread that producer thread has released the lock on
		// factory object and you can stop wait
		notify();

		// note: lock on factory object will be released after this method completion if
		// wait() is not called
	}

	synchronized public void consume() {
		// As this method is synchronized so the moment the consumer thread enters this
		// method, this method will acquire lock on factory object and will block
		// producer thread and will lock until this method completion.

		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (!isProduced) {
			try {
				// will release the lock on factory object and will wait/sleep till producer
				// thread notify
				System.out.println("Consumer thread is waiting for producer thread to produce the item");
				wait();
			} catch (InterruptedException e) {
			}
		}
		isProduced = false;
		System.out.println("Consumed " + count + " item");

		// will notify producer thread that consumer thread has released the lock on
		// factory object and you can stop wait
		notify();

		// note: lock on factory object will be released after this method completion if
		// wait() is not called
	}

}

public class ConsumerProducer1 {
	public static void main(String[] args) {
		Factory factory = new Factory();

		Thread producerThread = new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				factory.produce();// producerThread trying locking the factory object
			}
		}, "Producer Thread");

		Thread consumerThread = new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				factory.consume();// consumerThread trying locking the factory object
			}
		}, "Consumer Thread");

		producerThread.start();
		consumerThread.start();
	}

}
