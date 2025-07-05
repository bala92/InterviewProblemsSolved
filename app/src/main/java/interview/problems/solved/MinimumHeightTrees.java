package interview.problems.solved;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MinimumHeightTrees {

    /**
     * Function to find the roots of the minimum height trees.
     *
     * @param n     The number of nodes in the tree.
     * @param edges The edges of the tree.
     * @return A list of root nodes for the minimum height trees.
     */
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Base case: If there's only one node, return it as the root
        if (n == 1) {
            List<Integer> result = new ArrayList<>();
            result.add(0);
            return result;
        }

        // Build the adjacency list for the graph
        List<HashSet<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // Initialize the first layer of leaves (nodes with only one edge)
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                leaves.add(i);
            }
        }

        // Trim the leaves layer by layer until 1 or 2 nodes remain
        while (n > 2) {
            n -= leaves.size(); // Remove the current layer of leaves
            List<Integer> newLeaves = new ArrayList<>();

            for (int leaf : leaves) {
                // Get the only neighbor of the leaf
                int neighbor = graph.get(leaf).iterator().next();
                // Remove the edge between the leaf and its neighbor
                graph.get(neighbor).remove(leaf);
                // If the neighbor becomes a leaf, add it to the new leaves
                if (graph.get(neighbor).size() == 1) {
                    newLeaves.add(neighbor);
                }
            }

            // Update the leaves for the next iteration
            leaves = newLeaves;
        }

        // The remaining nodes are the roots of the minimum height trees
        return leaves;
    }

    public static void main(String[] args) {
        // Example 1
        int n1 = 4;
        int[][] edges1 = {{1, 0}, {1, 2}, {1, 3}};
        System.out.println("Roots of Minimum Height Trees: " + findMinHeightTrees(n1, edges1)); // Output: [1]

        // Example 2
        int n2 = 6;
        int[][] edges2 = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        System.out.println("Roots of Minimum Height Trees: " + findMinHeightTrees(n2, edges2)); // Output: [3, 4]
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **Base Case**:
 *    - If there's only one node, it is the root of the minimum height tree.
 *
 * 2. **Graph Representation**:
 *    - Use an adjacency list (`List<HashSet<Integer>>`) to represent the tree.
 *    - Each node points to its neighbors.
 *
 * 3. **Leaf Nodes**:
 *    - A leaf node is a node with only one edge (degree = 1).
 *    - Initialize the first layer of leaves by iterating through the adjacency list.
 *
 * 4. **Trimming Leaves**:
 *    - Iteratively remove the current layer of leaves and their edges.
 *    - Update the degrees of their neighbors.
 *    - If a neighbor becomes a leaf, add it to the next layer of leaves.
 *
 * 5. **Stopping Condition**:
 *    - Stop when there are only one or two nodes left. These nodes are the roots of the minimum height trees.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *
 * Steps:
 * 1. Build the adjacency list:
 *    - 0 -> [3], 1 -> [3], 2 -> [3], 3 -> [0, 1, 2, 4], 4 -> [3, 5], 5 -> [4].
 * 2. Initialize leaves: [0, 1, 2, 5].
 * 3. Trim leaves:
 *    - Remove [0, 1, 2, 5]. Update neighbors: 3 -> [4], 4 -> [3].
 *    - New leaves: [3, 4].
 * 4. Remaining nodes: [3, 4].
 * 5. Output: [3, 4].
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - Building the adjacency list: `O(n + e)`, where `e` is the number of edges.
 *    - Trimming the leaves: Each edge is visited once, so `O(n + e)`.
 *    - Overall: `O(n + e)`.
 *
 * 2. **Space Complexity**:
 *    - The adjacency list uses `O(n + e)` space.
 *    - The leaves list uses `O(n)` space.
 *    - Overall: `O(n + e)`.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 * Output: [1]
 *
 * Test Case 2:
 * Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 * Output: [3, 4]
 *
 * Test Case 3:
 * Input: n = 1, edges = []
 * Output: [0]
 *
 * Test Case 4:
 * Input: n = 2, edges = [[0, 1]]
 * Output: [0, 1]
 */