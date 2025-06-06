package com.corejava.concepts.multithreading.executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BasicExecutorExample1 {

	public static void main(String[] args) throws InterruptedException {
//		try executing tasks using 1 thread then 2 thread and 3 thread. Time taken to complete task will go down as per no of threads

		ExecutorService executorService = Executors.newFixedThreadPool(1);
//		ExecutorService executorService = Executors.newFixedThreadPool(2);
//		ExecutorService executorService = Executors.newFixedThreadPool(3);

		List<String> list = Arrays.asList("Sourabh", "Sunil", "Dinkar");
		long startTime = System.currentTimeMillis();
		
		for (String input : list) {
			/**
			 * The execute() method in Java is part of the Executor interface, and it's used
			 * to submit a task (a Runnable) for execution. It's one of the simplest ways to
			 * run a task using an executor.
			 */
			executorService.execute(() -> {
				processString(input);
			});
		}

		/**
		 * 🔧 What shutdown() Does When you call executorService.shutdown():
		 * 
		 * It stops accepting new tasks. It allows previously submitted tasks to execute
		 * before terminating. It does not forcibly stop running tasks. This means the
		 * executor will continue to run all the tasks that were submitted before the
		 * shutdown call, but no new tasks will be accepted.
		 */
		executorService.shutdown();

		// Current thread i.e. main thread will wait/sleep for specified time before it
		// continue it's execution
		executorService.awaitTermination(3000, TimeUnit.MILLISECONDS);

//		current thread i.e. main thread will wait for above mentioned time and 
//		then will see if executor service isn't terminated then forcefully shutdown it.

		/**
		 * ✅ What isTerminated() does is that it returns true only if:
		 * 
		 * shutdown() or shutdownNow() has been called, and All tasks have completed
		 * execution. Returns false if:
		 * 
		 * The executor is still running tasks OR The executor hasn't been shut down
		 * yet.
		 */
//		if (!executorService.isTerminated()) {
		/**
		 * Unlike shutdown(), which allows in-progress tasks to finish, shutdownNow()
		 * tries to interrupt those tasks.
		 */
//			executorService.shutdownNow();
//		}

		System.out.println("Time taken to complete the program..." + (System.currentTimeMillis() - startTime));
	}

	public static void processString(String input) {
		try {
			System.out.println("Processing [" + input + "] ....");
			Thread.sleep(3000);
			System.out.println("Processing [" + input + "] completed");
		} catch (InterruptedException e) {
			System.out.println("Error occurred processing " + input + ": " + e);
		}
	}
}
