package leetcode;

import java.util.List;
import java.util.ArrayList;

public class LCM_57_InsertInterval {
	public int[][] insert(int[][] intervals, int[] newInterval) {

		// Create a list to hold the resulting (merged + non-overlapping) intervals
		List<int[]> ls = new ArrayList<>();

		// 'i' will scan through the input array
		int i = 0;

		// 'n' is the total number of existing intervals
		int n = intervals.length;

		// 1) Add all intervals that end before the new interval begins (no overlap)
		while (i < n && intervals[i][1] < newInterval[0]) {
			ls.add(intervals[i]); // copy this non-overlapping interval as is
			i++; // move to the next interval
		}

		// 2) Merge all intervals that overlap with the new interval
		while (i < n && intervals[i][0] <= newInterval[1]) {
			// Expand the start of newInterval to the minimum start seen so far
			newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
			// Expand the end of newInterval to the maximum end seen so far
			newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
			i++; // consume this overlapping interval
		}

		// 3) Add the merged newInterval
		ls.add(newInterval);

		// 4) Add the remaining intervals that start after the newInterval ends (no
		// overlap)
		while (i < n) {
			ls.add(intervals[i]); // copy each remaining interval
			i++;
		}

		// 5) Convert the list back to an array of int[] and return
		return ls.toArray(new int[ls.size()][]);
// O(N) O(N)
	}

}
