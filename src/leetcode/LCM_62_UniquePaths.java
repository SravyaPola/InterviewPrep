package leetcode; // declare this class is part of the 'leetcode' package

public class LCM_62_UniquePaths { // define the public class LCM_62_UniquePaths

	public static void main(String[] args) { // main method: program entry point

		int m = 3; // number of rows in the grid
		int n = 2; // number of columns in the grid
		System.out.println(recursive(m - 1, n - 1)); // compute and print number of unique paths using pure recursion
														// from bottom-right cell

		int[][] dp = new int[m][n]; // create a 2D array for memoization with dimensions m × n

		for (int i = 0; i < m; i++) { // loop over each row
			for (int j = 0; j < n; j++) { // loop over each column in the current row
				dp[i][j] = -1; // initialize dp[i][j] to -1 indicating uncomputed state
			}
		}
		System.out.println(memoization(m - 1, n - 1, dp)); // compute and print number of unique paths using memoization

		System.out.println(tabulation(m, n)); // compute and print number of unique paths using bottom-up tabulation

		System.out.println(optimized(m, n)); // compute and print number of unique paths using space-optimized DP
	}

	private static int recursive(int i, int j) { // recursive approach to count paths to cell (i, j)
		if (i == 0 && j == 0) { // if at the starting cell (top-left)
			return 1; // there's exactly one path (stay put)
		}
		if (i < 0 || j < 0) { // if indices go out of bounds (above first row or left of first column)
			return 0; // no valid paths from outside the grid
		}
		int up = recursive(i - 1, j); // number of paths if we move down from the cell above
		int left = recursive(i, j - 1); // number of paths if we move right from the cell to the left
		return up + left; // total paths to (i, j) is sum of paths from above and left
	}

	private static int memoization(int i, int j, int[][] dp) { // memoized recursion to avoid repeated work
		if (i == 0 && j == 0) { // base case: at the starting cell
			return 1; // one path to start
		}
		if (i < 0 || j < 0) { // out-of-bounds check
			return 0; // zero paths outside grid
		}
		if (dp[i][j] != -1) { // if this subproblem was computed before
			return dp[i][j]; // return cached result
		}
		int up = recursive(i - 1, j); // recursively compute paths from above (note: calls recursive, not memoization)
		int left = recursive(i, j - 1); // recursively compute paths from left
		return dp[i][j] = up + left; // store result in dp and return it
	}

	private static int tabulation(int m, int n) { // bottom-up DP using a 2D table
		int[][] dp = new int[m][n]; // dp[i][j] will hold number of paths to cell (i, j)
		for (int i = 0; i < m; i++) { // iterate over rows
			for (int j = 0; j < n; j++) { // iterate over columns
				if (i == 0 && j == 0) { // starting cell
					dp[i][j] = 1; // one path at the start
				} else {
					int up = 0; // initialize paths from above
					int left = 0; // initialize paths from left
					if (i > 0) { // if there is a cell above
						up = dp[i - 1][j]; // take value from above cell
					}
					if (j > 0) { // if there is a cell to the left
						left = dp[i][j - 1]; // take value from left cell
					}
					dp[i][j] = up + left; // sum paths from above and left
				}
			}
		}
		return dp[m - 1][n - 1]; // final answer at bottom-right cell
	}

	private static int optimized(int m, int n) { // space-optimized DP using two 1D arrays
		if (m <= 0 || n <= 0) { // invalid grid dimensions
			return 0; // no paths if grid is empty
		}
		int[] prev = new int[n]; // array holding DP values for previous row
		for (int i = 0; i < n; i++) { // initialize first row
			prev[i] = 1; // only one way to reach any cell in the first row (all rights)
		}
		int[] curr = new int[n]; // array for current row calculations
		for (int i = 1; i < m; i++) { // iterate from second row to last
			curr[0] = 1; // only one way to reach first column of each row (all downs)
			for (int j = 1; j < n; j++) { // iterate columns from second to last
				curr[j] = curr[j - 1] + prev[j]; // sum of ways from left (curr) and above (prev)
			}
			prev = curr; // copy current row into prev for next iteration
		}
		return prev[n - 1]; // number of ways to reach bottom-right corner
	}

} // end of class LCM_62_UniquePaths
