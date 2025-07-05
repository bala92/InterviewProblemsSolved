package interview.problems.solved;

import java.util.Stack;

public class MinStack {

    private Stack<Long> stack; // Stack to store the differences
    private long min;          // Variable to store the current minimum

    /**
     * Constructor to initialize the MinStack.
     */
    public MinStack() {
        stack = new Stack<>();
    }

    /**
     * Push element x onto the stack.
     *
     * @param x The element to be pushed.
     */
    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(0L); // Push the difference (x - min), which is 0 for the first element
            min = x;        // Set the first element as the minimum
        } else {
            stack.push((long) x - min); // Push the difference
            if (x < min) {
                min = x; // Update the minimum if the new element is smaller
            }
        }
    }

    /**
     * Remove the element on top of the stack.
     */
    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        long diff = stack.pop(); // Get the difference
        if (diff < 0) {
            // If the difference is negative, the popped value was the minimum
            min = min - diff; // Restore the previous minimum
        }
    }

    /**
     * Get the top element of the stack.
     *
     * @return The top element.
     */
    public int top() {
        long diff = stack.peek(); // Get the difference
        if (diff > 0) {
            return (int) (min + diff); // Top value is min + diff
        } else {
            return (int) min; // Top value is the current minimum
        }
    }

    /**
     * Retrieve the minimum element in the stack.
     *
     * @return The minimum element.
     */
    public int getMin() {
        return (int) min;
    }

    public static void main(String[] args) {
        // Example usage
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("Minimum: " + minStack.getMin()); // Output: -3
        minStack.pop();
        System.out.println("Top: " + minStack.top());        // Output: 0
        System.out.println("Minimum: " + minStack.getMin()); // Output: -2
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **Single Stack**:
 *    - The `stack` stores the difference between the current value and the minimum value.
 *    - This allows us to track the minimum value without using an auxiliary stack.
 *
 * 2. **Push Operation**:
 *    - If the stack is empty, push `0` (difference between the first element and itself) and set the minimum to the first element.
 *    - Otherwise, push the difference `(x - min)`:
 *      - If `x` is smaller than the current minimum, update `min` to `x`.
 *
 * 3. **Pop Operation**:
 *    - If the popped value is negative, it means the popped value was the minimum.
 *    - Restore the previous minimum using the difference.
 *
 * 4. **Top Operation**:
 *    - If the top value is positive, it represents the difference from the current minimum.
 *    - If the top value is negative, the current minimum is the top value.
 *
 * 5. **Get Minimum**:
 *    - The `min` variable always holds the current minimum value.
 *
 * Example Walkthrough:
 * --------------------
 * Operations:
 * 1. push(-2): stack = [0], min = -2.
 * 2. push(0): stack = [0, 2], min = -2.
 * 3. push(-3): stack = [0, 2, -1], min = -3.
 * 4. getMin(): Returns -3 (min = -3).
 * 5. pop(): stack = [0, 2], min = -2.
 * 6. top(): Returns 0 (min + 2 = -2 + 2 = 0).
 * 7. getMin(): Returns -2 (min = -2).
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - All operations (`push`, `pop`, `top`, `getMin`) are `O(1)` because stack operations (push, pop, peek) and variable updates are constant time.
 *
 * 2. **Space Complexity**:
 *    - The stack stores all elements, so space complexity is `O(n)`, where `n` is the number of elements in the stack.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input:
 * push(-2), push(0), push(-3), getMin(), pop(), top(), getMin()
 * Output:
 * -3, 0, -2
 *
 * Test Case 2:
 * Input:
 * push(1), push(2), getMin(), pop(), getMin()
 * Output:
 * 1, 1
 *
 * Test Case 3:
 * Input:
 * push(5), push(3), push(7), getMin(), pop(), getMin()
 * Output:
 * 3, 3
 */