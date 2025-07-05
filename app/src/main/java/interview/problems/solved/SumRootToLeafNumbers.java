package interview.problems.solved;

public class SumRootToLeafNumbers {

    /**
     * Calculates the total sum of all root-to-leaf numbers.
     *
     * @param root The root of the binary tree.
     * @return The total sum of all root-to-leaf numbers.
     */
    public static int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    /**
     * Helper method to perform DFS and calculate the sum.
     *
     * @param node The current node.
     * @param currentSum The number formed so far.
     * @return The sum of all root-to-leaf numbers in the subtree.
     */
    private static int dfs(TreeNode node, int currentSum) {
        if (node == null) {
            return 0;
        }

        // Update the current number
        currentSum = currentSum * 10 + node.val;

        // If it's a leaf node, return the current number
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        // Recur for left and right subtrees
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println("Total Sum (Example 1): " + sumNumbers(root1)); // Output: 25

        // Example 2
        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(0);
        root2.left.left = new TreeNode(5);
        root2.left.right = new TreeNode(1);
        System.out.println("Total Sum (Example 2): " + sumNumbers(root2)); // Output: 1026
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use Depth-First Search (DFS) to traverse the tree.
 * - At each node, calculate the current number by multiplying the number formed so far by 10 and adding the value of the current node.
 * - If the node is a leaf, add the current number to the total sum.
 * - Recur for the left and right subtrees.
 *
 * Example Walkthrough:
 * Input:
 *     1
 *    / \
 *   2   3
 * - Step 1: Start at root (1), currentSum = 0.
 * - Step 2: Update currentSum = 0 * 10 + 1 = 1.
 * - Step 3: Recur for left child (2):
 *   - Update currentSum = 1 * 10 + 2 = 12.
 *   - Leaf node → Add 12 to total sum.
 * - Step 4: Recur for right child (3):
 *   - Update currentSum = 1 * 10 + 3 = 13.
 *   - Leaf node → Add 13 to total sum.
 * - Total sum = 12 + 13 = 25.
 *
 * Complexity:
 * - Time Complexity: O(N), where N is the number of nodes in the tree.
 * - Space Complexity: O(H), where H is the height of the tree (due to recursion stack).
 */