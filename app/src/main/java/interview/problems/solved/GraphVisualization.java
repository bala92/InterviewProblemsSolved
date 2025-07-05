package interview.problems.solved;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.util.*;

public class GraphVisualization {

    public static void main(String[] args) {
        // Example adjacency list
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D"));
        graph.put("C", Arrays.asList("A", "D"));
        graph.put("D", Arrays.asList("B", "C"));

        // Visualize the graph
        visualizeGraph(graph);
    }

    public static void visualizeGraph(Map<String, List<String>> adjacencyList) {
        // Create a new graph
        Graph graph = new SingleGraph("Graph Visualization");

        // Add nodes and edges
        for (String node : adjacencyList.keySet()) {
            if (graph.getNode(node) == null) {
                graph.addNode(node).setAttribute("ui.label", node); // Add node with label
            }
            for (String neighbor : adjacencyList.get(node)) {
                if (graph.getNode(neighbor) == null) {
                    graph.addNode(neighbor).setAttribute("ui.label", neighbor); // Add neighbor node with label
                }
                String edgeId = node + "-" + neighbor;
                if (graph.getEdge(edgeId) == null && graph.getEdge(neighbor + "-" + node) == null) {
                    graph.addEdge(edgeId, node, neighbor); // Add edge
                }
            }
        }

        // Set graph styling
        graph.setAttribute("ui.stylesheet", "node { fill-color: blue; size: 20px; text-size: 14px; } edge { fill-color: gray; }");

    // Export the graph to an image
    // Exporting the graph as an image is not supported directly in the current version of GraphStream.
    // Instead, you can use a screenshot tool or alternative libraries for visualization and export.
    // For now, we will display the graph in a viewer.
    
        graph.display();
        System.out.println("Graph displayed. Use a screenshot tool to save it as an image.");
    }
}