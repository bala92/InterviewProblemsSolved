package interview.problems.solved;

import java.util.ArrayList;
import java.util.List;

public class DetectCycleInDirectedGraph {

    /**
     * Function to detect a cycle in a directed graph.
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
        }

        // Arrays to track visited nodes and the recursion stack
        boolean[] visited = new boolean[n];
        boolean[] recStack = new boolean[n];

        // Perform DFS for each unvisited node
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfs(i, graph, visited, recStack)) {
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
     * @param graph    The adjacency list of the graph.
     * @param visited  Array to track visited nodes.
     * @param recStack Array to track the recursion stack.
     * @return True if a cycle is detected, otherwise false.
     */
    private static boolean dfs(int node, List<List<Integer>> graph, boolean[] visited, boolean[] recStack) {
        // Mark the current node as visited and add it to the recursion stack
        visited[node] = true;
        recStack[node] = true;

        // Visit all neighbors of the current node
        for (int neighbor : graph.get(node)) {
            // If the neighbor is in the recursion stack, a cycle is detected
            if (recStack[neighbor]) {
                return true;
            }
            // If the neighbor is not visited, recursively perform DFS
            if (!visited[neighbor]) {
                if (dfs(neighbor, graph, visited, recStack)) {
                    return true;
                }
            }
        }

        // Remove the current node from the recursion stack
        recStack[node] = false;
        return false;
    }

    public static void main(String[] args) {
        // Example 1: Graph with a cycle
        int n1 = 4;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 3}, {3, 1}};
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
 * 2. **DFS with Recursion Stack**:
 *    - The `visited` array tracks whether a node has been visited.
 *    - The `recStack` array tracks whether a node is in the current recursion stack.
 *    - If a node is revisited while it is still in the recursion stack, a cycle is detected.
 *
 * 3. **Cycle Detection**:
 *    - For each unvisited node, perform a DFS.
 *    - If a cycle is detected during the DFS, return `true`.
 *    - If no cycle is detected after processing all nodes, return `false`.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * n = 4, edges = [[0, 1], [1, 2], [2, 3], [3, 1]]
 *
 * Steps:
 * 1. Build the adjacency list:
 *    - 0 -> [1], 1 -> [2], 2 -> [3], 3 -> [1].
 * 2. Perform DFS starting from node 0:
 *    - Visit 0, add to recursion stack.
 *    - Visit 1, add to recursion stack.
 *    - Visit 2, add to recursion stack.
 *    - Visit 3, add to recursion stack.
 *    - Node 3 points to 1, which is already in the recursion stack. Cycle detected.
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
 *    - The `visited` and `recStack` arrays use `O(n)` space.
 *    - The recursion stack uses `O(h)` space, where `h` is the height of the DFS tree.
 *    - Overall: `O(n + e)`.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: n = 4, edges = [[0, 1], [1, 2], [2, 3], [3, 1]]
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
 * Input: n = 2, edges = [[0, 1], [1, 0]]
 * Output: true
 */