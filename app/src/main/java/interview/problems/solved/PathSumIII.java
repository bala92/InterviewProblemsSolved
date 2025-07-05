package interview.problems.solved;

/**
 * LeetCode 437: Path Sum III
 *
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (parent to child).
 *
 * Approach:
 * - For each node, count the number of valid paths starting from that node.
 * - Recursively do this for every node in the tree.
 * - Use a helper function to count paths starting from the current node.
 * - Time Complexity: O(n^2) in the worst case.
 */
public class PathSumIII {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        // Count paths starting from root, then from left and right children
        return countFromNode(root, targetSum) +
               pathSum(root.left, targetSum) +
               pathSum(root.right, targetSum);
    }

    // Helper to count paths starting from this node
    private int countFromNode(TreeNode node, int sum) {
        if (node == null) return 0;
        int count = (node.val == sum) ? 1 : 0;
        count += countFromNode(node.left, sum - node.val);
        count += countFromNode(node.right, sum - node.val);
        return count;
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Example tree:
         *      10
         *     /  \
         *    5   -3
         *   / \    \
         *  3   2   11
         * / \   \
         *3  -2   1
         *
         * targetSum = 8
         * Output: 3
         *
         * Explanation:
         * There are 3 paths that sum to 8:
         * 1. 5 -> 3 (left) -> -2 (right) -> 2 (right) -> 1 (right) [not a valid path, as it doesn't go downwards only]
         * 2. 5 -> 2 -> 1
         * 3. -3 -> 11
         * 4. 10 -> -3 -> 11 (not a valid path, as it doesn't go downwards only)
         *
         * The valid paths are:
         * - 5 -> 3 (left) [5 + 3 = 8]
         * - 5 -> 2 -> 1 [5 + 2 + 1 = 8]
         * - -3 -> 11 [-3 + 11 = 8]
         *
         * So, the output is 3.
         */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        PathSumIII solver = new PathSumIII();
        int result = solver.pathSum(root, 8);
        System.out.println("Number of paths: " + result); // Output: 3
    }
}
