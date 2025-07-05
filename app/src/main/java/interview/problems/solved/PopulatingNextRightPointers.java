package interview.problems.solved;

import java.util.*;

/**
 * LeetCode 116: Populating Next Right Pointers in Each Node
 *
 * Problem:
 * Given a perfect binary tree, populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to null.
 *
 * Example:
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 *
 * Approach:
 * - Use level order traversal (BFS) with a queue, or use constant space by connecting nodes at each level directly.
 * - For each node, set node.left.next = node.right, and node.right.next = node.next != null ? node.next.left : null.
 * - Repeat for all levels.
 *
 * Time Complexity: O(n), where n is the number of nodes.
 * Space Complexity: O(1) for the constant space approach, O(n) for BFS.
 */
public class PopulatingNextRightPointers {
    static class Node {
        int val;
        Node left, right, next;
        Node(int val) { this.val = val; }
    }

    // Constant space approach for perfect binary tree
    public Node connect(Node root) {
        if (root == null) return null;
        Node leftmost = root;
        while (leftmost.left != null) {
            Node head = leftmost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }

    // Level order traversal (BFS) approach for perfect binary tree
    public Node connectBFS(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (prev != null) prev.next = curr;
                prev = curr;
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            // Last node's next is already null
        }
        return root;
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following perfect binary tree:
         *      1
         *     / \
         *    2   3
         *   / \ / \
         *  4  5 6  7
         */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        PopulatingNextRightPointers solver = new PopulatingNextRightPointers();
        // solver.connect(root); // Constant space approach
        solver.connectBFS(root); // Level order traversal approach
        printLevels(root);
    }

    // Helper to print next pointers at each level
    private static void printLevels(Node root) {
        Node level = root;
        while (level != null) {
            Node curr = level;
            while (curr != null) {
                System.out.print(curr.val + (curr.next != null ? " -> " : " # "));
                curr = curr.next;
            }
            System.out.println();
            level = level.left;
        }
    }
}
