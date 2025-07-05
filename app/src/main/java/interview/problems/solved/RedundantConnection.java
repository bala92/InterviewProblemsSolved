package interview.problems.solved;

import java.util.List;
import java.util.ArrayList;

/**
 * LeetCode 684: Redundant Connection
 *
 * Problem:
 * In a tree with N nodes labeled 1 to N, one extra edge is added, forming a cycle. Find the edge that can be removed so that the resulting graph is a tree of N nodes.
 * Return the edge that can be removed (if multiple answers, return the one that occurs last in the input).
 *
 * Approach:
 * ---------
 * Use Union-Find (Disjoint Set Union, DSU) to detect the first edge that forms a cycle.
 * For each edge, if the two nodes are already connected, that edge is redundant.
 *
 * Time Complexity: O(N * α(N)), where α is the inverse Ackermann function (very slow-growing).
 * Space Complexity: O(N)
 */
public class RedundantConnection {
    /**
     * Finds the redundant connection in the given edges using Union-Find (Disjoint Set Union).
     *
     * Steps:
     * 1. Initialize a parent array where parent[i] = i for all nodes.
     * 2. For each edge [u, v]:
     *    - Use find() to get the root parent of u and v.
     *    - If they have the same root, adding this edge would form a cycle, so return this edge.
     *    - Otherwise, union the two sets (connect their roots).
     * 3. If no redundant edge is found, return an empty array (should not happen per problem statement).
     *
     * @param edges The list of edges in the graph.
     * @return The redundant edge that forms a cycle.
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        // Step 1: Initialize parent array
        for (int i = 1; i <= n; i++) parent[i] = i;
        // Step 2: Process each edge
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            int pu = find(parent, u);
            int pv = find(parent, v);
            if (pu == pv) {
                // u and v are already connected, so this edge is redundant
                return edge;
            }
            // Union the sets
            union(parent, u, v);
        }
        // Step 3: No redundant edge found (should not happen)
        return new int[0];
    }

    /**
     * Finds the root parent of x with path compression.
     *
     * @param parent The parent array.
     * @param x The node to find.
     * @return The root parent of x.
     */
    private int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]); // Path compression
        return parent[x];
    }

    /**
     * Unions the sets containing x and y.
     *
     * @param parent The parent array.
     * @param x First node.
     * @param y Second node.
     */
    private void union(int[] parent, int x, int y) {
        parent[find(parent, x)] = find(parent, y); // Mark the parent of x as the parent of y
    }

    /**
     * Graph-based approach using DFS to detect a cycle.
     * For each edge, add it to the graph and check if a path already exists between the two nodes.
     * If a path exists, this edge is redundant.
     *
     * Time Complexity: O(N^2) in the worst case.
     * Space Complexity: O(N^2) for the adjacency list.
     *
     * @param edges The list of edges in the graph.
     * @return The redundant edge that forms a cycle.
     */
    public int[] findRedundantConnectionGraph(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            boolean[] visited = new boolean[n + 1];
            if (hasPath(graph, u, v, visited)) {
                return edge;
            }
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return new int[0];
    }

    // Helper DFS to check if a path exists between u and v
    private boolean hasPath(List<List<Integer>> graph, int u, int v, boolean[] visited) {
        if (u == v) return true;
        visited[u] = true;
        for (int nei : graph.get(u)) {
            if (!visited[nei] && hasPath(graph, nei, v, visited)) {
                return true;
            }
        }
        return false;
    }

    // Example usage
    public static void main(String[] args) {
        RedundantConnection solution = new RedundantConnection();
        int[][] edges = {
            {1, 2},
            {1, 3},
            {2, 3}
        };
        int[] redundant = solution.findRedundantConnection(edges);
        System.out.println("Redundant edge: [" + redundant[0] + ", " + redundant[1] + "]");
        System.out.println("Expected output: [2, 3] graph-based approach: [" + solution.findRedundantConnectionGraph(edges)[0] + ", " + solution.findRedundantConnectionGraph(edges)[1] + "]");
        // Output: [2, 3]
    }
}
