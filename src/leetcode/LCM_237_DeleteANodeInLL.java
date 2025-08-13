package leetcode;

public class LCM_237_DeleteANodeInLL {
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}
}
