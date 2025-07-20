package leetcode;

import java.util.List;
import java.util.ArrayList;

public class LCM_545_BoundaryOfBT {
	List<Integer> result = new ArrayList<>();

	public List<Integer> boundaryOfBinaryTree(TreeNode root) {
		if (root == null) {
			return result;
		}
		if (root.left != null || root.right != null) {
			result.add(root.val);
		}
		leftBoundary(root.left);
		leaves(root);
		rightBoundary(root.right);

		return result;
	}

	private void leftBoundary(TreeNode root) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			return;
		}
		result.add(root.val);
		if (root.left == null) {
			leftBoundary(root.right);
		} else {
			leftBoundary(root.left);
		}
	}

	private void rightBoundary(TreeNode root) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			return;
		}
		if (root.right == null) {
			rightBoundary(root.left);
		} else {
			rightBoundary(root.right);
		}
		result.add(root.val);
	}

	private void leaves(TreeNode root) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			result.add(root.val);
			return;
		}
		leaves(root.left);
		leaves(root.right);
	}
}
