package leetcode;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.TreeMap;

public class LCM_314_BTVerticalOrderTraversal {

	/**
	 * Performs a vertical order traversal of a binary tree.
	 * 
	 * @param root the root node of the binary tree
	 * @return a list of lists of node values, grouped by vertical column from
	 *         leftmost to rightmost
	 */
	public List<List<Integer>> verticalOrder(TreeNode root) {
		// Final result to return: each inner list holds all node values for one
		// vertical column
		List<List<Integer>> result = new ArrayList<>();

		// Edge case: empty tree => empty result
		if (root == null) {
			return result;
		}

		// TreeMap keeps columns in sorted (ascending) order of their horizontal
		// distance
		// Key = column index (0 for root), Value = list of node values in that column
		TreeMap<Integer, List<Integer>> tm = new TreeMap<>();

		// Two parallel queues for breadth-first traversal:
		// valueQ holds the tree nodes
		// columnQ holds the corresponding column index (horizontal distance) for each
		// node
		Queue<TreeNode> valueQ = new LinkedList<>();
		Queue<Integer> columnQ = new LinkedList<>();

		// Initialize BFS with root at column 0
		valueQ.add(root);
		columnQ.add(0);

		// Standard BFS loop: processes nodes level by level (ensures top-to-bottom
		// order within each column)
		while (!valueQ.isEmpty()) {
			TreeNode current = valueQ.poll(); // next node
			int column = columnQ.poll(); // its vertical column index

			// If this is the first time we've seen this column, create a list to hold its
			// nodes
			if (!tm.containsKey(column)) { // first time seeing this column?
				tm.put(column, new ArrayList<>()); // create new list for it
			}

			// Append the current node's value to the list for its column
			tm.get(column).add(current.val);

			// Enqueue left child with column - 1 (one step to the left)
			if (current.left != null) {
				valueQ.add(current.left);
				columnQ.add(column - 1);
			}

			// Enqueue right child with column + 1 (one step to the right)
			if (current.right != null) {
				valueQ.add(current.right);
				columnQ.add(column + 1);
			}
		}

		// Traverse the TreeMap in key order (from smallest column index to largest)
		// and collect each column’s list into the final result
		for (List<Integer> columnList : tm.values()) {
			result.add(columnList);
		}

		return result;
	}

}
