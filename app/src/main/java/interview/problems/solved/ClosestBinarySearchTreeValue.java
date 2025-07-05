package interview.problems.solved;

/**
 * LeetCode 270: Closest Binary Search Tree Value
 *
 * Problem:
 * Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.
 *
 * Example:
 * Input: root = [4,2,5,1,3], target = 3.714286
 * Output: 4
 */

public class ClosestBinarySearchTreeValue {

    /**
     * Finds the value in the BST that is closest to the target.
     *
     * @param root   The root of the BST.
     * @param target The target value.
     * @return The value in the BST closest to the target.
     */
    public static int closestValue(TreeNode root, double target) {
        int closest = root.val; // Initialize the closest value to the root's value

        while (root != null) {
            // Update the closest value if the current node is closer to the target
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }

            // Move left or right based on the target value
            if (target < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        return closest;
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);

        double target1 = 3.714286;
        System.out.println("Closest Value: " + closestValue(root1, target1)); // Output: 4

        // Example 2
        TreeNode root2 = new TreeNode(1);

        double target2 = 4.428571;
        System.out.println("Closest Value: " + closestValue(root2, target2)); // Output: 1
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use the properties of a BST to traverse the tree.
 * - Keep track of the closest value seen so far.
 * - At each node:
 *   - Update the closest value if the current node's value is closer to the target.
 *   - Move to the left or right child based on the target value:
 *     - If `target < node.val`, move to the left child.
 *     - If `target > node.val`, move to the right child.
 * - Stop when the current node is null.
 *
 * Example Walkthrough:
 * Input: root = [4,2,5,1,3], target = 3.714286
 * - Start at root (4):
 *   - Closest = 4 (initial value).
 *   - Target < 4 → Move to left child (2).
 * - At node (2):
 *   - Closest = 4 (still closer than 2).
 *   - Target > 2 → Move to right child (3).
 * - At node (3):
 *   - Closest = 4 (still closer than 3).
 *   - Target > 3 → Move to right child (null).
 * - Stop traversal. Closest value is 4.
 *
 * Complexity:
 * - Time Complexity: O(h), where h is the height of the tree. In the worst case, h = O(n) for a skewed tree.
 * - Space Complexity: O(1), as no additional space is used.
 */