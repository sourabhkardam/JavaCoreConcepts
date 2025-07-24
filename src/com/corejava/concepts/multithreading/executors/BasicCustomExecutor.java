package com.corejava.concepts.multithreading.executors;

import java.util.concurrent.Executor;

class MyExecutor implements Executor {

	@Override
	public void execute(Runnable command) {
		Thread thread = new Thread(command);
		thread.start();
	}

}

public class BasicCustomExecutor {

	public static void main(String[] args) {
		MyExecutor executor = new MyExecutor();
		executor.execute(() -> {
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + " is counting, Count=" + i);
			}
		});

		executor.execute(() -> {
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + " is singing, blah blah..." + i);
			}
		});
		
		executor.execute(() -> {
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + " is dancing, dancing dancing..." + i);
			}
		});
	}

}
