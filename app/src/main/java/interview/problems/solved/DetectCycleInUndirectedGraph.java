package interview.problems.solved;

import java.util.ArrayList;
import java.util.List;

public class DetectCycleInUndirectedGraph {

    /**
     * Function to detect a cycle in an undirected graph.
     *
     * @param n     The number of nodes in the graph.
     * @param edges The edges of the graph.
     * @return True if the graph contains a cycle, otherwise false.
     */
    public static boolean hasCycle(int n, int[][] edges) {
        // Build the adjacency list for the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // Array to track visited nodes
        boolean[] visited = new boolean[n];

        // Perform DFS for each unvisited node
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfs(i, -1, graph, visited)) {
                    return true; // Cycle detected
                }
            }
        }

        return false; // No cycle detected
    }

    /**
     * Helper function to perform DFS and detect a cycle.
     *
     * @param node     The current node.
     * @param parent   The parent of the current node.
     * @param graph    The adjacency list of the graph.
     * @param visited  Array to track visited nodes.
     * @return True if a cycle is detected, otherwise false.
     */
    private static boolean dfs(int node, int parent, List<List<Integer>> graph, boolean[] visited) {
        // Mark the current node as visited
        visited[node] = true;

        // Visit all neighbors of the current node
        for (int neighbor : graph.get(node)) {
            // If the neighbor is not visited, recursively perform DFS
            if (!visited[neighbor]) {
                if (dfs(neighbor, node, graph, visited)) {
                    return true;
                }
            }
            // If the neighbor is visited and is not the parent, a cycle is detected
            else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Example 1: Graph with a cycle
        int n1 = 5;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 1}};
        System.out.println("Graph contains cycle: " + hasCycle(n1, edges1)); // Output: true

        // Example 2: Graph without a cycle
        int n2 = 4;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}};
        System.out.println("Graph contains cycle: " + hasCycle(n2, edges2)); // Output: false
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **Graph Representation**:
 *    - The graph is represented as an adjacency list (`List<List<Integer>>`).
 *    - Each node points to its neighbors.
 *
 * 2. **DFS with Parent Tracking**:
 *    - The `visited` array tracks whether a node has been visited.
 *    - The `parent` parameter tracks the parent of the current node in the DFS tree.
 *    - If a neighbor is visited and is not the parent, a cycle is detected.
 *
 * 3. **Cycle Detection**:
 *    - For each unvisited node, perform a DFS.
 *    - If a cycle is detected during the DFS, return `true`.
 *    - If no cycle is detected after processing all nodes, return `false`.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * n = 5, edges = [[0, 1], [1, 2], [2, 3], [3, 4], [4, 1]]
 *
 * Steps:
 * 1. Build the adjacency list:
 *    - 0 -> [1], 1 -> [0, 2], 2 -> [1, 3], 3 -> [2, 4], 4 -> [3, 1].
 * 2. Perform DFS starting from node 0:
 *    - Visit 0, add to visited.
 *    - Visit 1, add to visited.
 *    - Visit 2, add to visited.
 *    - Visit 3, add to visited.
 *    - Visit 4, add to visited.
 *    - Node 4 points to 1, which is already visited and is not the parent of 4. Cycle detected.
 * 3. Output: true.
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - Building the adjacency list: `O(n + e)`, where `e` is the number of edges.
 *    - DFS traversal: Each node and edge is visited once, so `O(n + e)`.
 *    - Overall: `O(n + e)`.
 *
 * 2. **Space Complexity**:
 *    - The adjacency list uses `O(n + e)` space.
 *    - The `visited` array uses `O(n)` space.
 *    - The recursion stack uses `O(h)` space, where `h` is the height of the DFS tree.
 *    - Overall: `O(n + e)`.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: n = 5, edges = [[0, 1], [1, 2], [2, 3], [3, 4], [4, 1]]
 * Output: true
 *
 * Test Case 2:
 * Input: n = 4, edges = [[0, 1], [1, 2], [2, 3]]
 * Output: false
 *
 * Test Case 3:
 * Input: n = 1, edges = []
 * Output: false
 *
 * Test Case 4:
 * Input: n = 2, edges = [[0, 1]]
 * Output: false
 */