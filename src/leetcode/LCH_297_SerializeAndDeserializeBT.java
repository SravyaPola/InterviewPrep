package leetcode;

import java.util.*;

public class LCH_297_SerializeAndDeserializeBT {

	public String serialize(TreeNode root) {
		if (root == null) {
			return "";
		}
		Queue<TreeNode> q = new LinkedList<>();
		StringBuilder result = new StringBuilder();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode temp = q.poll();
			if (temp == null) {
				result.append("n ");
				continue;
			}
			result.append(temp.val + " ");
			q.add(temp.left);
			q.add(temp.right);
		}
		return result.toString();
	}

	public TreeNode deserialize(String data) {
		if (data == "") {
			return null;
		}
		Queue<TreeNode> q = new LinkedList<>();
		String[] values = data.split(" ");
		TreeNode root = new TreeNode(Integer.parseInt(values[0]));
		q.add(root);
		for (int i = 1; i < values.length; i++) {
			TreeNode current = q.poll();
			if (!values[i].equals("n")) {
				TreeNode left = new TreeNode(Integer.parseInt(values[i]));
				current.left = left;
				q.add(left);
			}
			if (!values[++i].equals("n")) {
				TreeNode right = new TreeNode(Integer.parseInt(values[i]));
				current.right = right;
				q.add(right);
			}
		}
		return root;
	}

}
