package leetcode;

import java.util.*;

public class LCE_232_ImpleQueueUsinfStacks {

	// Two stacks to simulate the behavior of a queue
	Stack<Integer> s1 = new Stack<>(); // Used for enqueue (push) operations
	Stack<Integer> s2 = new Stack<>(); // Used for dequeue (pop/peek) operations

	// Constructor - initializes an empty queue
	public LCE_232_ImpleQueueUsinfStacks() {
	}

	// Push element x to the back of the queue
	public void push(int x) {
		// Always push new elements onto stack s1
		s1.push(x);
	}

	// Removes the element from the front of the queue and returns it
	public int pop() {
		// If s2 has elements, just pop from s2
		if (!s2.isEmpty()) {
			return s2.pop();
		} else {
			// Otherwise, move all elements from s1 to s2 to reverse order
			// This makes the oldest element end up on top of s2
			while (!s1.isEmpty()) {
				s2.push(s1.pop());
			}
			// Now pop the front element (top of s2)
			return s2.pop();
		}
	}

	// Get the front element without removing it
	public int peek() {
		// If s2 has elements, the top is the front of the queue
		if (!s2.isEmpty()) {
			return s2.peek();
		} else {
			// Otherwise, move elements from s1 to s2 to reverse order
			while (!s1.isEmpty()) {
				s2.push(s1.pop());
			}
			// Return the front element (top of s2) without removing it
			return s2.peek();
		}
	}

	// Returns true if the queue is empty
	public boolean empty() {
		// Queue is empty only if both stacks are empty
		return s1.isEmpty() && s2.isEmpty();
	}

}
