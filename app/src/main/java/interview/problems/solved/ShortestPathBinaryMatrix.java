package interview.problems.solved;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathBinaryMatrix {

    /**
     * Finds the shortest path in a binary matrix from the top-left to the bottom-right.
     *
     * @param grid The binary matrix.
     * @return The length of the shortest path, or -1 if no path exists.
     */
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // Edge case: If the start or end cell is blocked
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }

        // Directions for 8 possible moves (horizontal, vertical, diagonal)
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},         {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };

        // BFS initialization
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1}); // {row, col, pathLength}
        grid[0][0] = 1; // Mark the start cell as visited

        // BFS loop
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int pathLength = current[2];

            // If we reach the bottom-right cell, return the path length
            if (row == n - 1 && col == n - 1) {
                return pathLength;
            }

            // Explore all 8 neighbors
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                // Check if the neighbor is within bounds and not visited
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] == 0) {
                    queue.offer(new int[]{newRow, newCol, pathLength + 1});
                    grid[newRow][newCol] = 1; // Mark as visited
                }
            }
        }

        // If no path is found, return -1
        return -1;
    }

    public static void main(String[] args) {
        // Example 1
        int[][] grid1 = {
            {0, 1},
            {1, 0}
        };
        System.out.println("Shortest Path (Example 1): " + shortestPathBinaryMatrix(grid1)); // Output: 2

        // Example 2
        int[][] grid2 = {
            {0, 0, 0},
            {1, 1, 0},
            {1, 1, 0}
        };
        System.out.println("Shortest Path (Example 2): " + shortestPathBinaryMatrix(grid2)); // Output: 4

        // Example 3
        int[][] grid3 = {
            {1, 0},
            {0, 0}
        };
        System.out.println("Shortest Path (Example 3): " + shortestPathBinaryMatrix(grid3)); // Output: -1
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use Breadth-First Search (BFS) to find the shortest path in the binary matrix.
 * - Start from the top-left cell and explore all 8 possible directions.
 * - Use a queue to store the current cell and its path length.
 * - Mark cells as visited by setting their value to 1.
 * - If the bottom-right cell is reached, return the path length.
 * - If no path is found, return -1.
 *
 * Example Walkthrough:
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * - Step 1: Start at (0,0), pathLength = 1.
 * - Step 2: Explore neighbors of (0,0): Add (0,1) and (1,1) to the queue.
 * - Step 3: Explore neighbors of (0,1): Add (0,2) and (1,2) to the queue.
 * - Step 4: Explore neighbors of (1,2): Add (2,2) to the queue.
 * - Step 5: Reach (2,2), pathLength = 4.
 * Output: 4
 *
 * Complexity:
 * - Time Complexity: O(N²), where N is the size of the grid (n x n).
 * - Space Complexity: O(N²), for the queue and visited cells.
 */