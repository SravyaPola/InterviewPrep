package leetcode;

import java.util.*;

public class LCM_129_SumRootToLeafNumbers {
	int[] sum = new int[1];

	public int sumNumbers(TreeNode root) {
		if (root != null)
			helper(root, sum, new ArrayList<>());
		return sum[0];
	}

	private void helper(TreeNode root, int[] sum, List<Integer> curr) {
		curr.add(root.val);
		if (root.left == null && root.right == null) {
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < curr.size(); i++) {
				str.append(curr.get(i));
			}
			sum[0] = sum[0] + Integer.valueOf(str.toString());
		} else {
			if (root.left != null) {
				helper(root.left, sum, curr);
			}
			if (root.right != null) {
				helper(root.right, sum, curr);
			}
		}
		curr.remove(curr.size() - 1);
	}

	/// Approach 2

	int total;

	public int sumNumbers2(TreeNode root) {
		total = 0;
		helper(root, 0);
		return total;
	}

	private void helper(TreeNode root, int i) {
		if (root == null) {
			return;
		}
		i = i * 10 + root.val;

		if (root.left == null && root.right == null) {
			total = total + i;
		}
		helper(root.left, i);
		helper(root.right, i);
	}

}
