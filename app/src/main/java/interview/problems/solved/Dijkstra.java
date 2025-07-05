package interview.problems.solved;

import java.util.*;

public class Dijkstra {

    /**
     * Function to find the shortest path from the source node to all other nodes using Dijkstra's algorithm.
     *
     * @param graph The adjacency list representing the graph. Each node points to a list of (neighbor, weight) pairs.
     * @param source The source node.
     * @return An array of shortest distances from the source to each node.
     */
    public int[] dijkstra(Map<Integer, List<int[]>> graph, int source, int n) {
        // Distance array to store the shortest distance to each node
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // Min-heap to process nodes with the smallest distance
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{source, 0}); // Add the source node with distance 0

        // Visited set to avoid processing the same node multiple times
        boolean[] visited = new boolean[n];

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            // Skip if the node is already visited
            if (visited[currentNode]) {
                continue;
            }
            visited[currentNode] = true;

            // Process all neighbors of the current node
            if (graph.containsKey(currentNode)) {
                for (int[] neighbor : graph.get(currentNode)) {
                    int neighborNode = neighbor[0];
                    int weight = neighbor[1];

                    // Relaxation: Update the distance if a shorter path is found
                    if (currentDistance + weight < distances[neighborNode]) {
                        distances[neighborNode] = currentDistance + weight;
                        pq.add(new int[]{neighborNode, distances[neighborNode]});
                    }
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        Dijkstra solution = new Dijkstra();

        // Example graph represented as an adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(new int[]{1, 4}, new int[]{2, 1}));
        graph.put(1, Arrays.asList(new int[]{3, 1}));
        graph.put(2, Arrays.asList(new int[]{1, 2}, new int[]{3, 5}));
        graph.put(3, new ArrayList<>());

        int source = 0;
        int n = 4; // Number of nodes in the graph

        int[] distances = solution.dijkstra(graph, source, n);

        System.out.println("Shortest distances from source " + source + ":");
        System.out.println(Arrays.toString(distances));
        // Output: [0, 3, 1, 4]
    }
}