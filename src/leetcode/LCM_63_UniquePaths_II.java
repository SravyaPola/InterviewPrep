package leetcode;

public class LCM_63_UniquePaths_II {
	/**
	 * Computes the number of unique paths from the top-left to the bottom-right of
	 * a grid, where some cells contain obstacles. Uses dynamic programming with a
	 * rolling 1D array for O(n) space.
	 */

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {

		// m = number of rows
		int m = obstacleGrid.length;
		// n = number of columns (assumes at least one row exists)
		int n = obstacleGrid[0].length;

		// 'prev' will hold the DP values for the previous row
		int[] prev = new int[n];

		// If start or finish is blocked, no paths exist → return 0 immediately
		if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
			return 0;
		}

		// Iterate over each row i
		for (int i = 0; i < m; i++) {
			// 'curr' will hold the DP values for the current row
			int[] curr = new int[n];

			// Iterate over each column j in row i
			for (int j = 0; j < n; j++) {
				// 1) If this cell is an obstacle, there are 0 ways to reach it
				if (obstacleGrid[i][j] == 1) {
					curr[j] = 0;
				}
				// 2) Otherwise, if we're at the starting cell (0,0), initialize to 1
				else if (i == 0 && j == 0) {
					curr[j] = 1;
				}
				// 3) Otherwise, sum the ways from the top cell and the left cell
				else {
					// Ways to come from above: only if not in the first row
					int up = (i > 0 ? prev[j] : 0);
					// Ways to come from left: only if not in the first column
					int left = (j > 0 ? curr[j - 1] : 0);
					// Total ways to current cell
					curr[j] = up + left;
				}
			}

			// Move the current row's DP into 'prev' for the next iteration
			prev = curr;
		}

		// The bottom-right cell's value is in prev[n-1] after processing all rows
		return prev[n - 1];
	}
}
