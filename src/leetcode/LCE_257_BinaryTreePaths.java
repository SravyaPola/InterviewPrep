package leetcode;

import java.util.List;
import java.util.ArrayList;

public class LCE_257_BinaryTreePaths {

	public List<String> binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<>();
		List<Integer> curr = new ArrayList<>();
		if (root != null)
			helper(root, curr, result);
		return result;
	}

	private void helper(TreeNode root, List<Integer> curr, List<String> result) {
		curr.add(root.val);
		if (root.left == null && root.right == null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < curr.size(); i++) {
				sb.append(curr.get(i));
				if (i < curr.size() - 1) {
					sb.append("->");
				}
			}
			result.add(sb.toString());
		} else {
			if (root.left != null)
				helper(root.left, curr, result);
			if (root.right != null)
				helper(root.right, curr, result);
		}
		curr.remove(curr.size() - 1);
	}

	public List<String> binaryTreePaths2(TreeNode root) {
		List<String> result = new ArrayList<>();
		List<Integer> curr = new ArrayList<>();
		helper2(root, curr, result);
		return result;
	}

	private void helper2(TreeNode root, List<Integer> curr, List<String> result) {
		if (root == null) {
			return;
		}
		curr.add(root.val);
		if (root.left == null && root.right == null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < curr.size(); i++) {
				sb.append(curr.get(i));
				if (i < curr.size() - 1) {
					sb.append("->");
				}
			}
			result.add(sb.toString());
		}
		helper(root.left, curr, result);
		helper(root.right, curr, result);
		curr.remove(curr.size() - 1);
	}
}
