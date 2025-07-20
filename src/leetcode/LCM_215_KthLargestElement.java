package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LCM_215_KthLargestElement {

	private Comparator<Integer> cmp = (a, b) -> {
		return b.compareTo(a);
	};

	public int findKthLargest(int[] nums, int k) {
		// PriorityQueue<Integer> pq = new PriorityQueue<>(); -- Min Heap
		// PriorityQueue<Integer> pq = new
		// PriorityQueue<>(Collections.reverseOrder());// Max Heap
		PriorityQueue<Integer> pq = new PriorityQueue<>(cmp);
		for (int i : nums) {
			pq.add(i);
		}
		for (int i = 1; i < k; i++) {
			pq.poll();
		}
		return pq.peek();
	}
}
