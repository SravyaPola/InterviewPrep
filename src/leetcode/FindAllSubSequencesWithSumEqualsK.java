package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindAllSubSequencesWithSumEqualsK {
	public static void subarraySum(int[] nums, int k) {
		int index = 0;
		int sum = 0;
		List<Integer> ls = new ArrayList<>();
		helper(nums, k, index, sum, ls);
		return;
	}

	private static void helper(int[] nums, int k, int index, int sum, List<Integer> ls) {
		if (index == nums.length) {
			if (sum == k) {
				System.out.println(ls);
			}
			return;
		}
		ls.add(nums[index]);
		sum = sum + nums[index];
		helper(nums, k, index + 1, sum, ls);
		ls.remove(ls.size() - 1);
		sum = sum - nums[index];
		helper(nums, k, index + 1, sum, ls);
	}

	public static void main(String[] args) {
		subarraySum(new int[] { 17, 18, 6, 11, 2, 4 }, 6);
	}

}
// 	Take u forward
//	Time Complexity: O(2 ^ n), as we are generating all possible subsequences.
//	Auxiliary Space: O(1)