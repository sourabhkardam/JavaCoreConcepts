package com.corejava.concepts.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

	public static void main(String[] args) {
		int[][] intervals = new int[][] { { 8, 17 }, { 17, 20 }, { 32, 43 }, { 22, 28 }, { 30, 35 }, { 45, 49 } };

		List<List<Integer>> mergedIntervals = new ArrayList<List<Integer>>();

		// Sort by start time, then by end time
		Arrays.sort(intervals, (a, b) -> {
			if (a[0] != b[0]) {
				return Integer.compare(a[0], b[0]); // sort by start
			} else {
				return Integer.compare(a[1], b[1]); // sort by end if starts are equal
			}
		});

		// Print sorted array
		for (int[] interval : intervals) {
			System.out.print(Arrays.toString(interval) + ", ");
		}

		System.out.println();

		for (int i = 0; i < intervals.length; i++) {

			// if merged intervals is empty or
			// if current interval starts after the last merged interval ends, add it as a
			// new interval
			if (mergedIntervals.isEmpty() || currentIntervalNotPartOfLastInterval(intervals[i], mergedIntervals)) {
				mergedIntervals.add(Arrays.asList(intervals[i][0], intervals[i][1]));
			} else {
				List<Integer> lastInterval = mergedIntervals.get(mergedIntervals.size() - 1);
				int updatedEndInterval = Math.max(lastInterval.get(1), intervals[i][1]);
				lastInterval.set(1, updatedEndInterval);
			}
		}

		System.out.println("Merged Intervals:");
		mergedIntervals.forEach(System.out::println);
	}

	private static boolean currentIntervalNotPartOfLastInterval(int[] currentInterval,
			List<List<Integer>> mergedIntervals) {
		return currentInterval[0] > mergedIntervals.get(mergedIntervals.size() - 1).get(1);
	}

}
