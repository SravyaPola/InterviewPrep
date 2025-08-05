package leetcode;

public class LCE_733_FloodFill {
	public int[][] floodFill(int[][] image, int sr, int sc, int color) {
		int initial = image[sr][sc];
		if (initial == color) {
			return image;
		}
		helper(image, sr, sc, initial, color);
		return image;
	}

	private void helper(int[][] image, int sr, int sc, int initial, int color) {
		if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != initial) {
			return;
		}
		image[sr][sc] = color;
		helper(image, sr + 1, sc, initial, color);
		helper(image, sr - 1, sc, initial, color);
		helper(image, sr, sc + 1, initial, color);
		helper(image, sr, sc - 1, initial, color);
	}
}
