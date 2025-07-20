package leetcode;

/**
 * LeetCode 101. Symmetric Tree Determines whether a binary tree is a mirror of
 * itself (i.e., symmetric around its center).
 */
public class LCE_101_SymmetricTree {

	/**
	 * Main entry: checks if the tree is symmetric.
	 *
	 * @param root the root of the binary tree
	 * @return true if the tree is symmetric, false otherwise
	 */
	public boolean isSymmetric(TreeNode root) {
		// An empty tree is symmetric by definition
		if (root == null) {
			return true;
		}
		// Compare the left and right subtrees for mirror symmetry
		return helper(root.left, root.right);
	}

	/**
	 * Recursively compares two subtrees to verify mirror symmetry.
	 *
	 * @param left  the root of the left subtree
	 * @param right the root of the right subtree
	 * @return true if the two subtrees are mirror images of each other
	 */
	private boolean helper(TreeNode left, TreeNode right) {
		// If both nodes are null, they match symmetrically
		if (left == null || right == null) {
			// Only symmetric if both are null; otherwise false
			return left == right;
		}
		// If values differ, symmetry is broken
		if (left.val != right.val) {
			return false;
		}
		// Recursively check the "outer" and "inner" pairs:
		// left.left vs. right.right AND left.right vs. right.left
		return helper(left.left, right.right) && helper(left.right, right.left);
	}
}
