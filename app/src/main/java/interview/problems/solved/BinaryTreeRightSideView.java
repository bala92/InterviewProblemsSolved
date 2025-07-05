package interview.problems.solved;

import java.util.*;

public class BinaryTreeRightSideView {

    // BFS Approach
    public static List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Integer rightmostValue = null;

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                rightmostValue = currentNode.val; // Update the rightmost value at this level

                // Add child nodes to the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // Add the rightmost value of this level to the result
            result.add(rightmostValue);
        }

        return result;
    }

    // DFS Approach
    public static List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private static void dfs(TreeNode node, int depth, List<Integer> result) {
        if (node == null) {
            return;
        }

        // If this is the first node we're visiting at this depth, add it to the result
        if (depth == result.size()) {
            result.add(node.val);
        }

        // Visit the right subtree first, then the left subtree
        dfs(node.right, depth + 1, result);
        dfs(node.left, depth + 1, result);
    }

    public static void main(String[] args) {
        // Example: Create a binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        // Call the BFS method
        List<Integer> rightViewBFS = rightSideViewBFS(root);
        System.out.println("Right Side View (BFS): " + rightViewBFS);

        // Call the DFS method
        List<Integer> rightViewDFS = rightSideViewDFS(root);
        System.out.println("Right Side View (DFS): " + rightViewDFS);
    }
}