package leetcode;

import java.util.PriorityQueue;

public class LCM_378_KthSmallestElement {
	public int kthSmallest(int[][] matrix, int k) {
		// We will always keep our Priority Queue of size to k fized
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (pq.size() < k) {
					// fill until we have k elements
					pq.add(matrix[i][j]);
				} else if (matrix[i][j] < pq.peek()) {
					// incoming is smaller than our current kth smallest
					pq.poll();
					pq.add(matrix[i][j]);
				}
			}
		}
		return pq.peek();
	}
	// O(n^2 logk)
	// O(k)
}
