package leetcode;

import java.util.List;
import java.util.ArrayList;

public class LCE_145_BTPostOrder {

	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> ls = new ArrayList<>();
		helper(ls, root);
		return ls;
	}

	private void helper(List<Integer> ls, TreeNode root) {
		if (root == null) {
			return;
		}
		helper(ls, root.left);
		helper(ls, root.right);
		ls.add(root.val);
	}

}
