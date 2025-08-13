package leetcode;

/**
 * LeetCode Problem 1423: Maximum Points You Can Obtain from Cards
 *
 * Problem Summary: You have an array of integers cardPoints[] representing
 * points of cards arranged in a row. You can take exactly k cards from either
 * the beginning or the end of the array. Your goal is to maximize the total
 * points you obtain.
 *
 * Approach: Instead of brute-forcing all possible picks (which would be O(k²)),
 * we calculate: - First, the sum of the first k cards (taking all from the
 * left). - Then, gradually shift the selection by taking fewer cards from the
 * left and more from the right, updating the maximum sum seen so far. This is
 * done in O(k) time.
 */
public class LCM_1423_MaxPointsFromCards {

	public int maxScore(int[] cardPoints, int k) {
		// Sum of cards taken from the left side
		int leftSum = 0;
		// Sum of cards taken from the right side
		int rightSum = 0;
		// Track the maximum score obtained
		int maxSum = Integer.MIN_VALUE;

		// Step 1: Take all k cards from the left initially
		for (int i = 0; i < k; i++) {
			leftSum += cardPoints[i];
		}
		// Record the initial max (all from the left)
		maxSum = Math.max(maxSum, leftSum);

		// Step 2: Move the "window" from left to right
		// Gradually give up cards from the left and take from the right
		int rightIndex = cardPoints.length - 1;
		for (int leftIndex = k - 1; leftIndex >= 0; leftIndex--) {
			// Remove one card from the left sum
			leftSum -= cardPoints[leftIndex];
			// Add one card from the right sum
			rightSum += cardPoints[rightIndex];
			rightIndex--;
			// Update maximum score after this shift
			maxSum = Math.max(maxSum, leftSum + rightSum);
		}

		// Return the best score possible
		return maxSum;
	}
}
