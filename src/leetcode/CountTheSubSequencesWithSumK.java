package leetcode;

public class CountTheSubSequencesWithSumK {
	public int subarraySum(int[] nums, int k) {
		int count = 0;
		int index = 0;
		int sum = 0;
		count = helper(index, sum, k, nums);
		return count;
	}

	private int helper(int index, int sum, int k, int[] nums) {
		if (index == nums.length) {
			if (sum == k) {
				return 1;
			} else {
				return 0;
			}
		}
		sum = sum + nums[index];
		int left = helper(index + 1, sum, k, nums);
		sum = sum - nums[index];
		int right = helper(index + 1, sum, k, nums);
		return left + right;
	}
// 2 ^ n
}
