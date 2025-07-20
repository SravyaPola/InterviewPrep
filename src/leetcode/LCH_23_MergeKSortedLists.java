package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LCH_23_MergeKSortedLists {

	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public ListNode mergeKLists1(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		for (int i = 1; i < lists.length; ++i) {
			lists[0] = merge1(lists[0], lists[i]);
		}
		return lists[0];
	}

	private ListNode merge1(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode result = dummy;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				result.next = l1;
				result = result.next;
				l1 = l1.next;
			} else {
				result.next = l2;
				result = result.next;
				l2 = l2.next;
			}
		}
		ListNode rest = (l1 != null) ? l1 : l2;
		result.next = rest;
		return dummy.next;
	}
	// O(n * k)
	// O(1)

	// Approach 2 -- PriorityQueue ----- Very Important
	// -- Referrred Take you forward
	private Comparator<ListNode> cmp = (ListNode a, ListNode b) -> {
		return a.val - b.val;
	};

	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		PriorityQueue<ListNode> pq = new PriorityQueue<>(cmp);
		for (ListNode head : lists) {
			if (head != null) {
				pq.add(head);
			}
		}
		ListNode dummy = new ListNode();
		ListNode tail = dummy;
		while (!pq.isEmpty()) {
			ListNode smallest = pq.poll();
			tail.next = smallest;
			tail = tail.next;
			if (smallest.next != null) {
				pq.add(smallest.next);
			}
		}
		return dummy.next;
	}
	// O(Nlogk)
	// O(k)
}
