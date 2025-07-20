package leetcode;

// There is a frog on the 1st step of an N-stair staircase. The frog wants to reach the Nth stair.
// HEIGHT[i] is the height of the (i+1)th stair.
// If the frog jumps from the iᵗʰ to the jᵗʰ stair, the energy lost is |HEIGHT[j-1] − HEIGHT[i-1]|.
// From stair i the frog can jump to i+1 or i+2. Find the minimum total energy to reach stair N.

public class FrogJump {

	public static void main(String[] args) {
		int[] arr = { 10, 20, 30, 10 };
		// Call whichever approach you want: here we're using the space-optimized DP
		System.out.println(solution(arr)); // expected output: 20
	}

	// Dispatcher: uncomment exactly one of the four solX calls below
	private static int solution(int[] arr) {
		// Pure recursion (exponential time, no extra space aside from call stack)
		// return sol1(arr.length - 1, arr);

		// Top-down memoization (O(N) time, O(N) space for dp[] + call stack)
		// int[] dp = new int[arr.length];
		// for (int i = 0; i < arr.length; i++) {
		// dp[i] = -1;
		// }
		// return sol2(arr.length - 1, arr, dp);

		// Bottom-up tabulation (O(N) time, O(N) space for dp[])
		// return sol3(arr);

		// Space-optimized tabulation (O(N) time, O(1) extra space)
		return sol4(arr);
	}

	// 1) Pure recursion
	// - Recurrence: dp(i) = min(dp(i-1) + cost(i-1→i), dp(i-2) + cost(i-2→i))
	// - Base case: i==0 -> 0
	// - Time: O(2^N), Space: O(N) call stack
	private static int sol1(int i, int[] arr) {
		if (i == 0) {
			// At the first stair: no energy spent
			return 0;
		}
		// Jump from i-1 to i
		int left = sol1(i - 1, arr) + Math.abs(arr[i] - arr[i - 1]);

		// Jump from i-2 to i (only if possible)
		int right = Integer.MAX_VALUE;
		if (i > 1) {
			right = sol1(i - 2, arr) + Math.abs(arr[i] - arr[i - 2]);
		}

		// Return the cheaper of the two options
		return Math.min(left, right);
	}

	// 2) Top-down DP with memoization
	// - Same recurrence as sol1, but store results in dp[] to avoid recomputation
	// - Time: O(N), Space: O(N) for dp[] + O(N) call stack
	private static int sol2(int i, int[] arr, int[] dp) {
		if (i == 0) {
			return 0;
		}
		if (dp[i] != -1) {
			// Return already computed result
			return dp[i];
		}

		int left = sol2(i - 1, arr, dp) + Math.abs(arr[i] - arr[i - 1]);

		int right = Integer.MAX_VALUE;
		if (i > 1) {
			right = sol2(i - 2, arr, dp) + Math.abs(arr[i] - arr[i - 2]);
		}

		// Memoize and return
		dp[i] = Math.min(left, right);
		return dp[i];
	}

	// 3) Bottom-up tabulation
	// - Build dp[] from i=0 up to i=n-1
	// - Time: O(N), Space: O(N)
	private static int sol3(int[] arr) {
		int n = arr.length;
		int[] dp = new int[n];
		dp[0] = 0; // cost to reach first stair

		for (int i = 1; i < n; i++) {
			int left = dp[i - 1] + Math.abs(arr[i] - arr[i - 1]);
			int right = Integer.MAX_VALUE;
			if (i > 1) {
				right = dp[i - 2] + Math.abs(arr[i] - arr[i - 2]);
			}
			dp[i] = Math.min(left, right);
		}

		// Answer is cost to reach the last stair
		return dp[n - 1];
	}

	// 4) Space-optimized tabulation
	// - Only keep the last two dp values (dp[i-1] and dp[i-2])
	// - Time: O(N), Space: O(1)
	private static int sol4(int[] arr) {
		int n = arr.length;
		if (n == 1) {
			// Only one stair: no jumps needed
			return 0;
		}

		// prev2 = dp[i-2], prev1 = dp[i-1]
		int prev2 = 0; // dp[0]
		int prev1 = Math.abs(arr[1] - arr[0]); // dp[1]

		if (n == 2) {
			// Only two stairs: single jump cost
			return prev1;
		}

		int curr = 0;
		for (int i = 2; i < n; i++) {
			// Option 1: from stair i-1
			int left = prev1 + Math.abs(arr[i] - arr[i - 1]);
			// Option 2: from stair i-2
			int right = prev2 + Math.abs(arr[i] - arr[i - 2]);

			curr = Math.min(left, right);

			// Slide window for next iteration
			prev2 = prev1;
			prev1 = curr;
		}

		return curr; // dp[n-1]
	}
}
