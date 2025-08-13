package leetcode;

import java.util.*;

/**
 * LeetCode 225: Implement Stack using Queues
 * 
 * This class implements a stack (LIFO) using a single queue (FIFO). The trick:
 * when pushing an element, rotate the queue so that the most recently pushed
 * element is always at the front.
 */
public class LCE_225_ImpleStackUsingQueue {
	// Queue to store stack elements
	Queue<Integer> q = new LinkedList<>();

	// Constructor (no special initialization required)
	LCE_225_ImpleStackUsingQueue() {

	}

	/**
	 * Pushes an element onto the stack. Time complexity: O(n) due to queue rotation
	 */
	public void push(int x) {
		// Add the new element at the back of the queue
		q.offer(x);

		// Rotate the queue so that the new element moves to the front
		// This ensures the stack's top is always at the queue's front
		for (int i = 0; i < q.size() - 1; i++) {
			q.offer(q.poll());
		}
	}

	/**
	 * Removes and returns the top element of the stack. Time complexity: O(1)
	 */
	public int pop() {
		// If queue is not empty, remove the front element
		if (!q.isEmpty()) {
			return q.poll();
		} else {
			// Return -1 if stack is empty (error case)
			return -1;
		}
	}

	/**
	 * Returns the top element of the stack without removing it. Time complexity:
	 * O(1)
	 */
	public int top() {
		// If queue is not empty, return the front element
		if (!q.isEmpty()) {
			return q.peek();
		} else {
			// Return -1 if stack is empty (error case)
			return -1;
		}
	}

	/**
	 * Checks if the stack is empty. Time complexity: O(1)
	 */
	public boolean empty() {
		return q.isEmpty();
	}
}
