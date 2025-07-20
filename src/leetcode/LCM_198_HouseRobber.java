package leetcode;

public class LCM_198_HouseRobber {
	public static int rob1(int[] nums, int index) { // brute-force recursion entry: nums array and current index
		if (index == 0) { // base case: if at first house
			return nums[0]; // return its value
		}
		if (index < 0) { // base case: if index is before first house
			return 0; // nothing to rob
		}
		int pick = nums[index] + rob1(nums, index - 2); // option: rob this house + best till two houses before
		int notpick = 0 + rob1(nums, index - 1); // option: skip this house + best till previous house
		return Math.max(pick, notpick); // choose the better option
	}

	public static int robMemo(int[] nums, int i, int[] memo) { // top-down DP with memoization
		if (i < 0) // if index is before first house
			return 0; // nothing to rob
		if (memo[i] != -1) // if already computed for this index
			return memo[i]; // return cached result
		int skip = robMemo(nums, i - 1, memo); // compute best if we skip current house
		int take = robMemo(nums, i - 2, memo) + nums[i]; // compute best if we take current house
		return memo[i] = Math.max(skip, take); // cache and return the better option
	}

	public static int robDp(int[] nums) { // bottom-up DP solution
		if (nums == null || nums.length == 0) // handle empty input
			return 0; // no houses to rob

		int n = nums.length; // number of houses
		int[] dp = new int[n]; // dp[i] = best we can rob from houses 0..i
		dp[0] = nums[0]; // base case: only first house

		for (int i = 1; i < n; i++) { // iterate from second house to last
			int take = nums[i]; // if we take current house
			if (i > 1) { // if there's a house two steps before
				take = take + dp[i - 2]; // add its best result
			}
			int skip = 0 + dp[i - 1]; // if we skip current, take previous best
			dp[i] = Math.max(take, skip); // choose the better
		}
		return dp[n - 1]; // best for all houses
	}

	public static int robOptimized(int[] nums) { // space-optimized DP (O(1) space)
		if (nums == null || nums.length == 0) // handle empty input
			return 0; // no houses to rob
		int n = nums.length; // number of houses
		int prev = nums[0]; // dp value for i-1 (initially house 0)
		int prev2 = 0; // dp value for i-2 (none before start)

		for (int i = 0; i < n; i++) { // iterate through all houses
			int take = nums[i]; // if we take current house
			if (i > 1) { // if there's a house two steps before
				take = take + prev2; // add dp for i-2
			}
			int skip = 0 + prev; // if we skip current, use dp for i-1
			int curr = Math.max(take, skip); // best for current i
			prev2 = prev; // shift window: prev becomes prev2
			prev = curr; // curr becomes new prev
		}
		return prev; // contains dp value for last house
	}

	public static void main(String[] args) { // main method to run examples
		int[] arr = { 1, 2, 3, 1 }; // example house values

		System.out.println(rob1(arr, arr.length - 1)); // print result of brute-force

		int[] memo = new int[arr.length]; // array for memoization
		for (int i = 0; i < memo.length; i++) { // initialize memo
			memo[i] = -1; // -1 indicates unsolved
		}
		System.out.println(robMemo(arr, arr.length - 1, memo)); // print memoized result

		System.out.println(robDp(arr)); // print bottom-up DP result

		System.out.println(robOptimized(arr)); // print optimized DP result
	}

}
