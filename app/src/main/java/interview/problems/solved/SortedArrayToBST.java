package interview.problems.solved;

/**
 * LeetCode 108: Convert Sorted Array to Binary Search Tree
 *
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree (BST).
 *
 * Approach:
 * - Use recursion: Pick the middle element as the root to ensure balance.
 * - Recursively build the left subtree from the left half and the right subtree from the right half.
 * - This guarantees the tree is height-balanced.
 * - Time Complexity: O(n), where n is the number of elements in the array.
 */
public class SortedArrayToBST {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(mid);
        node.val = nums[mid];
        node.left = buildBST(nums, left, mid - 1);
        node.right = buildBST(nums, mid + 1, right);
        return node;
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Example:
         * Input: [-10, -3, 0, 5, 9]
         * Output: A height-balanced BST
         */
        int[] nums = {-10, -3, 0, 5, 9};
        SortedArrayToBST solver = new SortedArrayToBST();
        TreeNode root = solver.sortedArrayToBST(nums);
        System.out.println("BST created. Root value: " + (root != null ? root.val : "null"));
    }
}
