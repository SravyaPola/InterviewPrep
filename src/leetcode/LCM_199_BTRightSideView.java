package leetcode;

import java.util.List;
import java.util.ArrayList;

/**
 * Computes the right-side (or left-side) view of a binary tree. The right-side
 * view is the list of node values visible when the tree is viewed from the
 * right side; similarly for the left side.
 */
public class LCM_199_BTRightSideView {

	/**
	 * Returns the list of node values visible from the right side of the tree.
	 *
	 * @param root the root of the binary tree
	 * @return a list of integers representing the right-side view
	 */
	public List<Integer> rightSideView(TreeNode root) {
		// Initialize the result list that will hold the visible values
		List<Integer> result = new ArrayList<>();
		// Kick off the helper that prioritizes the right subtree first
		helperRight(root, 0, result);
		return result;

		// If you'd like the left-side view instead, uncomment below:
		// helperLeft(root, 0, result);
		// return result;
	}

	/**
	 * Recursive helper that traverses the tree in right-first order. Ensures that
	 * at each depth (level) we record the first node encountered, which will be the
	 * rightmost node for that level.
	 *
	 * @param root   the current node
	 * @param level  the current depth in the tree (root is level 0)
	 * @param result the list accumulating the first-seen node values per level
	 */
	private void helperRight(TreeNode root, int level, List<Integer> result) {
		// Base case: if node is null, there's nothing to do
		if (root == null) {
			return;
		}
		// If we're visiting this level for the first time,
		// the result list size equals the current level
		if (level == result.size()) {
			// Add the current node's value: it's the rightmost at this level
			result.add(root.val);
		}
		// Recurse right first to capture the right-side nodes
		helperRight(root.right, level + 1, result);
		// Then recurse left to fill in any missing levels deeper down
		helperRight(root.left, level + 1, result);
	}

	/**
	 * Alternative recursive helper that traverses the tree in left-first order.
	 * This captures the left-side view by recording the first node seen at each
	 * level when coming from the left.
	 *
	 * @param root   the current node
	 * @param level  the current depth in the tree (root is level 0)
	 * @param result the list accumulating the first-seen node values per level
	 */
	private void helperLeft(TreeNode root, int level, List<Integer> result) {
		// Base case: nothing to process if node is null
		if (root == null) {
			return;
		}
		// On first visit to this level, add the node's value
		if (level == result.size()) {
			result.add(root.val);
		}
		// Traverse left subtree first to capture leftmost nodes
		helperLeft(root.left, level + 1, result);
		// Then traverse right subtree for any deeper levels
		helperLeft(root.right, level + 1, result);
	}
}
