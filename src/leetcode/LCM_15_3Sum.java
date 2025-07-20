package leetcode;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class LCM_15_3Sum {

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);// So that duplicates will be adjacent to each other
		// 1. Iterate through each number as the first element of the triplet
		for (int i = 0; i < nums.length - 2; i++) {
			// 1a. Skip duplicates for the first element
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			// 2. Two-pointer setup for the remaining two elements
			int left = i + 1;
			int right = nums.length - 1;
			// 3. Move the two pointers inward until they meet
			while (left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if (sum == 0) {
					// 3a. Found a valid triplet
					result.add(Arrays.asList(nums[i], nums[left], nums[right]));
					// 3b. Skip duplicates for the second element
					while (left < right && nums[left] == nums[left + 1]) {
						left++;
					}
					// 3c. Skip duplicates for the third element
					while (left < right && nums[right] == nums[right - 1]) {
						right--;
					}
					// 3d. Move both pointers inward to look for new pairs
					left++;
					right--;
				} else if (sum < 0) {
					// 3e. Sum too small → need a larger value → move left pointer right
					left++;
				} else {
					// 3f. Sum too large → need a smaller value → move right pointer left
					right--;
				}
			}
		}
		// 4. Return all found triplets
		return result;
	}
}
