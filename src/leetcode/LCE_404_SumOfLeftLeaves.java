package leetcode;

public class LCE_404_SumOfLeftLeaves {
	private int result = 0;

	public int sumOfLeftLeaves(TreeNode root) {
		result = helper(root, result, false);
		return result;
	}

	private int helper(TreeNode root, int result, boolean isLeft) {
		if (root == null) {
			return 0;
		}
		if (isLeft && root.left == null && root.right == null) {
			result = result + root.val;
			return result;
		}
		return helper(root.left, result, true) + helper(root.right, result, false);
	}
}
