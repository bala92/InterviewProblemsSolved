package interview.problems.solved;

import java.util.*;



public class LCANaryTree {

    private static class Node {
        int val;
        List<Node> children;
    
        public Node(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    /**
     * Finds the Lowest Common Ancestor (LCA) of two nodes in an N-ary tree.
     *
     * @param root The root of the N-ary tree.
     * @param p The first node.
     * @param q The second node.
     * @return The LCA of nodes p and q.
     */
    public static Node findLCA(Node root, Node p, Node q) {
        // Base case: if root is null or matches one of the nodes
        if (root == null || root == p || root == q) {
            return root;
        }

        int count = 0; // Count of children that return non-null
        Node temp = null; // Store the non-null child

        // Recur for all children
        for (Node child : root.children) {
            Node res = findLCA(child, p, q);
            if (res != null) {
                count++;
                temp = res;
            }
        }

        // If more than one child returns non-null, root is the LCA
        if (count > 1) {
            return root;
        }

        // Otherwise, return the non-null child (or null if none)
        return temp;
    }

    public static void main(String[] args) {
        // Example: Create an N-ary tree
        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);
        Node child3 = new Node(4);
        Node child4 = new Node(5);
        Node child5 = new Node(6);

        root.children.add(child1);
        root.children.add(child2);
        root.children.add(child3);
        child1.children.add(child4);
        child1.children.add(child5);

        // Find LCA of nodes 5 and 6
        Node lca = findLCA(root, child4, child5);
        System.out.println("LCA of 5 and 6: " + (lca != null ? lca.val : "null")); // Output: 2

        // Find LCA of nodes 5 and 3
        lca = findLCA(root, child4, child2);
        System.out.println("LCA of 5 and 3: " + (lca != null ? lca.val : "null")); // Output: 1
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use a recursive DFS approach to find the LCA.
 * - Base Case:
 *   - If the current node is null, return null.
 *   - If the current node matches either p or q, return the current node.
 * - Recursive Search:
 *   - Recur for all children of the current node.
 *   - Count how many children return a non-null value.
 * - Determine the LCA:
 *   - If more than one child returns non-null, the current node is the LCA.
 *   - If only one child returns non-null, propagate that value up the recursion.
 * - Return the Result:
 *   - Return the LCA node if found, or null if neither p nor q is found in the subtree.
 *
 * Example Walkthrough:
 * Input:
 * Tree:
 *         1
 *       / | \
 *      2  3  4
 *     / \
 *    5   6
 * - Find LCA of 5 and 6:
 *   - Recur for root (1):
 *     - Recur for child (2):
 *       - Recur for child (5): Return 5.
 *       - Recur for child (6): Return 6.
 *       - Count = 2 → Return 2 (LCA).
 *     - Recur for child (3): Return null.
 *     - Recur for child (4): Return null.
 *   - Count = 1 → Return 2.
 * Output: 2
 *
 * Complexity:
 * - Time Complexity: O(N), where N is the number of nodes in the tree.
 * - Space Complexity: O(H), where H is the height of the tree (due to recursion stack).
 */