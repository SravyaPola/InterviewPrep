package leetcode;

/**
 * LeetCode 110: Balanced Binary Tree
 *
 * Time complexity: O(N) — each node is visited once in the worst case. Space
 * complexity: O(N) — recursion stack in the worst case (skewed tree of depth
 * N).
 */
public class LCE_110_BalancedBT {
	/**
	 * Determines whether the given binary tree is height-balanced.
	 *
	 * A binary tree is height-balanced if for every node, the heights of its left
	 * and right subtrees differ by no more than 1.
	 *
	 * @param root the root of the binary tree
	 * @return true if the tree is balanced, false otherwise
	 */
	public boolean isBalanced(TreeNode root) {
		// helper() returns -1 if unbalanced; otherwise the subtree height.
		return helper(root) != -1;
	}

	/**
	 * Recursively computes the height of the subtree rooted at `node`, while also
	 * checking balance.
	 *
	 * @param node the current tree node
	 * @return the height of the subtree if balanced, or -1 if unbalanced
	 */
	private int helper(TreeNode node) {
		if (node == null) {
			// An empty subtree has height 0 and is trivially balanced.
			return 0;
		}

		// Compute height of left subtree
		int leftHeight = helper(node.left);
		if (leftHeight == -1) {
			// Left subtree is unbalanced; propagate failure upward.
			return -1;
		}

		// Compute height of right subtree
		int rightHeight = helper(node.right);
		if (rightHeight == -1) {
			// Right subtree is unbalanced; propagate failure upward.
			return -1;
		}

		// If the current node's subtrees differ in height by more than 1,
		// this subtree is unbalanced.
		if (Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}

		// Otherwise, return the height of this subtree:
		// max of child heights + 1 for the current node.
		return Math.max(leftHeight, rightHeight) + 1;
	}
}
