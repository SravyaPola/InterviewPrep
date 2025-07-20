package leetcode;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class LCM_103_ZigzagLevelOrder {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		boolean flag = false;
		while (!q.isEmpty()) {
			int size = q.size();
			List<Integer> ls = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode temp = q.remove();
				if (temp != null) {
					if (flag) {
						ls.add(0, temp.val);
					} else {
						ls.add(temp.val);
					}
					if (temp.left != null) {
						q.add(temp.left);
					}
					if (temp.right != null) {
						q.add(temp.right);
					}
				}
			}
			result.add(ls);
			flag = !flag;
		}
		return result;
	}

}
