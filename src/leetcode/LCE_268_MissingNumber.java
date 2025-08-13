package leetcode;

public class LCE_268_MissingNumber {
	public int missingNumber(int[] nums) {
		int n = nums.length;
		int sum = n * (n + 1) / 2;
		int curr = 0;
		for (int i = 0; i < n; i++) {
			curr = curr + nums[i];
		}
		return sum - curr;
	}

}
