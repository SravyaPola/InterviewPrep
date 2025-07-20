package leetcode;

public class LCE_509_FibonacciNumber {

	/**
	 * Approach 1: Top-down DP (Memoization)
	 *
	 * We recursively compute fib(n) but store (memoize) results in dp[] so that
	 * overlapping subproblems are not recomputed.
	 *
	 * Time Complexity: O(n) — each fib(k) for k ≤ n is computed only once. Space
	 * Complexity: O(n) for the recursion stack + O(n) for the dp array.
	 *
	 * @param n  the nth Fibonacci to compute
	 * @param dp memo array of size n+1, initialized to -1
	 * @return fib(n)
	 */
	public static int fibUsingDP1(int n, int[] dp) {
		// Base case: fib(0) = 0, fib(1) = 1
		if (n <= 1) {
			return n;
		}
		// If already computed, return from dp[]
		if (dp[n] != -1) {
			return dp[n];
		}
		// Compute, store in dp[n], and return
		dp[n] = fibUsingDP1(n - 1, dp) + fibUsingDP1(n - 2, dp);
		return dp[n];
	}

	/**
	 * Approach 2: Bottom-up DP (Tabulation)
	 *
	 * We build up the dp[] array from the bottom: start with dp[0] and dp[1], then
	 * fill dp[2] through dp[n].
	 *
	 * Time Complexity: O(n) — one loop of size n. Space Complexity: O(n) for the dp
	 * array.
	 *
	 * @param n  the nth Fibonacci to compute
	 * @param dp array of size n+1 (we will overwrite dp[0] and dp[1] here)
	 * @return fib(n)
	 */
	public static int fibUsingDP2(int n, int[] dp) {
		// Initialize base values
		dp[0] = 0;
		dp[1] = 1;
		// Build the table in increasing order
		for (int i = 2; i <= n; i++) {
			// Recurrence: fib(i) = fib(i-1) + fib(i-2)
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	/**
	 * Approach 3: Space-optimized DP (Rolling Variables)
	 *
	 * Since fib(i) only depends on the two previous values, we don't need the full
	 * dp array—just two variables.
	 *
	 * Time Complexity: O(n) — one loop of size n. Space Complexity: O(1) — only
	 * constant extra space.
	 *
	 * @param n the nth Fibonacci to compute
	 * @return fib(n)
	 */
	public static int fibUsingDP3(int n) {
		// Handle small n directly
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;

		// prev2 = fib(i-2), prev = fib(i-1)
		int prev2 = 0;
		int prev = 1;

		// Compute from i=2 up to n
		for (int i = 2; i <= n; i++) {
			// Current fib(i)
			int curr = prev + prev2;
			// Slide the window:
			// new prev2 becomes old prev,
			// new prev becomes curr
			prev2 = prev;
			prev = curr;
		}
		// At the end, prev holds fib(n)
		return prev;
	}

	/**
	 * Driver Code
	 *
	 * Demonstrates all three methods for n = 3.
	 */
	public static void main(String[] args) {
		int n = 3;

		// Prepare dp array for methods 1 & 2; fill with -1 for memoization
		int[] nums = new int[n + 1];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = -1;
		}

		// --- Method 1: Top-down with memo ---
		// Saves work by caching recursive results
		int result1 = fibUsingDP1(n, nums);
		System.out.println("fibUsingDP1(" + n + ") = " + result1);

		// --- Method 2: Bottom-up tabulation ---
		// Re-use the same array; overridden by fibUsingDP2
		int result2 = fibUsingDP2(n, nums);
		System.out.println("fibUsingDP2(" + n + ") = " + result2);

		// --- Method 3: Space-optimized iterative ---
		// Only two variables track state
		int result3 = fibUsingDP3(n);
		System.out.println("fibUsingDP3(" + n + ") = " + result3);
	}
	// Recursive + Memo (Top-down) avoids recomputation of overlapping subproblems,
	// but pays recursion-stack cost.

	// Iterative Table (Bottom-up) is straightforward and fast, but uses O(n) extra
	// memory.

	// Rolling Variables achieve the same O(n) time in O(1) space—ideal when n is
	// large but fits in an int.
}
