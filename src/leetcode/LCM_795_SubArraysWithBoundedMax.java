package leetcode;

public class LCM_795_SubArraysWithBoundedMax {
	public int numSubarrayBoundedMax(int[] nums, int left, int right) {
		int leftLength = 0;
		int rightLength = 0;
		int total = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= right) {
				rightLength++;
			} else {
				rightLength = 0;
			}
			if (nums[i] <= left - 1) {
				leftLength++;
			} else {
				leftLength = 0;
			}
			total = total + rightLength - leftLength;
		}
		return total;
	}
}
