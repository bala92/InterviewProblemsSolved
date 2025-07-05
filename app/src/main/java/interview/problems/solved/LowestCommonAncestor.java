package interview.problems.solved;

import java.util.*;

public class LowestCommonAncestor {

    // Using shared TreeNode class

    /**
     * Recursive solution to find the lowest common ancestor of two nodes in a binary tree.
     *
     * @param root The root of the binary tree.
     * @param p    The first node.
     * @param q    The second node.
     * @return The lowest common ancestor of nodes p and q.
     */
    public static TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if the current node is null or matches one of the target nodes
        if (root == null || root == p || root == q) {
            return root;
        }

        // Recur for left and right subtrees
        TreeNode left = lowestCommonAncestorRecursive(root.left, p, q);
        TreeNode right = lowestCommonAncestorRecursive(root.right, p, q);

        // If both left and right are non-null, the current node is the LCA
        if (left != null && right != null) {
            return root;
        }

        // Otherwise, return the non-null value (either left or right)
        return left != null ? left : right;
    }

    /**
     * Iterative solution to find the lowest common ancestor of two nodes in a binary tree.
     *
     * @param root The root of the binary tree.
     * @param p    The first node.
     * @param q    The second node.
     * @return The lowest common ancestor of nodes p and q.
     */
    public static TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        // Map to store the parent of each node
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        // Stack for tree traversal
        Stack<TreeNode> stack = new Stack<>();

        // Initialize the stack with the root and set its parent to null
        parentMap.put(root, null);
        stack.push(root);

        // Traverse the tree until both p and q are found
        while (!parentMap.containsKey(p) || !parentMap.containsKey(q)) {
            TreeNode node = stack.pop();

            // Add the left child to the stack and map its parent
            if (node.left != null) {
                parentMap.put(node.left, node);
                stack.push(node.left);
            }

            // Add the right child to the stack and map its parent
            if (node.right != null) {
                parentMap.put(node.right, node);
                stack.push(node.right);
            }
        }

        // Set to store the ancestors of p
        Set<TreeNode> ancestors = new HashSet<>();

        // Add all ancestors of p to the set
        while (p != null) {
            ancestors.add(p);
            p = parentMap.get(p);
        }

        // Find the first ancestor of q that is also an ancestor of p
        while (!ancestors.contains(q)) {
            q = parentMap.get(q);
        }

        return q;
    }

    public static void main(String[] args) {
        // Example tree
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode p = root.left; // Node with value 5
        TreeNode q = root.right; // Node with value 1

        // Recursive solution
        TreeNode lcaRecursive = lowestCommonAncestorRecursive(root, p, q);
        System.out.println("Lowest Common Ancestor (Recursive): " + (lcaRecursive != null ? lcaRecursive.val : "null")); // Output: 3

        // Iterative solution
        TreeNode lcaIterative = lowestCommonAncestorIterative(root, p, q);
        System.out.println("Lowest Common Ancestor (Iterative): " + (lcaIterative != null ? lcaIterative.val : "null")); // Output: 3

        p = root.left; // Node with value 5
        q = root.left.right.right; // Node with value 4

        // Recursive solution
        lcaRecursive = lowestCommonAncestorRecursive(root, p, q);
        System.out.println("Lowest Common Ancestor (Recursive): " + (lcaRecursive != null ? lcaRecursive.val : "null")); // Output: 5

        // Iterative solution
        lcaIterative = lowestCommonAncestorIterative(root, p, q);
        System.out.println("Lowest Common Ancestor (Iterative): " + (lcaIterative != null ? lcaIterative.val : "null")); // Output: 5
    }
}

/**
 * Explanation:
 *
 * Recursive Solution:
 * - Use a recursive DFS to traverse the tree.
 * - At each node:
 *   - If the node is null, return null.
 *   - If the node matches either p or q, return the node.
 * - Recur for the left and right subtrees.
 * - If both left and right subtrees return non-null values, the current node is the LCA.
 * - If only one subtree returns a non-null value, propagate that value up the recursion.
 *
 * Iterative Solution:
 * - Use a parent map to store the parent of each node during a tree traversal.
 * - Traverse the tree using a stack until both p and q are found.
 * - Add all ancestors of p to a set.
 * - Traverse the ancestors of q until you find the first common ancestor with p.
 *
 * Complexity:
 * - Recursive Solution:
 *   - Time Complexity: O(n), where n is the number of nodes in the tree.
 *   - Space Complexity: O(h), where h is the height of the tree (due to the recursion stack).
 * - Iterative Solution:
 *   - Time Complexity: O(n), where n is the number of nodes in the tree.
 *   - Space Complexity: O(n), for the parent map and ancestor set.
 */
