package interview.problems.solved;

import java.util.*;

/**
 * Problem Description:
 * ---------------------
 * Given the root of a binary tree and a target node, find the distance to the closest leaf node from the target node.
 *
 * Approach:
 * ---------
 * 1. Traverse the tree to locate the target node and build a parent map.
 * 2. Perform a BFS starting from the target node:
 *    - Explore left, right, and parent nodes.
 *    - Stop when a leaf node is found and return the distance.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the number of nodes in the tree.
 * - Space Complexity: O(n), for the parent map and BFS queue.
 */
public class DistanceToClosestLeaf {

    /**
     * Function to find the distance to the closest leaf node from the target node.
     *
     * @param root The root of the binary tree.
     * @param target The target node.
     * @return The distance to the closest leaf node.
     */
    public int findClosestLeafDistance(TreeNode root, TreeNode target) {
        // Step 1: Build a parent map and locate the target node
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, null, parentMap);

        // Step 2: Perform BFS to find the distance to the closest leaf
        return bfsToFindClosestLeafDistance(target, parentMap);
    }

    /**
     * Helper function to build a parent map for the tree.
     *
     * @param node The current node being processed.
     * @param parent The parent of the current node.
     * @param parentMap A map to store parent-child relationships.
     */
    private void buildParentMap(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if (node == null) {
            return;
        }

        // Add the current node and its parent to the map
        if (parent != null) {
            parentMap.put(node, parent);
        }

        // Recursively process the left and right subtrees
        buildParentMap(node.left, node, parentMap);
        buildParentMap(node.right, node, parentMap);
    }

    /**
     * Helper function to perform BFS and find the distance to the closest leaf node.
     *
     * @param start The starting node for BFS.
     * @param parentMap A map to access parent nodes.
     * @return The distance to the closest leaf node.
     */
    private int bfsToFindClosestLeafDistance(TreeNode start, Map<TreeNode, TreeNode> parentMap) {
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        int distance = 0;

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process all nodes at the current level
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                // Check if the current node is a leaf
                if (current.left == null && current.right == null) {
                    return distance;
                }

                // Add left child to the queue
                if (current.left != null && !visited.contains(current.left)) {
                    queue.offer(current.left);
                    visited.add(current.left);
                }

                // Add right child to the queue
                if (current.right != null && !visited.contains(current.right)) {
                    queue.offer(current.right);
                    visited.add(current.right);
                }

                // Add parent to the queue
                if (parentMap.containsKey(current) && !visited.contains(parentMap.get(current))) {
                    queue.offer(parentMap.get(current));
                    visited.add(parentMap.get(current));
                }
            }

            // Increment the distance after processing all nodes at the current level
            distance++;
        }

        return -1; // This should never happen if the tree is valid
    }

    public static void main(String[] args) {
        DistanceToClosestLeaf solution = new DistanceToClosestLeaf();

        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(6);
        root1.left.right.left = new TreeNode(7);
        System.out.println("Distance to Closest Leaf from Node 2: " + solution.findClosestLeafDistance(root1, root1.left));
        // Output: 1

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        System.out.println("Distance to Closest Leaf from Node 1: " + solution.findClosestLeafDistance(root2, root2));
        // Output: 1
    }
}
