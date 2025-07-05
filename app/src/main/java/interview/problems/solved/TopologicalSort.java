package interview.problems.solved;

import java.util.*;

/**
 * Problem Description:
 * ---------------------
 * Perform topological sorting on a directed acyclic graph (DAG).
 * Topological sorting is a linear ordering of vertices such that for every directed edge (u, v),
 * vertex u comes before vertex v in the ordering.
 *
 * Approach:
 * ---------
 * 1. Use Kahn's Algorithm (BFS-based approach):
 *    - Compute the in-degree (number of incoming edges) for each vertex.
 *    - Add all vertices with in-degree 0 to a queue.
 *    - Perform BFS:
 *      - Remove a vertex from the queue and add it to the topological order.
 *      - Decrease the in-degree of its neighbors by 1.
 *      - If any neighbor's in-degree becomes 0, add it to the queue.
 * 2. If all vertices are processed, the graph is a DAG, and the topological order is valid.
 * 3. If not all vertices are processed, the graph contains a cycle, and topological sorting is not possible.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
 * - Space Complexity: O(V + E), for storing the graph and the in-degree array.
 */
public class TopologicalSort {

    /**
     * Function to perform topological sorting on a directed acyclic graph (DAG).
     *
     * @param vertices The number of vertices in the graph.
     * @param edges    The list of directed edges in the graph.
     * @return The topological order of the vertices, or an empty list if the graph contains a cycle.
     */
    public static List<Integer> topologicalSort(int vertices, List<int[]> edges) {
        // Step 1: Build the graph and compute in-degrees
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            inDegree[to]++;
        }

        // Step 2: Add all vertices with in-degree 0 to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Step 3: Perform BFS
        List<Integer> topologicalOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            topologicalOrder.add(current);

            // Decrease the in-degree of neighbors
            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Step 4: Check if all vertices are processed
        if (topologicalOrder.size() == vertices) {
            return topologicalOrder; // Valid topological order
        } else {
            return new ArrayList<>(); // Graph contains a cycle
        }
    }

    public static void main(String[] args) {
        // Example 1: Valid DAG
        int vertices1 = 6;
        List<int[]> edges1 = Arrays.asList(
            new int[]{5, 2},
            new int[]{5, 0},
            new int[]{4, 0},
            new int[]{4, 1},
            new int[]{2, 3},
            new int[]{3, 1}
        );
        System.out.println("Topological Sort (Example 1): " + topologicalSort(vertices1, edges1));
        // Output: [5, 4, 2, 3, 1, 0] (or any valid topological order)

        // Example 2: Graph with a cycle
        int vertices2 = 4;
        List<int[]> edges2 = Arrays.asList(
            new int[]{0, 1},
            new int[]{1, 2},
            new int[]{2, 3},
            new int[]{3, 1} // Cycle
        );
        System.out.println("Topological Sort (Example 2): " + topologicalSort(vertices2, edges2));
        // Output: [] (graph contains a cycle)
    }
}