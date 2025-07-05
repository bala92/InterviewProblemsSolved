package interview.problems.solved;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem Description:
 * ---------------------
 * Given the root of a binary tree, determine if it is possible to split the tree into two subtrees
 * such that the sum of the values of the nodes in both subtrees is equal.
 *
 * Approach:
 * ---------
 * 1. Perform a postorder traversal to calculate the total sum of the tree.
 * 2. Use a map to store the sum of each subtree during the traversal.
 * 3. If the total sum is odd, return false (cannot split into two equal parts).
 * 4. Check if there exists a subtree with sum equal to totalSum / 2.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the number of nodes in the tree.
 * - Space Complexity: O(n), for the map and recursion stack.
 */
public class EqualTreePartitionWithMap {

    /**
     * Function to determine if the tree can be split into two subtrees with equal sums.
     *
     * @param root The root of the binary tree.
     * @return True if the tree can be split, otherwise false.
     */
    public boolean checkEqualTree(TreeNode root) {
        if (root == null) {
            return false;
        }

        // Map to store the sum of each subtree
        Map<Integer, Integer> subtreeSumMap = new HashMap<>();

        // Step 1: Calculate the total sum of the tree
        int totalSum = calculateSubtreeSums(root, subtreeSumMap);

        // Step 2: If the total sum is odd, return false
        if (totalSum % 2 != 0) {
            return false;
        }

        // Step 3: Check if there exists a subtree with sum equal to totalSum / 2
        int targetSum = totalSum / 2;

        // If the total sum is 0, ensure there are at least two subtrees with sum 0
        if (totalSum == 0) {
            return subtreeSumMap.getOrDefault(0, 0) > 1;
        }

        return subtreeSumMap.containsKey(targetSum);
    }

    /**
     * Helper function to calculate the sum of each subtree and store it in the map.
     *
     * @param node The current node.
     * @param subtreeSumMap A map to store the sum of each subtree.
     * @return The sum of the subtree rooted at the current node.
     */
    private int calculateSubtreeSums(TreeNode node, Map<Integer, Integer> subtreeSumMap) {
        if (node == null) {
            return 0;
        }

        // Calculate the sum of the current subtree
        int subtreeSum = node.val + calculateSubtreeSums(node.left, subtreeSumMap) + calculateSubtreeSums(node.right, subtreeSumMap);

        // Store the subtree sum in the map
        subtreeSumMap.put(subtreeSum, subtreeSumMap.getOrDefault(subtreeSum, 0) + 1);

        return subtreeSum;
    }

    public static void main(String[] args) {
        EqualTreePartitionWithMap solution = new EqualTreePartitionWithMap();

        // Example 1
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(10);
        root1.right = new TreeNode(10);
        root1.right.left = new TreeNode(2);
        root1.right.right = new TreeNode(3);
        System.out.println("Can Partition (Example 1): " + solution.checkEqualTree(root1));
        // Output: true

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(10);
        System.out.println("Can Partition (Example 2): " + solution.checkEqualTree(root2));
        // Output: false

        // Example 3
        TreeNode root3 = new TreeNode(0);
        root3.left = new TreeNode(0);
        root3.right = new TreeNode(0);
        System.out.println("Can Partition (Example 3): " + solution.checkEqualTree(root3));
        // Output: true
    }
}

    // Using shared TreeNode class
