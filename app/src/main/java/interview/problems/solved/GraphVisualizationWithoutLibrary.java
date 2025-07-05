package interview.problems.solved;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;

public class GraphVisualizationWithoutLibrary extends JPanel {
    private final Map<String, List<String>> adjacencyList;
    private final Map<String, Point> nodePositions;

    public GraphVisualizationWithoutLibrary(Map<String, List<String>> adjacencyList) {
        this.adjacencyList = adjacencyList;
        this.nodePositions = new HashMap<>();
        generateNodePositions();
    }

    // Generate random positions for nodes
    private void generateNodePositions() {
        Random random = new Random();
        int width = 800;
        int height = 600;

        for (String node : adjacencyList.keySet()) {
            int x = random.nextInt(width - 100) + 50; // Keep nodes within bounds
            int y = random.nextInt(height - 100) + 50;
            nodePositions.put(node, new Point(x, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw edges
        g2d.setColor(Color.GRAY);
        for (String node : adjacencyList.keySet()) {
            Point p1 = nodePositions.get(node);
            for (String neighbor : adjacencyList.get(node)) {
                Point p2 = nodePositions.get(neighbor);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y); // Draw edge
            }
        }

        // Draw nodes
        g2d.setColor(Color.BLUE);
        for (String node : nodePositions.keySet()) {
            Point p = nodePositions.get(node);
            g2d.fillOval(p.x - 10, p.y - 10, 20, 20); // Draw node as a circle
            g2d.setColor(Color.BLACK);
            g2d.drawString(node, p.x - 5, p.y - 15); // Label the node
            g2d.setColor(Color.BLUE);
        }
    }

    public static void main(String[] args) {
        // Example adjacency list
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D"));
        graph.put("C", Arrays.asList("A", "D"));
        graph.put("D", Arrays.asList("B", "C"));

        // Create the frame
        JFrame frame = new JFrame("Graph Visualization");
        GraphVisualizationWithoutLibrary panel = new GraphVisualizationWithoutLibrary(graph);
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}