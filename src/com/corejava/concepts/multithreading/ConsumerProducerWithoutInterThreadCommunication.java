package com.corejava.concepts.multithreading;

class OtherFactory {
	private int count = 0;
	private boolean isProduced = false;

	synchronized public void produce() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (isProduced) {
			// if we use while loop here, then producer thread will hold the lock on the
			// object and loop will keep running
			System.out.println("Producer thread is waiting for consumer thread to consume the item");
			return;
		}

		isProduced = true;
		count++;
		System.out.println("Produced " + count + " item");

	}

	synchronized public void consume() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!isProduced) {
			System.out.println("Consumer thread is waiting for producer thread to produce the item");
			return;
		}
		isProduced = false;
		System.out.println("Consumed " + count + " item");

	}

}

public class ConsumerProducerWithoutInterThreadCommunication {
	public static void main(String[] args) {
		OtherFactory factory = new OtherFactory();

		Thread producerThread = new Thread(() -> {
			for (int i = 1; i <= 20; i++) {
				factory.produce();// producerThread trying locking the factory object
			}
		}, "Producer Thread");

		Thread consumerThread = new Thread(() -> {
			for (int i = 1; i <= 20; i++) {
				factory.consume();// consumerThread trying locking the factory object
			}
		}, "Consumer Thread");

		producerThread.start();
		consumerThread.start();
	}

}
