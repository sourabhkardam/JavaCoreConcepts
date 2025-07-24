package com.corejava.concepts.multithreading.executors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class MyExecutorService implements ExecutorService {

	private class MyWorker implements Runnable {

		@Override
		public void run() {
			while (!hasShutdown || !taskQueue.isEmpty()) {
				try {
					Runnable runnable = taskQueue.poll(50, TimeUnit.MILLISECONDS);
					if (runnable != null) {
						runnable.run();
					}
				} catch (InterruptedException e) {
					System.out.println(e);
				}
			}
		}

	}

	private final BlockingQueue<Runnable> taskQueue;
	private boolean hasShutdown = false;

	public MyExecutorService(int poolSize) {
		this.taskQueue = new LinkedBlockingQueue<Runnable>();

		for (int i = 1; i <= poolSize; i++) {
			// will create n-threads with each thread having runnable task which will run
			// continuously until custom executor service got shutdown & queue is not empty.
			// If you notice, we are running a runnable inside a runnable
			// n=poolSize
			new Thread(() -> {
				while (!hasShutdown || !taskQueue.isEmpty()) {
					try {
						Runnable runnable = taskQueue.poll(50, TimeUnit.MILLISECONDS);
						if (runnable != null) {
							runnable.run();
						}
					} catch (InterruptedException e) {
						System.out.println(e);
					}
				}
			}, "Thread-" + i).start();
		}
	}

	/**
	 * public MyExecutorService(int poolSize) { this.taskQueue = new
	 * LinkedBlockingQueue<Runnable>();
	 * 
	 * for (int i = 1; i <= poolSize; i++) { Runnable myWorker = new MyWorker(); //
	 * will create n-threads with each thread having runnable task which will run //
	 * continuously until custom executor service got shutdown & queue is not empty
	 * // n=poolSize new Thread(myWorker, "Thread-" + i).start(); } }
	 */

	@Override
	public void execute(Runnable task) {
		if (!hasShutdown) {
			taskQueue.offer(task);
		}
	}

	@Override
	public void shutdown() {
		hasShutdown = true;
	}

	@Override
	public List<Runnable> shutdownNow() {
		hasShutdown = true;
		return new ArrayList<Runnable>(taskQueue);
	}

	@Override
	public boolean isShutdown() {
		return hasShutdown;
	}

	@Override
	public boolean isTerminated() {
		return hasShutdown && taskQueue.isEmpty();
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " thread has invoked awaitTermination");

		// Will sleep thread which invokes this thread, in this case it is main thread
		Thread.sleep(timeout);
		return false;
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<?> submit(Runnable task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

}

public class CustomExecutorService {
	private static int fibonacci(int n) {
		if (n <= 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		}
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public static void main(String[] args) throws InterruptedException {
//		MyExecutorService executorService = new MyExecutorService(1);
//		MyExecutorService executorService = new MyExecutorService(2);
		MyExecutorService executorService = new MyExecutorService(3);
//		MyExecutorService executorService = new MyExecutorService(4);

		executorService.execute(() -> {
			int n = 10;
			System.out.println("Fibonacci of " + n + " = " + fibonacci(n) + " and this is calculate by "
					+ Thread.currentThread().getName());
		});

		executorService.execute(() -> {
			int n = 11;
			System.out.println("Fibonacci of " + n + " = " + fibonacci(n) + " and this is calculate by "
					+ Thread.currentThread().getName());
		});

		executorService.execute(() -> {
			int n = 12;
			System.out.println("Fibonacci of " + n + " = " + fibonacci(n) + " and this is calculate by "
					+ Thread.currentThread().getName());
		});

		executorService.execute(() -> {
			int n = 13;
			System.out.println("Fibonacci of " + n + " = " + fibonacci(n) + " and this is calculate by "
					+ Thread.currentThread().getName());
		});

		// it's important to call shutdown otherwise thread will keep running
		executorService.shutdown();

		executorService.awaitTermination(1000, null);

		System.out.println("End of main method");
	}

}
