package leetcode;

public class LCM_64_MinimumPathSum {

	private static int recursion(int i, int j, int[][] grid) {
		if (i == 0 && j == 0) {
			return grid[i][j];
		}
		if (i < 0 || j < 0) {
			return Integer.MAX_VALUE;
		}
		int up = recursion(i - 1, j, grid);
		int down = recursion(i, j - 1, grid);
		return grid[i][j] + Math.min(up, down);
	}

	private static int memoization(int i, int j, int[][] grid, int[][] dp) {
		if (i == 0 && j == 0) {
			return grid[i][j];
		}
		if (i < 0 || j < 0) {
			return Integer.MAX_VALUE;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		int up = recursion(i - 1, j, grid);
		int down = recursion(i, j - 1, grid);
		return dp[i][j] = grid[i][j] + Math.min(up, down);
	}

	private static int tabulation(int m, int n, int[][] grid) {
		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = grid[i][j];
				} else {
					int up;
					if (i > 0) {
						up = dp[i - 1][j];
					} else {
						up = Integer.MAX_VALUE;
					}
					int left;
					if (j > 0) {
						left = dp[i][j - 1];
					} else {
						left = Integer.MAX_VALUE;
					}

					dp[i][j] = grid[i][j] + Math.min(up, left);
				}
			}
		}
		return dp[m - 1][n - 1];
	}

	public static int optimized(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[] prev = new int[n];
		for (int i = 0; i < m; i++) {
			int[] curr = new int[n];
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					curr[j] = grid[0][0];
				} else {
					int up = Integer.MAX_VALUE;
					if (i > 0) {
						up = prev[j] + grid[i][j];
					}
					int left = Integer.MAX_VALUE;
					if (j > 0) {
						left = curr[j - 1] + grid[i][j];
					}
					curr[j] = Math.min(up, left);
				}
			}
			prev = curr;
		}
		return prev[n - 1];
	}

	public int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		for (int i = 1; i < m; i++) {
			grid[i][0] = grid[i][0] + grid[i - 1][0];
		}
		for (int j = 1; j < n; j++) {
			grid[0][j] = grid[0][j] + grid[0][j - 1];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				grid[i][j] = grid[i][j] + Math.min(grid[i - 1][j], grid[i][j - 1]);
			}
		}
		return grid[m - 1][n - 1];
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 2, 3 }, { 4, 5, 6 } };
		int m = grid.length;
		int n = grid[0].length;
		System.out.println(recursion(m - 1, n - 1, grid));

		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] = -1;
			}
		}

		System.out.println(memoization(m - 1, n - 1, grid, dp));

		System.out.println(tabulation(m, n, grid));

		System.out.println(optimized(grid));
	}
}
