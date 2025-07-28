package leetcode;

// refer ninja training problem
public class LCM_256_PaintHouse {
	public int minCost(int[][] costs) {
		int n = costs.length;
		if (n == 0) {
			return 0;
		}
		int prev0 = costs[0][0];
		int prev1 = costs[0][1];
		int prev2 = costs[0][2];
		for (int i = 1; i < n; i++) {
			int curr0 = costs[i][0] + Math.min(prev1, prev2);
			int curr1 = costs[i][1] + Math.min(prev0, prev2);
			int curr2 = costs[i][2] + Math.min(prev0, prev1);
			prev0 = curr0;
			prev1 = curr1;
			prev2 = curr2;
		}
		return Math.min(prev0, Math.min(prev1, prev2));
	}

}
