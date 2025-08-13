package leetcode;

import java.util.Stack;

public class LCM_150_EvalReversePolishNotation {
	public int evalRPN(String[] tokens) {
		Stack<Integer> stk = new Stack<>();
		for (String str : tokens) {
			if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
				int a = stk.pop();
				int b = stk.pop();
				int result = 0;
				switch (str) {
				case "+":
					result = b + a;
					break;
				case "-":
					result = b - a;
					break;
				case "*":
					result = b * a;
					break;
				case "/":
					result = b / a;
					break;
				}
				stk.push(result);
			} else {
				stk.push(Integer.parseInt(str));
			}
		}
		return stk.pop();
	}
}
