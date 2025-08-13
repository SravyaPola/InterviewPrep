package leetcode;

public class LCM_189_RotateArray {
	public void rotateRight(int[] nums, int k) {
		k = k % nums.length;
		reverse(0, nums.length - 1, nums);
		reverse(0, k - 1, nums);
		reverse(k, nums.length - 1, nums);
	}

	public void rotateLeft(int[] nums, int k) {
		reverse(0, k - 1, nums);
		reverse(k, nums.length - 1, nums);
		reverse(0, nums.length - 1, nums);
	}

	private void reverse(int a, int b, int[] nums) {
		int left = a;
		int right = b;
		while (left < right) {
			int temp = nums[left];
			nums[left] = nums[right];
			nums[right] = temp;
			left++;
			right--;
		}
	}
}
