package interview.problems.solved;

import java.util.Stack;

/**
 * LeetCode 173: Binary Search Tree Iterator
 *
 * Problem:
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 *
 * Methods:
 *   - BSTIterator(TreeNode root): Initializes the iterator.
 *   - boolean hasNext(): Returns true if there is a next smallest number.
 *   - int next(): Returns the next smallest number.
 *
 * Approach:
 * ---------
 * Use a stack to simulate the in-order traversal iteratively.
 * - Push all the left children to the stack initially.
 * - next(): Pop from the stack, push all left children of the right child.
 * - hasNext(): Stack is not empty.
 *
 * Time Complexity: next() and hasNext() are O(1) amortized.
 * Space Complexity: O(h), where h is the height of the tree.
 */
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        pushLeft(root);
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode node = stack.pop();
        int val = node.val;
        if (node.right != null) {
            pushLeft(node.right);
        }
        return val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    // Helper to push all left children to the stack
    private void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    // Example usage
    public static void main(String[] args) {
        /*
         *    7
         *   / \
         *  3   15
         *     /  \
         *    9   20
         */
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator iterator = new BSTIterator(root);
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        // Output: 3 7 9 15 20
    }
}
