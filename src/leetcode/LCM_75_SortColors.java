package leetcode;

public class LCM_75_SortColors {
	public void sortColors(int[] nums) {
		// 'left' is the boundary for the next position of 0
		int left = 0;
		// 'right' is the boundary for the next position of 2
		int right = nums.length - 1;
		// 'i' is the current index we are inspecting
		int i = 0;

		// Process elements until 'i' passes 'right'
		while (i <= right) {
			if (nums[i] == 0) {
				// Found a 0: swap it to the 'left' region
				swap(nums, i, left);
				// Expand the 0-region
				left++;
				// Move on, since the element swapped in at 'i' must be ≥1
				i++;
			} else if (nums[i] == 2) {
				// Found a 2: swap it to the 'right' region
				swap(nums, i, right);
				// Shrink the 2-region
				right--;
				// Do NOT increment 'i' here, because the new nums[i]
				// (swapped from the end) must still be checked
			} else {
				// Found a 1: it's already in the correct middle region
				i++;
			}
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;

	}
//O(n) O(1)
}
