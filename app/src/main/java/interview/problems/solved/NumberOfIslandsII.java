package interview.problems.solved;

import java.util.*;

public class NumberOfIslandsII {

    /**
     * Returns the number of islands after each addition of land.
     *
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
     * @param positions The list of positions where land is added.
     * @return A list of the number of islands after each addition.
     */
    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        int[] parent = new int[m * n];
        int[] rank = new int[m * n];
        Arrays.fill(parent, -1); // Initialize all cells as water (-1)
        int count = 0; // Number of islands

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Right, Down, Left, Up

        for (int[] position : positions) {
            int row = position[0];
            int col = position[1];
            int index = row * n + col;

            // If the position is already land, skip it
            if (parent[index] != -1) {
                result.add(count);
                continue;
            }

            // Add land and initialize its parent
            parent[index] = index;
            rank[index] = 0;
            count++; // New island added

            // Check all 4 neighbors
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                int newIndex = newRow * n + newCol;

                // If the neighbor is within bounds and is land, union the components
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && parent[newIndex] != -1) {
                    if (union(index, newIndex, parent, rank)) {
                        count--; // Merge two islands, so reduce the count
                    }
                }
            }

            result.add(count);
        }

        return result;
    }

    /**
     * Finds the root of a node in the Union-Find structure.
     *
     * @param node The node to find the root for.
     * @param parent The parent array.
     * @return The root of the node.
     */
    private static int find(int node, int[] parent) {
        if (parent[node] != node) {
            parent[node] = find(parent[node], parent); // Path compression
        }
        return parent[node];
    }

    /**
     * Unions two nodes in the Union-Find structure.
     *
     * @param node1 The first node.
     * @param node2 The second node.
     * @param parent The parent array.
     * @param rank The rank array.
     * @return True if the nodes were in different components and were merged, false otherwise.
     */
    private static boolean union(int node1, int node2, int[] parent, int[] rank) {
        int root1 = find(node1, parent);
        int root2 = find(node2, parent);

        if (root1 != root2) {
            // Union by rank
            if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else {
                parent[root2] = root1;
                rank[root1]++;
            }
            return true; // Nodes were merged
        }

        return false; // Nodes were already in the same component
    }

    public static void main(String[] args) {
        int m = 3, n = 3;
        int[][] positions = {{0, 0}, {0, 1}, {1, 2}, {2, 1}};
        System.out.println("Number of Islands: " + numIslands2(m, n, positions)); // Output: [1, 1, 2, 3]

        int[][] positions1 = {{0, 0}, {0, 1}, {0, 3}, {0, 4}, {0, 2}};
        System.out.println("Number of Islands: " + numIslands2(5, 5, positions1)); // Output: [1, 1, 2, 2, 1]
    }
}