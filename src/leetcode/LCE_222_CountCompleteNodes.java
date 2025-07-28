package leetcode;

public class LCE_222_CountCompleteNodes {
	public int countNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = getLeftHeight(root);
		int right = getRightHeight(root);
		if (left == right) {
			return (1 << left) - 1;
		}
		return 1 + countNodes(root.left) + countNodes(root.right);
	}

	private int getRightHeight(TreeNode root) {
		int count = 0;
		while (root != null) {
			count++;
			root = root.right;
		}
		return count;
	}

	private int getLeftHeight(TreeNode root) {
		int count = 0;
		while (root != null) {
			count++;
			root = root.left;
		}
		return count;
	}
}
