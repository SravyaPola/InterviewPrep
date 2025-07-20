package leetcode;

import java.util.HashMap;
import java.util.PriorityQueue;

public class LCM_451_SortCharactersByFrequency {

	public String frequencySort(String s) {
		HashMap<Character, Integer> hm = new HashMap<>();
		for (char ch : s.toCharArray()) {
			hm.put(ch, hm.getOrDefault(ch, 0) + 1);
		}
		PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> hm.get(b) - hm.get(a));
		pq.addAll(hm.keySet());
		StringBuilder result = new StringBuilder();
		while (!pq.isEmpty()) {
			char ch = pq.poll();
			for (int i = 0; i < hm.get(ch); i++) {
				result.append(ch);
			}
		}
		return result.toString();
	}
// O(NlogN) O(N)
}
