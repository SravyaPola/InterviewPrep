package leetcode;

import java.util.HashMap;
import java.util.PriorityQueue;

public class LCM_347_TopKFrequentElements {
	public int[] topKFrequent(int[] nums, int k) {

		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int i : nums) {
			hm.put(i, hm.getOrDefault(i, 0) + 1);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> hm.get(b) - hm.get(a));
		pq.addAll(hm.keySet());
		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			result[i] = pq.poll();
		}
		return result;
	}

}
