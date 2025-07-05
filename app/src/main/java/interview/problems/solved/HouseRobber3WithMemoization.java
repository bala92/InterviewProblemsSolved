package interview.problems.solved;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber3WithMemoization {

    /**
     * Function to calculate the maximum amount of money the thief can rob.
     *
     * @param root The root of the binary tree.
     * @return The maximum amount of money that can be robbed.
     */
    public static int rob(TreeNode root) {
        // Use a map to store the results of subproblems
        Map<TreeNode, Integer> memo = new HashMap<>();
        return robHelper(root, memo);
    }

    /**
     * Helper function to calculate the maximum money for each node using memoization.
     *
     * @param node The current node.
     * @param memo A map to store the results of subproblems.
     * @return The maximum amount of money that can be robbed starting from this node.
     */
    private static int robHelper(TreeNode node, Map<TreeNode, Integer> memo) {
        if (node == null) {
            return 0; // Base case: no money can be robbed from a null node
        }

        // If the result for this node is already calculated, return it
        if (memo.containsKey(node)) {
            return memo.get(node);
        }

        // Option 1: Rob this node
        int rob = node.val;
        if (node.left != null) {
            rob += robHelper(node.left.left, memo) + robHelper(node.left.right, memo);
        }
        if (node.right != null) {
            rob += robHelper(node.right.left, memo) + robHelper(node.right.right, memo);
        }

        // Option 2: Do not rob this node
        int notRob = robHelper(node.left, memo) + robHelper(node.right, memo);

        // Store the result in the map and return the maximum of the two options
        int result = Math.max(rob, notRob);
        memo.put(node, result);
        return result;
    }

    public static void main(String[] args) {
        // Example tree:
        //     3
        //    / \
        //   2   3
        //    \    \
        //     3    1
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);

        // Call the rob function
        int result = rob(root);

        // Print the result
        System.out.println("Maximum amount that can be robbed: " + result);
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **Memoization**:
 *    - A `Map<TreeNode, Integer>` is used to store the maximum money that can be robbed starting from each node.
 *    - If the result for a node is already calculated, it is retrieved from the map instead of recalculating it.
 *
 * 2. **Two Options for Each Node**:
 *    - **Option 1 (Rob the Node)**:
 *      - Add the value of the current node.
 *      - Skip its immediate children and recursively calculate for its grandchildren.
 *    - **Option 2 (Do Not Rob the Node)**:
 *      - Skip the current node and recursively calculate for its children.
 *
 * 3. **Base Case**:
 *    - If the node is `null`, return `0` (no money can be robbed).
 *
 * 4. **Recursive Calculation**:
 *    - For each node, calculate the maximum of the two options and store the result in the map.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 *     3
 *    / \
 *   2   3
 *    \    \
 *     3    1
 *
 * Steps:
 * 1. Start at the root (3):
 *    - Option 1 (Rob root): 3 + rob(left.grandchildren) + rob(right.grandchildren) = 3 + 3 + 1 = 7.
 *    - Option 2 (Do not rob root): rob(left) + rob(right) = 3 + 3 = 6.
 *    - Result for root: max(7, 6) = 7.
 * 2. Store results for all nodes in the map to avoid redundant calculations.
 *
 * Output:
 * Maximum amount that can be robbed: 7
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - Each node is visited once, and the result is stored in the map.
 *    - Time complexity is `O(n)`, where `n` is the number of nodes in the tree.
 *
 * 2. **Space Complexity**:
 *    - The map stores results for all nodes, so space complexity is `O(n)`.
 *    - The recursion stack uses `O(h)` space, where `h` is the height of the tree.
 *    - Overall space complexity: `O(n)`.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input:
 *     3
 *    / \
 *   2   3
 *    \    \
 *     3    1
 * Output: 7
 *
 * Test Case 2:
 * Input:
 *     3
 *    /
 *   4
 *  / \
 * 1   3
 * Output: 7
 *
 * Test Case 3:
 * Input:
 *     3
 * Output: 3
 */