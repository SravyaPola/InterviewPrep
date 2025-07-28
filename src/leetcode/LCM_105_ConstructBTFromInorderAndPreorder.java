package leetcode;

import java.util.*;

public class LCM_105_ConstructBTFromInorderAndPreorder {

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}

		TreeNode root = helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
		return root;
	}

	private TreeNode helper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd,
			Map<Integer, Integer> map) {
		if (preStart > preEnd || inStart > inEnd) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preStart]);

		int inRoot = map.get(root.val);
		int numsLeft = inRoot - inStart;

		root.left = helper(preorder, preStart + 1, preStart + numsLeft, inorder, inStart, inRoot - 1, map);
		root.right = helper(preorder, preStart + numsLeft + 1, preEnd, inorder, inRoot + 1, inEnd, map);
		return root;
	}

}
