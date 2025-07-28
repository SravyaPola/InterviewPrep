package leetcode;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class LCM_515_FindLargestInEachTreeRow {

	public List<Integer> largestValues(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			int size = q.size();
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < size; i++) {
				TreeNode temp = q.remove();
				max = Math.max(max, temp.val);
				if (temp != null && temp.left != null) {
					q.add(temp.left);
				}
				if (temp != null && temp.right != null) {
					q.add(temp.right);
				}
			}
			result.add(max);
		}
		return result;
	}
}
