package interview.problems.solved;

import java.util.*;

/**
 * Problem: Clone Graph
 * 
 * You are given a reference to a node in a connected undirected graph. Each node in the graph contains:
 * - A value (val).
 * - A list of its neighbors (List<Node>).
 * 
 * Your task is to return a deep copy (clone) of the graph.
 * 
 * Example:
 * Input:
 * Graph:
 * 1 -- 2
 * |    |
 * 4 -- 3
 * 
 * Output:
 * Cloned Graph:
 * 1 -- 2
 * |    |
 * 4 -- 3
 * 
 * Approach:
 * - Use either Depth-First Search (DFS) or Breadth-First Search (BFS) to traverse the graph.
 * - Maintain a mapping of original nodes to their cloned counterparts using a HashMap.
 * - For each node, clone it if it hasn't been cloned yet, and recursively or iteratively clone its neighbors.
 * 
 * Time Complexity: O(V + E), where V is the number of vertices (nodes) and E is the number of edges.
 * Space Complexity: O(V) for the HashMap and recursion stack (in DFS).
 */

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }

    public Node(int val, List<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}

public class CloneGraph {

    // DFS Implementation
    public Node cloneGraphDFS(Node node) {
        if (node == null) {
            return null;
        }

        // A map to store the original node -> cloned node mapping
        Map<Node, Node> visited = new HashMap<>();

        // Start the DFS traversal and clone the graph
        return dfs(node, visited);
    }

    private Node dfs(Node node, Map<Node, Node> visited) {
        // If the node is already cloned, return the cloned node
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Clone the current node
        Node clone = new Node(node.val);
        visited.put(node, clone);

        // Recursively clone all the neighbors
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor, visited));
        }

        return clone;
    }

    // BFS Implementation
    public Node cloneGraphBFS(Node node) {
        if (node == null) {
            return null;
        }

        // A map to store the original node -> cloned node mapping
        Map<Node, Node> visited = new HashMap<>();

        // Create the clone for the starting node and add it to the map
        Node clone = new Node(node.val);
        visited.put(node, clone);

        // Use a queue for BFS
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Iterate through the neighbors of the current node
            for (Node neighbor : current.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    // Clone the neighbor and add it to the map
                    visited.put(neighbor, new Node(neighbor.val));
                    queue.add(neighbor);
                }
                // Add the cloned neighbor to the current node's clone
                visited.get(current).neighbors.add(visited.get(neighbor));
            }
        }

        return clone;
    }

    // Method to draw the graph (print adjacency list representation)
    public void drawGraph(Node node) {
        if (node == null) {
            System.out.println("Graph is empty.");
            return;
        }

        // Use a set to track visited nodes
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        System.out.println("Graph adjacency list:");

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // If the node is already visited, skip it
            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);

            // Print the current node and its neighbors
            System.out.print(current.val + " -> ");
            for (Node neighbor : current.neighbors) {
                System.out.print(neighbor.val + " ");
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Example usage
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        CloneGraph solution = new CloneGraph();

        // Draw the original graph
        System.out.println("Original Graph:");
        solution.drawGraph(node1);

        // Clone using DFS
        Node clonedGraphDFS = solution.cloneGraphDFS(node1);
        System.out.println("\nCloned Graph (DFS):");
        solution.drawGraph(clonedGraphDFS);

        // Clone using BFS
        Node clonedGraphBFS = solution.cloneGraphBFS(node1);
        System.out.println("\nCloned Graph (BFS):");
        solution.drawGraph(clonedGraphBFS);
    }
}