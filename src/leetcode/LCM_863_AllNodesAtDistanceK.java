package leetcode;

import java.util.*;

public class LCM_863_AllNodesAtDistanceK {
	public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
		List<Integer> result = new ArrayList<>();
		Map<Integer, TreeNode> parent = new HashMap<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode temp = q.poll();
				if (temp.left != null) {
					parent.put(temp.left.val, temp);
					q.add(temp.left);
				}
				if (temp.right != null) {
					parent.put(temp.right.val, temp);
					q.add(temp.right);
				}
			}
		}
		Map<Integer, Integer> visited = new HashMap<>();
		q.add(target);
		while (k > 0 && !q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode temp = q.poll();
				visited.put(temp.val, 1);
				if (temp.left != null && !visited.containsKey(temp.left.val)) {
					q.add(temp.left);
				}
				if (temp.right != null && !visited.containsKey(temp.right.val)) {
					q.add(temp.right);
				}
				if (parent.containsKey(temp.val) && !visited.containsKey(parent.get(temp.val).val)) {
					q.add(parent.get(temp.val));
				}
			}
			k--;
		}
		while (!q.isEmpty()) {
			result.add(q.poll().val);
		}
		return result;
	}

}
