package leetcode;

import java.util.List;
import java.util.ArrayList;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

public class LCE_94_BTInorder {

	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> ls = new ArrayList<>();
		helper(ls, root);
		return ls;
	}

	private void helper(List<Integer> ls, TreeNode root) {
		if (root == null) {
			return;
		}
		helper(ls, root.left);
		ls.add(root.val);
		helper(ls, root.right);
	}

}
