package interview.problems.solved;

import java.util.*;

public class BinaryTreeVerticalOrderTraversalXY {

    /**
     * BFS Approach for Vertical Order Traversal using x and y values.
     *
     * @param root The root of the binary tree.
     * @return A list of lists, where each inner list contains the nodes in one vertical line.
     */
    public static List<List<Integer>> verticalOrderBFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // TreeMap to store nodes grouped by their x values
        TreeMap<Integer, List<int[]>> columnMap = new TreeMap<>();

        // Queue to perform BFS, storing triples of (node, x, y)
        Queue<Triple> queue = new LinkedList<>();
        queue.offer(new Triple(root, 0, 0));

        while (!queue.isEmpty()) {
            Triple current = queue.poll();
            TreeNode node = current.node;
            int x = current.x;
            int y = current.y;

            // Add the node's value to the corresponding x in the map
            columnMap.putIfAbsent(x, new ArrayList<>());
            columnMap.get(x).add(new int[]{y, node.val});

            // Add left and right children to the queue with updated x and y
            if (node.left != null) {
                queue.offer(new Triple(node.left, x - 1, y + 1));
            }
            if (node.right != null) {
                queue.offer(new Triple(node.right, x + 1, y + 1));
            }
        }

        // Sort nodes at each x by their y values and then by their values
        for (List<int[]> nodes : columnMap.values()) {
            nodes.sort((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
            List<Integer> column = new ArrayList<>();
            for (int[] node : nodes) {
                column.add(node[1]);
            }
            result.add(column);
        }

        return result;
    }

    /**
     * DFS Approach for Vertical Order Traversal using x and y values.
     *
     * @param root The root of the binary tree.
     * @return A list of lists, where each inner list contains the nodes in one vertical line.
     */
    public static List<List<Integer>> verticalOrderDFS(TreeNode root) {
        TreeMap<Integer, List<int[]>> columnMap = new TreeMap<>();
        dfsHelper(root, 0, 0, columnMap);

        // Prepare the result list
        List<List<Integer>> result = new ArrayList<>();
        for (List<int[]> nodes : columnMap.values()) {
            nodes.sort((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
            List<Integer> column = new ArrayList<>();
            for (int[] node : nodes) {
                column.add(node[1]);
            }
            result.add(column);
        }

        return result;
    }

    private static void dfsHelper(TreeNode node, int x, int y, TreeMap<Integer, List<int[]>> columnMap) {
        if (node == null) {
            return;
        }

        // Add the node to the corresponding x in the map
        columnMap.putIfAbsent(x, new ArrayList<>());
        columnMap.get(x).add(new int[]{y, node.val});

        // Recur for left and right subtrees with updated x and y
        dfsHelper(node.left, x - 1, y + 1, columnMap);
        dfsHelper(node.right, x + 1, y + 1, columnMap);
    }

    // Helper class to store a node and its x and y values
    static class Triple {
        TreeNode node;
        int x;
        int y;

        Triple(TreeNode node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        // Example input
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        // Perform vertical order traversal using BFS
        List<List<Integer>> bfsResult = verticalOrderBFS(root);
        System.out.println("Vertical Order Traversal (BFS): " + bfsResult);
        // Output: [[9], [3, 15], [20], [7]]

        // Perform vertical order traversal using DFS
        List<List<Integer>> dfsResult = verticalOrderDFS(root);
        System.out.println("Vertical Order Traversal (DFS): " + dfsResult);
        // Output: [[9], [3, 15], [20], [7]]
    }
}