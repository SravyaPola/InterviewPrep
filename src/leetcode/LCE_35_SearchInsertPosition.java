package leetcode;
//Lower bound = the first position in the sorted array where the value is greater than or equal to the target.

//Upper bound = the first position in the sorted array where the value is strictly greater than the target.

//Floor → the largest element ≤ target ------- upper bound

//Ceil → the smallest element ≥ target ------- lower bound

public class LCE_35_SearchInsertPosition {
	/**
	 * Finds the index at which the target should be inserted in a sorted array to
	 * maintain order. If the target exists, returns its index.
	 *
	 * This uses a binary search approach to achieve O(log n) time complexity.
	 *
	 * @param nums   A sorted array of integers (ascending order)
	 * @param target The integer value to search for or insert
	 * @return The index where target is found or should be inserted
	 */
	public int searchInsert(int[] nums, int target) {
		// Initialize the search boundaries
		int low = 0;
		int high = nums.length - 1;

		// Default answer is the end of the array (insert at last position if target >
		// all elements)
		int ans = nums.length;

		// Binary search loop
		while (low <= high) {
			// Calculate middle index
			int mid = (low + high) / 2;

			// If the mid element is greater than or equal to target:
			// - This could be the correct position for insertion (update ans)
			// - But we check to the left to see if there's an earlier valid position
			if (nums[mid] >= target) {
				ans = mid;
				high = mid - 1; // Move search to the left half
			} else {
				// If nums[mid] is less than target, search right half
				low = mid + 1;
			}
		}

		// Return the index where target should be inserted
		return ans;
	}

}
