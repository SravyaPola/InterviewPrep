package leetcode;

import java.util.*;

public class LCM_102_LevelOrder {
	public List<List<Integer>> levelOrder(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		q.offer(root);
		while (!q.isEmpty()) {
			int size = q.size();
			List<Integer> sublist = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode temp = q.poll();
				if (temp != null) {
					if (temp.left != null) {
						q.add(temp.left);
					}
					if (temp.right != null) {
						q.add(temp.right);
					}
					sublist.add(temp.val);
				}
			}
			result.add(sublist);
		}
		return result;
	}

}
