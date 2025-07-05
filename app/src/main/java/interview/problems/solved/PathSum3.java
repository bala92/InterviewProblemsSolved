package interview.problems.solved;

/**
 * LeetCode 437: Path Sum III
 *
 * Problem:
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (parent to child).
 *
 * Example:
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths are: [5,3], [5,2,1], and [10,-3,1].
 *
 * Approach:
 * - Use prefix sum and a HashMap to count the number of valid paths ending at each node.
 * - For each node, calculate the current prefix sum and check if (currentSum - targetSum) exists in the map.
 * - Recursively traverse the tree, updating the map as you go.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(h), where h is the height of the tree (recursion stack and map size).
 */
public class PathSum3 {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public int pathSum(TreeNode root, int targetSum) {
        return dfs(root, 0, targetSum, new java.util.HashMap<>() {{ put(0, 1); }});
    }

    private int dfs(TreeNode node, int currSum, int target, java.util.Map<Integer, Integer> prefixSumCount) {
        if (node == null) return 0;
        currSum += node.val;
        int res = prefixSumCount.getOrDefault(currSum - target, 0);
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        res += dfs(node.left, currSum, target, prefixSumCount);
        res += dfs(node.right, currSum, target, prefixSumCount);
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1); // Backtrack
        return res;
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following tree:
         *      10
         *     /  \
         *    5   -3
         *   / \    \
         *  3   2   11
         * / \   \
         *3  -2   1
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

        PathSum3 solver = new PathSum3();
        int targetSum = 8;
        System.out.println("Number of paths with sum 8: " + solver.pathSum(root, targetSum)); // Output: 3
    }
}
