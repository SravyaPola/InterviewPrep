package leetcode;

/**
 * LeetCode 543: Diameter of Binary Tree
 *
 * We define the diameter of a binary tree as the length (in number of edges) of
 * the longest path between any two nodes in the tree. This path may or may not
 * pass through the root.
 *
 * The key observation is that for any given node, the longest path that passes
 * through that node is equal to the height of its left subtree plus the height
 * of its right subtree. So we traverse the tree once, computing heights, and at
 * each node update a global maximum diameter.
 *
 * Time complexity: O(n), where n = number of nodes, since we visit each node
 * once. Space complexity: O(h), where h = height of the tree, due to the
 * recursion stack.
 */
public class LCE_543_DiameterOfBT {
	/**
	 * max stores the largest diameter (in edges) seen so far during our depth-first
	 * traversal. We make it a field so that every recursive call to helper() can
	 * update it.
	 */
	private int max = 0;

	/**
	 * Public API: returns the diameter (number of edges) of the binary tree rooted
	 * at `root`.
	 *
	 * @param root the root of the binary tree (may be null)
	 * @return the diameter measured in edges
	 */
	public int diameterOfBinaryTree(TreeNode root) {
		// Kick off the recursive depth calculation. As a side-effect,
		// this will update `max` to the true diameter.
		helper(root);
		// Once recursion finishes, `max` holds the maximum diameter across all nodes.
		return max;
	}

	/**
	 * Recursive helper method: 1. Computes the height of the subtree rooted at
	 * `root`. - Height is defined here as the number of nodes on the longest
	 * downward path from `root` to any leaf in its subtree. 2. Updates `max` to
	 * consider the path that goes through `root`, whose length in edges is
	 * (height_of_left_subtree + height_of_right_subtree).
	 *
	 * @param root current node in the recursion (null means empty subtree)
	 * @return height (in nodes) of this subtree
	 */
	private int helper(TreeNode root) {
		// === Base case ===
		// If we've descended past a leaf, the height of an empty subtree is 0.
		if (root == null) {
			return 0;
		}

		// === Recursive case ===
		// Compute the height (in nodes) of the left subtree.
		// This call will in turn update `max` for all descendants.
		int left = helper(root.left);

		// Similarly, compute the height of the right subtree.
		int right = helper(root.right);

		// === Update diameter via this node ===
		// A path that goes through `root` and extends to the deepest leaf on the left
		// and the deepest leaf on the right has length (in edges) = left + right.
		// (Since `left` and `right` are node-counts, the number of edges on each side
		// equals the node-count.)
		max = Math.max(max, left + right);

		// === Return height of this subtree ===
		// The height at this node is 1 (for `root` itself) plus the greater of
		// the heights of its two subtrees.
		return 1 + Math.max(left, right);
	}
}
