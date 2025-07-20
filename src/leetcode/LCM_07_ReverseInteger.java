package leetcode;

public class LCM_07_ReverseInteger {
	public static int reverse(int x) {
		boolean isNegative = false;
		if (x < 0) {
			isNegative = true;
			x = (-1) * x;
		}
		int reversed = 0;
		while (x > 0) {
			int digit = x % 10;
			x = x / 10;
			if (reversed > (Integer.MAX_VALUE - digit) / 10) {
				return 0;
			}
			reversed = reversed * 10 + digit;
		}
		return isNegative ? (-1) * reversed : reversed;
	}

	public static void main(String[] args) {
		System.out.println(reverse(-123));
	}

	// O(log N), O(1)
}
