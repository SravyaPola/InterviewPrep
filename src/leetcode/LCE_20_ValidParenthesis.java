package leetcode;

import java.util.*;

public class LCE_20_ValidParenthesis {
	public boolean isValid(String s) {
		Stack<Character> stk = new Stack<>();
		for (char ch : s.toCharArray()) {
			if (ch == '(' || ch == '{' || ch == '[') {
				stk.push(ch);
			} else {
				if (!stk.isEmpty()) {
					if (ch == ')' && stk.peek() == '(') {
						stk.pop();
					} else if (ch == '}' && stk.peek() == '{') {
						stk.pop();
					} else if (ch == ']' && stk.peek() == '[') {
						stk.pop();
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		return stk.isEmpty();
	}

}
