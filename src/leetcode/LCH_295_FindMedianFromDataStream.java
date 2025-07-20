package leetcode;

import java.util.PriorityQueue;
import java.util.ArrayList;

public class LCH_295_FindMedianFromDataStream {
	class MedianFinder {
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

		public MedianFinder() {

		}

		public void addNum(int num) {
			pq.add(num);

		}

		public double findMedian() {
			int size = pq.size();
			ArrayList<Integer> temp = new ArrayList<>();
			for (int i = 0; i < size / 2 - 1; i++) {
				temp.add(pq.poll());
			}
			if (size % 2 == 0) {
				int a = pq.poll();
				int b = pq.poll();
				pq.addAll(temp);
				pq.add(a);
				pq.add(b);
				return ((double) (a + b) / 2);
			} else {
				int a = pq.poll();
				pq.addAll(temp);
				pq.add(a);
				return a;
			}

		}
	}

	/**
	 * Your MedianFinder object will be instantiated and called as such:
	 * MedianFinder obj = new MedianFinder(); obj.addNum(num); double param_2 =
	 * obj.findMedian();
	 */

}
