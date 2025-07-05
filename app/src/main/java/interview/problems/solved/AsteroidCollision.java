package interview.problems.solved;

/**
 * Problem: Asteroid Collision
 *
 * We are given an array of integers `asteroids` representing asteroids in a row.
 * - The absolute value represents the size of the asteroid.
 * - The sign represents the direction:
 *   - Positive (+) means the asteroid is moving to the right.
 *   - Negative (-) means the asteroid is moving to the left.
 *
 * Asteroids move at the same speed. If two asteroids meet:
 * - The smaller one will explode.
 * - If both are the same size, both will explode.
 * - Two asteroids moving in the same direction will never meet.
 *
 * Return an array of the asteroids that remain after all collisions.
 *
 * Example:
 * Input: [5, 10, -5]
 * Output: [5, 10]
 * Explanation:
 * - The 10 and -5 collide. The 10 survives because it is larger.
 * - The 5 and 10 never collide because they are moving in the same direction.
 *
 * Approach:
 * - Use a stack to simulate the collisions.
 * - Push asteroids moving to the right onto the stack.
 * - For asteroids moving to the left, check for collisions with the top of the stack.
 */

import java.util.Stack;

public class AsteroidCollision {

    /**
     * Function to simulate asteroid collisions and return the remaining asteroids.
     *
     * @param asteroids Array of integers representing the asteroids.
     * @return Array of integers representing the remaining asteroids after all collisions.
     */
    public static int[] asteroidCollision(int[] asteroids) {
        // Stack to keep track of asteroids that are still "alive"
        Stack<Integer> stack = new Stack<>();

        // Iterate through each asteroid in the input array
        for (int asteroid : asteroids) {
            boolean exploded = false; // Flag to check if the current asteroid explodes

            // Handle collisions: Check if the current asteroid is moving left (< 0)
            // and the top of the stack is moving right (> 0)
            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                if (Math.abs(asteroid) > stack.peek()) {
                    // Current asteroid is larger, so the top of the stack explodes
                    stack.pop();
                    continue; // Continue checking for further collisions
                } else if (Math.abs(asteroid) == stack.peek()) {
                    // Both asteroids are of the same size, so both explode
                    stack.pop();
                    exploded = true; // Mark the current asteroid as exploded
                    break;
                } else {
                    // Current asteroid is smaller, so it explodes
                    exploded = true;
                    break;
                }
            }

            // If the current asteroid didn't explode, add it to the stack
            if (!exploded) {
                stack.push(asteroid);
            }
        }

        // Convert the stack to an array for the final result
        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        // Example input
        int[] asteroids = {5, 10, -5};

        // Call the function
        int[] result = asteroidCollision(asteroids);

        // Print the result
        System.out.print("Remaining asteroids: ");
        for (int asteroid : result) {
            System.out.print(asteroid + " ");
        }
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **Stack Usage**:
 *    - The stack is used to keep track of asteroids that are still "alive" and moving to the right.
 *    - When a new asteroid moving to the left is encountered, it is checked against the top of the stack for collisions.
 *
 * 2. **Collision Handling**:
 *    - If the current asteroid is moving to the left (`< 0`) and the top of the stack is moving to the right (`> 0`), a collision occurs:
 *      - If the current asteroid is larger, the top of the stack is popped.
 *      - If the current asteroid is smaller, it "explodes" and is not added to the stack.
 *      - If both are the same size, both are removed.
 *
 * 3. **Final Result**:
 *    - After processing all asteroids, the stack contains the remaining asteroids in order.
 *    - The stack is converted to an array for the final result.
 *
 * Example Run:
 * Input: [5, 10, -5]
 * Execution:
 * - Push `5` → Stack: [5]
 * - Push `10` → Stack: [5, 10]
 * - Encounter `-5`:
 *   - Collides with `10` → `10` survives.
 * Final Stack: [5, 10]
 * Output: [5, 10]
 *
 * Complexity:
 * - Time Complexity: O(n) (each asteroid is pushed and popped from the stack at most once).
 * - Space Complexity: O(n) (for the stack).
 */