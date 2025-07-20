package leetcode; // declares that this class is part of the 'leetcode' package

public class LCM_213_HouseRobberII {

	// Main helper that solves the “House Robber” for a linear street of houses
	public static int rob(int[] nums) {
		// If input is null or there are no houses, nothing to rob
		if (nums == null || nums.length == 0)
			return 0;

		int n = nums.length; // number of houses
		int prev = nums[0]; // prev holds the best we can do up to the previous house
		int prev2 = 0; // prev2 holds the best up to the house before the previous

		// Iterate through each house
		for (int i = 0; i < n; i++) {
			int take = nums[i]; // if we take (rob) current house i, we start with its value
			if (i > 1) {
				// if there's a house two steps back, add the best up to that house
				take = take + prev2;
			}
			int skip = 0 + prev; // if we skip current house, best remains what we had at previous
			int curr = Math.max(take, skip); // choose the better of robbing vs. skipping

			// shift our window forward:
			prev2 = prev; // what was prev becomes prev2
			prev = curr; // and curr becomes the new prev
		}

		return prev; // at the end, prev holds the best total
	}

	public static void main(String[] args) {
		int[] arr = { 2, 3, 2 }; // example circular arrangement of three houses

		int[] temp1 = new int[arr.length]; // will hold houses 1..n-1 (skip house 0)
		int[] temp2 = new int[arr.length]; // will hold houses 0..n-2 (skip last house)

		// If there's only one house, you can't have adjacent conflicts
		if (arr.length == 1) {
			System.out.println("No adjacents");
			return; // exit early
		}

		// Build the two scenarios:
		// 1) Rob houses from index 1 to end (skip the very first house)
		// 2) Rob houses from index 0 to second-to-last (skip the very last house)
		for (int i = 0; i < arr.length; i++) {
			if (i != 0) {
				temp1[i] = arr[i]; // copy everything except the 0th into temp1
			}
			if (i != arr.length - 1) {
				temp2[i] = arr[i]; // copy everything except the last into temp2
			}
		}

		// Compute max for both linear scenarios and print the greater:
		System.out.println(Math.max(rob(temp1), rob(temp2)));
	}
}
