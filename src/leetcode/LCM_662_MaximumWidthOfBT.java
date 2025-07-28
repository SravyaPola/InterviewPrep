package leetcode;

import java.util.*;

public class LCM_662_MaximumWidthOfBT {
	private static class Pair<K, V> {
		final K key;
		final V val;

		Pair(K k, V v) {
			key = k;
			val = v;
		}

		K getKey() {
			return key;
		}

		V getValue() {
			return val;
		}
	}

	public int widthOfBinaryTree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int ans = 0;
		Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
		q.add(new Pair(root, 0));
		while (!q.isEmpty()) {
			int size = q.size();
			int min = q.peek().getValue();
			int first = 0;
			int last = 0;
			for (int i = 0; i < size; i++) {
				int curr = q.peek().getValue() - min;
				TreeNode currNode = q.peek().getKey();
				q.poll();
				if (i == 0) {
					first = curr;
				}
				if (i == size - 1) {
					last = curr;
				}
				if (currNode.left != null) {
					q.add(new Pair(currNode.left, curr * 2 + 1));
				}
				if (currNode.right != null) {
					q.add(new Pair(currNode.right, curr * 2 + 2));
				}
			}
			ans = Math.max(ans, last - first + 1);
		}
		return ans;
	}

}
