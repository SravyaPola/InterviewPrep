package leetcode;

import java.util.Map;
import java.util.HashMap;

/**
 * Solutions to LeetCode #70: Climbing Stairs.
 * 
 * The problem: You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can climb either 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 */
public class LCE_70_ClimbingStairs {

	/**
	 * 1) Pure recursive solution (exponential time).
	 *
	 * Idea: - At step n, you can come from step n-1 (by taking 1 step) or from step
	 * n-2 (by taking 2 steps). - So total ways to reach n = ways(n-1) + ways(n-2).
	 * - Base cases: ways(0)=1 (one way to “stay” at the bottom), ways(1)=1 (only
	 * one single-step move).
	 *
	 * Time complexity: O(2^n) — very slow for large n!
	 */
	public int climbStairs1(int n) {
		// Base cases: if no steps or just one step, there's exactly one way.
		if (n == 0 || n == 1) {
			return 1;
		}
		// Recursive case: sum of the two previous results.
		return climbStairs1(n - 1) + climbStairs1(n - 2);
	}

	/**
	 * 2) Recursive solution with memoization (top-down DP).
	 *
	 * Idea: - Same recurrence as climbStairs1, but cache results so each n is
	 * computed only once. - Use a Map (or array) as “memo” to avoid redundant work.
	 *
	 * Time complexity: O(n) Space complexity: O(n) for the call stack + memo map.
	 */
	public int climbStairs2(int n) {
		// Create memoization map to store computed results.
		Map<Integer, Integer> memo = new HashMap<>();
		return climbStairs2(n, memo);
	}

	private int climbStairs2(int n, Map<Integer, Integer> memo) {
		// Base cases: exactly one way to stand still or take one step.
		if (n == 0 || n == 1) {
			return 1;
		}
		// If we haven't yet computed ways(n), do so now.
		if (!memo.containsKey(n)) {
			// Store the computed sum of the two previous states.
			memo.put(n, climbStairs2(n - 1, memo) // ways to get to n from n-1
					+ climbStairs2(n - 2, memo) // ways to get to n from n-2
			);
		}
		// Return the cached result.
		return memo.get(n);
	}

	/**
	 * 3) Bottom-up dynamic programming (DP array).
	 *
	 * Idea: - Build up an array dp[] where dp[i] = ways to climb i steps. - Start
	 * from dp[0]=1, dp[1]=1, then fill dp[2], dp[3], …, dp[n].
	 *
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	public int climbStairs3(int n) {
		// Base cases: if n is small, we still return 1.
		if (n == 0 || n == 1) {
			return 1;
		}
		// Create dp array of size n+1.
		int[] dp = new int[n + 1];
		// Initialize the known starting values.
		dp[0] = 1; // one way to do nothing
		dp[1] = 1; // one way to take one step

		// Fill dp[i] for i = 2 to n.
		for (int i = 2; i <= n; i++) {
			// Recurrence: from i-1 (one-step) + from i-2 (two-steps).
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		// dp[n] now holds the total number of ways.
		return dp[n];
	}

	/**
	 * 4) Space-optimized DP (two variables).
	 *
	 * Idea: - We only ever need the last two values (prev and curr). - Roll forward
	 * updating these two variables without using an array.
	 *
	 * Time complexity: O(n) Space complexity: O(1)
	 */
	public int climbStairs4(int n) {
		// Base cases: still return 1 for n=0 or n=1.
		if (n == 0 || n == 1) {
			return 1;
		}
		// prev = ways(i-2), curr = ways(i-1) at each step.
		int prev = 1; // dp[0]
		int curr = 1; // dp[1]

		// Iterate from step 2 up to n, updating “prev” and “curr”.
		for (int i = 2; i <= n; i++) {
			int next = prev + curr; // ways to reach step i
			prev = curr; // shift window: old curr becomes new prev
			curr = next; // next becomes the new curr
		}
		// curr now holds dp[n].
		return curr;
	}
}
