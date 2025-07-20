package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LCE_1046_LastStoneWeight {

	private Comparator<Integer> cmp = (a, b) -> {
		return b.compareTo(a);
	};

	public int lastStoneWeight(int[] stones) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(cmp);
		for (int i : stones) {
			pq.add(i);
		}
		while (!pq.isEmpty() && pq.size() > 1) {
			int a = pq.poll();
			int b = pq.poll();
			if (a != b) {
				pq.add(a - b);
			}
		}
		return !pq.isEmpty() ? pq.peek() : 0;
	}
// Building heap - O(nlogn)
// two poll()s + one add(), each O(logn) - total O(nlogn)
// Total time O(nlogn)+O(nlogn)=O(nlogn).
// Space = O(n)
}
