package interview.problems.solved;

import java.util.HashSet;
import java.util.Set;

/**
 * Find the Lowest Common Ancestor (LCA) of two nodes p and q when the root of the binary tree is not given.
 *
 * Problem:
 * Given two nodes p and q in a binary tree (not necessarily a BST), and no access to the root,
 * find their lowest common ancestor (LCA).
 * Each node has a parent pointer.
 *
 * Approach:
 * - Since each node has a parent pointer, we can walk up from both nodes to the root.
 * - Store all ancestors of p in a set.
 * - Walk up from q, the first ancestor found in the set is the LCA.
 *
 * Time Complexity: O(h), where h is the height of the tree (distance from node to root).
 * Space Complexity: O(h), for storing ancestors of p.
 */
public class LowestCommonAncestorWithParentPointer {
    static class TreeNode {
        int val;
        TreeNode left, right, parent;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode lowestCommonAncestor(TreeNode p, TreeNode q) {
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = p.parent;
        }
        while (q != null) {
            if (ancestors.contains(q)) return q;
            q = q.parent;
        }
        return null; // If no common ancestor found
    }

    /**
     * Correct recursive solution to find LCA with parent pointers.
     *
     * Steps:
     * 1. Compute the depth of both nodes.
     * 2. Move the deeper node up until both are at the same depth.
     * 3. Move both up together until they meet.
     */
    public TreeNode lowestCommonAncestorRecursive(TreeNode p, TreeNode q) {
        int depthP = getDepth(p);
        int depthQ = getDepth(q);
        // Bring both nodes to the same depth
        while (depthP > depthQ) {
            p = p.parent;
            depthP--;
        }
        while (depthQ > depthP) {
            q = q.parent;
            depthQ--;
        }
        // Move up together until they meet
        return lcaRecursiveHelper(p, q);
    }

    private int getDepth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            node = node.parent;
            depth++;
        }
        return depth;
    }

    private TreeNode lcaRecursiveHelper(TreeNode p, TreeNode q) {
        if (p == null || q == null) return null;
        if (p == q) return p;
        return lcaRecursiveHelper(p.parent, q.parent);
    }

    /**
     * Two-pointer approach (linked list intersection style) to find LCA with parent pointers.
     *
     * Start two pointers at p and q. Move each up to its parent at each step.
     * When a pointer reaches null, redirect it to the other node. They will meet at the LCA.
     *
     * Time Complexity: O(h), Space Complexity: O(1)
     */
    public TreeNode lowestCommonAncestorTwoPointer(TreeNode p, TreeNode q) {
        TreeNode a = p, b = q;
        while (a != b) {
            a = (a == null) ? q : a.parent;
            b = (b == null) ? p : b.parent;
        }
        return a;
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following tree (with parent pointers):
         *      3
         *     / \
         *    5   1
         *   / \ / \
         *  6  2 0  8
         *    / \
         *   7   4
         */
        TreeNode root = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);
        root.left = n5; root.right = n1;
        n5.parent = root; n1.parent = root;
        n5.left = n6; n5.right = n2;
        n6.parent = n5; n2.parent = n5;
        n1.left = n0; n1.right = n8;
        n0.parent = n1; n8.parent = n1;
        n2.left = n7; n2.right = n4;
        n7.parent = n2; n4.parent = n2;

        LowestCommonAncestorWithParentPointer solver = new LowestCommonAncestorWithParentPointer();
        TreeNode lca = solver.lowestCommonAncestor(n7, n4);
        System.out.println("LCA of 7 and 4: " + (lca != null ? lca.val : "null")); // Output: 2
        lca = solver.lowestCommonAncestor(n6, n4);
        System.out.println("LCA of 6 and 4: " + (lca != null ? lca.val : "null")); // Output: 5

        TreeNode lcaRec = solver.lowestCommonAncestorRecursive(n7, n4);
        System.out.println("[Recursive] LCA of 7 and 4: " + (lcaRec != null ? lcaRec.val : "null")); // Output: 2
        lcaRec = solver.lowestCommonAncestorRecursive(n6, n4);
        System.out.println("[Recursive] LCA of 6 and 4: " + (lcaRec != null ? lcaRec.val : "null")); // Output: 5

        TreeNode lcaTP = solver.lowestCommonAncestorTwoPointer(n7, n4);
        System.out.println("[Two-pointer] LCA of 7 and 4: " + (lcaTP != null ? lcaTP.val : "null")); // Output: 2
        lcaTP = solver.lowestCommonAncestorTwoPointer(n6, n4);
        System.out.println("[Two-pointer] LCA of 6 and 4: " + (lcaTP != null ? lcaTP.val : "null")); // Output: 5
    }
}
