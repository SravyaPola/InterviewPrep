package leetcode;

import java.util.List;
import java.util.ArrayList;

public class LCM_78_Subsets {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		int index = 0;
		List<Integer> ls = new ArrayList<>();
		helper(nums, index, ls, result);
		return result;

	}

	private void helper(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
		if (index >= nums.length) {
			result.add(new ArrayList<>(current));
			return;
		}
		// 1) INCLUDE nums[index] in the subset
		current.add(nums[index]);
		helper(nums, index + 1, current, result);
		// backtrack: remove last added element to restore state
		current.remove(current.size() - 1);
		// 2) EXCLUDE nums[index] from the subset
		helper(nums, index + 1, current, result);
	}
}
// Take you forward
// O(N * 2 ^ N)
// O(N)