package leetcode;

public class NinjaTraining {

	public static int recursion(int day, int last, int[][] points) {
		// Defines a static method 'recursion' that:
		// - returns an int (the max points achievable)
		// - takes three parameters:
		// 1) day : the index of the current day (0-based)
		// 2) last : the index (0,1,2 or 3) of the task done on the previous day
		// 3) points: a 2D array where points[day][task] is the reward for doing 'task'
		// on 'day'

		if (day == 0) {
			// Base case: if we're looking at day 0 (the first day),
			// we cannot recurse further.

			int max = 0;
			// Initialize a local variable 'max' to 0;
			// this will track the best possible score on day 0,
			// given the constraint of not repeating the 'last' task.

			for (int task = 0; task < 3; task++) {
				// Loop over the 3 possible tasks (indexed 0, 1, 2).

				if (task != last) {
					// Only consider this task if it is different from 'last'
					// (we cannot do the same task two days in a row).

					max = Math.max(max, points[0][task]);
					// Compare the current best (max) with the score for
					// doing 'task' on day 0, and keep whichever is larger.
				}
			}

			return max;
			// Return the highest-scoring valid task for day 0.
		}

		// If day > 0, we need to explore recursive possibilities:

		int max = 0;
		// Re-initialize 'max' for this day.

		for (int task = 0; task < 3; task++) {
			// Again, loop through each of the 3 tasks.

			if (task != last) {
				// Enforce the constraint: today's task != yesterday's task.

				int point = points[day][task] + recursion(day - 1, task, points);
				// Calculate 'point' as:
				// - points[day][task]: points earned by doing 'task' today
				// - recursion(day-1, task, points): the maximum points for
				// all previous days, where today’s task becomes 'last'
				// for the recursive call.

				max = Math.max(max, point);
				// Update 'max' if this combination yields a higher total.
			}
		}

		return max;
		// Return the best total points achievable up through this day,
		// given the 'last' restriction.
	}

	public static int memoization(int day, int last, int[][] points, int[][] dp) {
		if (day == 0) {
			int max = 0;
			for (int task = 0; task < 3; task++) {
				if (task != last) {
					max = Math.max(max, points[0][task]);
				}
			}
			return max;
		}
		if (dp[day][last] != -1) {
			return dp[day][last];
		}
		int max = 0;
		for (int task = 0; task < 3; task++) {
			if (task != last) {
				int point = points[day][task] + memoization(day - 1, task, points, dp);
				max = Math.max(max, point);
			}
		}
		return dp[day][last] = max;
	}

	public static int tabulation(int n, int[][] points) {
		// dp[day][last] = max points up to 'day' if the task done on 'day-1' was 'last'
		// (0,1,2), or 3 for "no restriction"
		int[][] dp = new int[n][4];

		// Base case: on day 0, if last=0 you can't do task 0, so pick best of 1,2; etc.
		dp[0][0] = Math.max(points[0][1], points[0][2]);
		dp[0][1] = Math.max(points[0][0], points[0][2]);
		dp[0][2] = Math.max(points[0][0], points[0][1]);
		// last = 3 means no restriction, so you can choose the best of all three
		dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

		// Fill table
		for (int day = 1; day < n; day++) {
			for (int last = 0; last < 4; last++) {
				int maxPoint = 0;
				// Try doing each task != last
				for (int task = 0; task < 3; task++) {
					if (task != last) {
						int point = points[day][task] + dp[day - 1][task];
						maxPoint = Math.max(maxPoint, point);
					}
				}
				dp[day][last] = maxPoint;
			}
		}

		// On the last day, use last=3 (no restriction) to get the overall max
		return dp[n - 1][3];
	}

	public static int optimized(int n, int[][] points) {
		int[] prev = new int[4];
		prev[0] = Math.max(points[0][1], points[0][2]);
		prev[1] = Math.max(points[0][0], points[0][2]);
		prev[2] = Math.max(points[0][0], points[0][1]);
		prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
		for (int day = 1; day < n; day++) {
			int[] temp = new int[4];
			for (int last = 0; last < 4; last++) {
				int maxPoint = 0;
				for (int task = 0; task < 3; task++) {
					if (task != last) {
						maxPoint = Math.max(maxPoint, points[day][task] + prev[task]);
					}
				}
				temp[last] = maxPoint;
			}
			prev = temp;
		}
		return prev[3];
	}

	public static int optimized2(int n, int[][] points) {
		// base case (day 0), where “lastX” means the max points if yesterday’s task was
		// X,
		// and last3 means “no restriction” (for the very first day).
		int last0 = Math.max(points[0][1], points[0][2]);
		int last1 = Math.max(points[0][0], points[0][2]);
		int last2 = Math.max(points[0][0], points[0][1]);
		int last3 = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

		// now roll forward day by day
		for (int day = 1; day < n; day++) {
			// if you can’t pick task 0 today, choose the best of doing 1 or 2
			int curr0 = Math.max(last1 + points[day][1], last2 + points[day][2]);
			// can’t pick task 1 today → pick best of 0 or 2
			int curr1 = Math.max(last0 + points[day][0], last2 + points[day][2]);
			// can’t pick task 2 today → pick best of 0 or 1
			int curr2 = Math.max(last0 + points[day][0], last1 + points[day][1]);
			// no restriction for tomorrow
			int curr3 = Math.max(Math.max(curr0, curr1), curr2);

			// shift for next iteration
			last0 = curr0;
			last1 = curr1;
			last2 = curr2;
			last3 = curr3;
		}

		// last3 holds the max points over all n days with no final restriction
		return last3;
	}

	public static int optimized3(int n, int[][] points) {
		// --- base case: day 0 -----------------------------------
		// prev[c] = max points up through day 0 if you do task c on day 0
		int last0 = points[0][0];
		int last1 = points[0][1];
		int last2 = points[0][2];

		// --- transition: days 1…n−1 -----------------------------
		for (int day = 1; day < n; day++) {
			// if you do task 0 today, you must have done 1 or 2 yesterday:
			int curr0 = points[day][0] + Math.max(last1, last2);
			// if you do task 1 today, you must have done 0 or 2 yesterday:
			int curr1 = points[day][1] + Math.max(last0, last2);
			// if you do task 2 today, you must have done 0 or 1 yesterday:
			int curr2 = points[day][2] + Math.max(last0, last1);

			// roll forward
			last0 = curr0;
			last1 = curr1;
			last2 = curr2;
		}

		// --- answer = best of the three options on the last day ----
		return Math.max(last0, Math.max(last1, last2));
	}

	public static void main(String[] args) {
		int[][] arr = new int[][] { { 17, 2, 17 }, { 16, 16, 5 }, { 14, 3, 19 } };
		System.out.println(recursion(arr.length - 1, 3, arr));

		int n = arr.length;
		// Prepare a DP table for memoization with dimensions [n_days][4].
		// - Rows (n): one entry for each day index 0…n-1.
		// - Columns (4): one entry for each possible “last task” value:
		// • 0, 1, 2 → you did task #0, #1, or #2 on the previous day.
		// • 3 → dummy initial value meaning “no task done yesterday”.
		// We need 4 columns so that dp[day][3] is a valid index on the very first call.
		int[][] dp = new int[n][4];

		// Initialize every cell of dp to –1, which we’ll treat as “not yet computed.”
		// This lets us check dp[day][last] != -1 to know we’ve already solved that
		// subproblem.
		for (int i = 0; i < dp.length; i++) {
			// Loop over each day (row) in the DP table.
			for (int j = 0; j < dp[i].length; j++) {
				// Loop over each possible 'last task' (column) for that day.
				dp[i][j] = -1; // Mark state (i, j) as uninitialized.
			}
		}
		System.out.println(memoization(arr.length - 1, 3, arr, dp));

		System.out.println(tabulation(3, arr));

		System.out.println(optimized(3, arr));

		System.out.println(optimized2(3, arr));

		System.out.println(optimized3(3, arr));
	}

//	recursion(day=2, last=3)
//	│
//	├─ [task=0] points[2][0]=14 + recursion(day=1, last=0)
//	│   │
//	│   ├─ [task=1] points[1][1]=16 + recursion(day=0, last=1)
//	│   │   └─ recursion(day=0, last=1):
//	│   │       • consider tasks ≠1 → task0=17, task2=17 → return 17
//	│   │   → subtotal = 16 + 17 = 33
//	│   │
//	│   └─ [task=2] points[1][2]=5 + recursion(day=0, last=2)
//	│       └─ recursion(day=0, last=2):
//	│           • consider tasks ≠2 → task0=17, task1=2 → return 17
//	│       → subtotal = 5 + 17 = 22
//	│
//	│   recursion(day=1, last=0) returns max(33, 22) = 33
//	│   → branch total = 14 + 33 = 47
//	│
//	├─ [task=1] points[2][1]=3 + recursion(day=1, last=1)
//	│   │
//	│   ├─ [task=0] points[1][0]=16 + recursion(day=0, last=0)
//	│   │   └─ recursion(day=0, last=0):
//	│   │       • consider tasks ≠0 → task1=2, task2=17 → return 17
//	│   │   → subtotal = 16 + 17 = 33
//	│   │
//	│   └─ [task=2] points[1][2]=5 + recursion(day=0, last=2)
//	│       └─ recursion(day=0, last=2):
//	│           • as before → return 17
//	│       → subtotal = 5 + 17 = 22
//	│
//	│   recursion(day=1, last=1) returns max(33, 22) = 33
//	│   → branch total = 3 + 33 = 36
//	│
//	└─ [task=2] points[2][2]=19 + recursion(day=1, last=2)
//	    │
//	    ├─ [task=0] points[1][0]=16 + recursion(day=0, last=0)
//	    │   └─ recursion(day=0, last=0):
//	    │       • as before → return 17
//	    │   → subtotal = 16 + 17 = 33
//	    │
//	    └─ [task=1] points[1][1]=16 + recursion(day=0, last=1)
//	        └─ recursion(day=0, last=1):
//	            • as before → return 17
//	        → subtotal = 16 + 17 = 33
//
//	    recursion(day=1, last=2) returns max(33, 33) = 33
//	    → branch total = 19 + 33 = 52
//
//	recursion(day=2, last=3) returns max(47, 36, 52) = 52

	// tabulation
//	Day 1
//	For each last = 0…3, we choose the best “today’s task” ≠ last, adding yesterday’s best for that task:
//
//	last=0: can do tasks 1 or 2
//
//	do 1 → 16 + dp[0][1]=16+17=33
//
//	do 2 → 5 + dp[0][2]= 5+17=22
//	→ dp[1][0]=33
//
//	last=1: can do 0 or 2
//
//	do 0 → 16+17=33
//
//	do 2 → 5+17=22
//	→ dp[1][1]=33
//
//	last=2: can do 0 or 1
//
//	do 0 → 16+17=33
//
//	do 1 → 16+17=33
//	→ dp[1][2]=33
//
//	last=3 (“no restriction”): can do 0,1,2
//
//	do 0 → 16+17=33
//
//	do 1 → 16+17=33
//
//	do 2 → 5+17=22
//	→ dp[1][3]=33
//
//	Day 2
//	Now repeat for day 2’s points:
//
//	last=0: tasks 1 or 2
//
//	do 1 → 3 + dp[1][1]= 3+33=36
//
//	do 2 → 19 + dp[1][2]=19+33=52
//	→ dp[2][0]=52
//
//	last=1: tasks 0 or 2
//
//	do 0 → 14+33=47
//
//	do 2 → 19+33=52
//	→ dp[2][1]=52
//
//	last=2: tasks 0 or 1
//
//	do 0 → 14+33=47
//
//	do 1 → 3+33=36
//	→ dp[2][2]=47
//
//	last=3: tasks 0,1,2
//
//	do 0 → 14+33=47
//
//	do 1 → 3+33=36
//
//	do 2 → 19+33=52
//	→ dp[2][3]=52
//
//	Result
//	The answer is dp[n-1][3] = dp[2][3] = 52.

}
