package com.corejava.concepts.multithreading.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutor1 {

	public static void main(String[] args) throws InterruptedException {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
		String path = "\"C:\\Storage_A\\Dummy.txt\"";

		/**
		 * The method scheduleAtFixedRate() is part of the ScheduledExecutorService
		 * interface in Java. It is used to schedule a task to run repeatedly at a fixed
		 * interval, regardless of how long the task takes to execute.
		 */
		scheduledExecutorService.scheduleAtFixedRate(
				() -> System.out.println("Write this file:" + path + " , 5 seconds"), 0, 5, TimeUnit.SECONDS);

		scheduledExecutorService.scheduleAtFixedRate(
				() -> System.out.println("Read this file:" + path + " , 5 seconds"), 0, 5, TimeUnit.SECONDS);

		scheduledExecutorService.awaitTermination(10, TimeUnit.SECONDS);

		if (!scheduledExecutorService.isTerminated()) {
			scheduledExecutorService.shutdown();
		}

	}

}
