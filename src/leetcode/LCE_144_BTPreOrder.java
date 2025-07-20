package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LCE_144_BTPreOrder {

	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> ls = new ArrayList<>();
		helper(ls, root);
		return ls;
	}

	private void helper(List<Integer> ls, TreeNode root) {
		if (root == null) {
			return;
		}
		ls.add(root.val);
		helper(ls, root.left);
		helper(ls, root.right);
	}

}
