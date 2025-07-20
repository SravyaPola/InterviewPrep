package leetcode;

public class LCM_05_LongestPalindromicSubstring {
	// Expand around center for every element, also do for even and odd pointers
	// stop expanding if left and right are not equal
	public static String longestPalindrome(String s) {
		int n = s.length();
		if (s.length() < 2) {
			return s;
		}
		int bestLength = 0;
		String bestString = "";
		for (int i = 0; i < n; i++) {
			// Odd -- Expand around one element left and right are same
			int left = i;
			int right = i;
			while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
				int length = right - left + 1;
				if (length > bestLength) {
					bestLength = length;
					bestString = s.substring(left, right + 1);
				}
				left--;
				right++;
			}
			// Even -- Expand around two elements in center ie left and right are differed
			// by 1
			left = i;
			right = i + 1;
			while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
				int length = right - left + 1;
				if (length > bestLength) {
					bestLength = length;
					bestString = s.substring(left, right + 1);
				}
				left--;
				right++;
			}
		}
		return bestString;
	}

	public static void main(String[] args) {
		String str = "cbbd";
		System.out.println(longestPalindrome(str));
	}

	// O(N^2) O(1)
}
