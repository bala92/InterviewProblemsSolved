package interview.problems.solved;

import java.util.*;

/**
 * LeetCode 339: Nested List Weight Sum
 *
 * Problem Description:
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 *
 * Example:
 * Input: [1,[4,[6]]]
 * Output: 1*1 + 4*2 + 6*3 = 27
 *
 * Approach:
 * ---------
 * - Use Breadth-First Search (BFS) to traverse the nested list level by level.
 * - For each integer, multiply it by its depth and add to the sum.
 * - For each list, add its elements to the queue for the next level (deeper depth).
 * - Increment the depth after processing each level.
 *
 * Why BFS?
 * - BFS naturally processes all elements at the same depth together, making it easy to apply the correct weight.
 * - This avoids the need for recursion and makes the code easy to follow.
 *
 * Time Complexity: O(N), where N is the total number of integers and lists.
 * Space Complexity: O(N), for the queue.
 */
public class NestedListWeightSum {

    /**
     * Calculates the weighted sum of a nested list using iterative BFS.
     *
     * @param nestedList The nested list of integers and lists.
     * @return The weighted sum of all integers.
     */
    @SuppressWarnings("unchecked")
    public static int depthSum(List<Object> nestedList) {
        int sum = 0;
        int depth = 1;

        // Queue to store elements at the current depth
        Queue<Object> queue = new LinkedList<>(nestedList);

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all elements at the current depth
            for (int i = 0; i < size; i++) {
                Object element = queue.poll();

                if (element instanceof Integer) {
                    // Multiply the integer by the depth
                    sum += (Integer) element * depth;
                } else if (element instanceof List) {
                    // Add the nested list to the queue
                    queue.addAll((List<Object>) element);
                }
            }

            // Increment the depth for the next level
            depth++;
        }

        return sum;
    }

    public static void main(String[] args) {
        // Example input
        List<Object> nestedList = Arrays.asList(
            1,
            Arrays.asList(4, Arrays.asList(6))
        );

        // Calculate the weighted sum
        int result = depthSum(nestedList);
        System.out.println("Weighted Sum: " + result); // Output: 27
    }
}