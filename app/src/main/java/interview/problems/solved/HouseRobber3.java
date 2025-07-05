package interview.problems.solved;

public class HouseRobber3 {

    /**
     * Function to calculate the maximum amount of money the thief can rob.
     *
     * @param root The root of the binary tree.
     * @return The maximum amount of money that can be robbed.
     */
    public static int rob(TreeNode root) {
        int[] result = robHelper(root);
        return Math.max(result[0], result[1]);
    }

    /**
     * Helper function to calculate the maximum money for each node.
     *
     * @param node The current node.
     * @return An array where:
     *         - result[0] = maximum money if the current node is not robbed.
     *         - result[1] = maximum money if the current node is robbed.
     */
    private static int[] robHelper(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0}; // Base case: no money can be robbed from a null node
        }

        // Post-order traversal: calculate for left and right children first
        int[] left = robHelper(node.left);
        int[] right = robHelper(node.right);

        // If the current node is not robbed, take the maximum of robbing or not robbing its children
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        // If the current node is robbed, its children cannot be robbed
        int rob = node.val + left[0] + right[0];

        return new int[]{notRob, rob};
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
 * 1. **TreeNode Class**:
 *    - Represents a node in the binary tree with a value and left/right children.
 *
 * 2. **rob Function**:
 *    - Calls the helper function `robHelper` and returns the maximum of robbing or not robbing the root node.
 *
 * 3. **robHelper Function**:
 *    - Uses post-order traversal to calculate the maximum money for each node.
 *    - Returns an array:
 *      - result[0]: Maximum money if the current node is not robbed.
 *      - result[1]: Maximum money if the current node is robbed.
 *
 * 4. **Post-Order Traversal**:
 *    - Process the left and right children before calculating the values for the current node.
 *    - Ensures that the decision for the current node is based on the optimal decisions for its children.
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
 * 1. Start at the leaf nodes:
 *    - For node 3 (left child of 2): rob = 3, notRob = 0.
 *    - For node 1 (right child of 3): rob = 1, notRob = 0.
 * 2. For node 2:
 *    - rob = 2 + 0 (left child not robbed) + 0 (right child not robbed) = 2.
 *    - notRob = max(0, 3) + max(0, 0) = 3.
 * 3. For node 3 (right child of root):
 *    - rob = 3 + 0 (left child not robbed) + 0 (right child not robbed) = 3.
 *    - notRob = max(0, 0) + max(0, 1) = 1.
 * 4. For root node:
 *    - rob = 3 + 3 (left child not robbed) + 1 (right child not robbed) = 7.
 *    - notRob = max(3, 2) + max(3, 1) = 6.
 * 5. Result = max(rob, notRob) = max(7, 6) = 7.
 *
 * Output:
 * Maximum amount that can be robbed: 7
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - Each node is visited once, so the time complexity is `O(n)` where `n` is the number of nodes in the tree.
 *
 * 2. **Space Complexity**:
 *    - The recursion stack uses `O(h)` space, where `h` is the height of the tree.
 *    - In the worst case (skewed tree), `h = n`. In the best case (balanced tree), `h = log(n)`.
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