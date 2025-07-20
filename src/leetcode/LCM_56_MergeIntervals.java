package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LCM_56_MergeIntervals {
	public int[][] merge(int[][] intervals) {
		List<int[]> result = new ArrayList<>();
		// Sort intervals by their start time (ascending).
		// This ensures when we scan through, any overlapping intervals
		// will be adjacent.
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		// Initialize 'start' and 'end' to the bounds of the first interval.
		int start = intervals[0][0];
		int end = intervals[0][1];
		for (int[] interval : intervals) {
			// If the current interval overlaps with the one in progress
			// (i.e., its start is <= our tracked 'end'), we merge them
			if (interval[0] <= end) {
				// Extend 'end' if this interval goes further
				end = Math.max(end, interval[1]);
			} else {
				// No overlap: push the previous interval [start, end] into result
				result.add(new int[] { start, end });
				// Start a new interval tracking with the current one
				start = interval[0];
				end = interval[1];
			}
		}
		// Add the final interval after the loop ends
		result.add(new int[] { start, end });
		// Convert the List<int[]> back into a 2D int[][] array and return
		return result.toArray(new int[result.size()][]);
	}
//O(NlogN) O(N)
}
