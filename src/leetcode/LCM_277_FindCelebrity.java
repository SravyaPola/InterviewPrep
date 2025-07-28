package leetcode;

import java.util.*;

public class LCM_277_FindCelebrity {
	private static int[][] arr = { { 0, 1, 0 }, { 0, 0, 0 }, { 0, 1, 0 } };

	public static int findCelebrity(int n) {
		if (n <= 0) {
			return -1;
		}
		if (n == 1) {
			return 0;
		}
		Stack<Integer> stk = new Stack<>();

		for (int i = 0; i < n; i++) {
			stk.push(i);
		}

		while (stk.size() > 1) {
			int a = stk.pop();
			int b = stk.pop();

			if (knows(a, b)) {
				stk.push(b);
			} else {
				stk.push(a);
			}
		}
		int celebrity = stk.pop();
		for (int i = 0; i < n; i++) {
			if (i == celebrity) {
				continue;
			}
			if (knows(celebrity, i) || !knows(i, celebrity)) {
				return -1;
			}
		}
		return celebrity;
	}

	private static boolean knows(int a, int b) {
		if (arr[a][b] == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(findCelebrity(arr.length));
	}
}
