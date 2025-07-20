package leetcode;

import java.util.Stack;

public class LCM_71_SimplifyPath {
	public String simplifyPath(String path) {
		// StringBuilder to assemble the final simplified path
		StringBuilder result = new StringBuilder();
		// Stack to hold the valid directory names as we parse
		Stack<String> stk = new Stack<>();

		// Split the input path on "/" and iterate over each segment
		for (String str : path.split("/")) {
			// Skip empty segments (caused by "//") and "." (current directory)
			if (str.equals("") || str.equals(".")) {
				continue;
			}
			// ".." means move up one directory: pop if stack isn't empty
			if (str.equals("..")) {
				if (!stk.isEmpty()) {
					stk.pop();
				}
			} else {
				// A normal directory name: push onto the stack
				stk.push(str);
			}
		}

		// If there are no valid directories, return the root "/"
		if (stk.isEmpty()) {
			return "/";
		}

		// Otherwise, build the path by prepending each directory from the stack
		while (!stk.isEmpty()) {
			result.insert(0, "/" + stk.pop());
		}

		// Convert StringBuilder to String and return
		return result.toString();
	}
	// Overall time complexity: O(N) and space complexity: O(N)
}
