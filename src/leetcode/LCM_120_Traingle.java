package leetcode;

import java.util.List;

public class LCM_120_Traingle {

	private static int recursion(int i, int j, List<List<Integer>> triangle, int n) {
		if (i == n - 1) {
			return triangle.get(n - 1).get(j);
		}
		int down = triangle.get(i).get(j) + recursion(i + 1, j, triangle, n);
		int diagonal = triangle.get(i).get(j) + recursion(i + 1, j + 1, triangle, n);
		return Math.min(down, diagonal);
	}

	private static int memoization(int i, int j, List<List<Integer>> triangle, int n, List<List<Integer>> dp) {
		if (i == n - 1) {
			return triangle.get(n - 1).get(j);
		}
		if (dp.get(i).get(j) != -1) {
			return dp.get(i).get(j);
		}
		int down = triangle.get(i).get(j) + recursion(i + 1, j, triangle, n);
		int diagonal = triangle.get(i).get(j) + recursion(i + 1, j + 1, triangle, n);
		return dp.get(i).get(j) = Math.min(down, diagonal);
	}

	public static void main(String[] args) {
		List<List<Integer>> ls = List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3));
		System.out.println(recursion(0, 0, ls, ls.size()));

		List<List<Integer>> dp = List.of(List.of(-1), List.of(-1, -1), List.of(-1, -1, -1), List.of(-1, -1, -1, -1));
		System.out.println(memoization(0, 0, ls, ls.size(), dp));
	}

}
