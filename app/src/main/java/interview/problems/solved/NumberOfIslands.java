package interview.problems.solved;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    /**
     * Counts the number of islands in the grid using DFS.
     *
     * @param grid The input grid.
     * @return The number of islands.
     */
    public static int numIslandsDFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    dfs(grid, i, j);
                }
            }
        }

        return numIslands;
    }

    /**
     * Performs DFS to mark all connected land cells as visited.
     *
     * @param grid The input grid.
     * @param i The current row.
     * @param j The current column.
     */
    private static void dfs(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Base case: Out of bounds or water
        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] == '0') {
            return;
        }

        // Mark the current cell as visited
        grid[i][j] = '0';

        // Explore all 4 directions
        dfs(grid, i + 1, j); // Down
        dfs(grid, i - 1, j); // Up
        dfs(grid, i, j + 1); // Right
        dfs(grid, i, j - 1); // Left
    }

    /**
     * Counts the number of islands in the grid using BFS.
     *
     * @param grid The input grid.
     * @return The number of islands.
     */
    public static int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Right, Down, Left, Up

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    bfs(grid, i, j, directions);
                }
            }
        }

        return numIslands;
    }

    /**
     * Performs BFS to mark all connected land cells as visited.
     *
     * @param grid The input grid.
     * @param i The starting row.
     * @param j The starting column.
     * @param directions The possible directions for movement.
     */
    private static void bfs(char[][] grid, int i, int j, int[][] directions) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        grid[i][j] = '0'; // Mark the starting cell as visited

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if the neighbor is within bounds and is land
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == '1') {
                    queue.offer(new int[]{newRow, newCol});
                    grid[newRow][newCol] = '0'; // Mark as visited
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid1 = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };

        // Test DFS
        char[][] grid1CopyForDFS = copyGrid(grid1);
        System.out.println("Number of Islands (DFS): " + numIslandsDFS(grid1CopyForDFS)); // Output: 3

        // Test BFS
        char[][] grid1CopyForBFS = copyGrid(grid1);
        System.out.println("Number of Islands (BFS): " + numIslandsBFS(grid1CopyForBFS)); // Output: 3
    }

    /**
     * Utility function to create a deep copy of a grid.
     *
     * @param grid The input grid.
     * @return A deep copy of the grid.
     */
    private static char[][] copyGrid(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        char[][] copy = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, cols);
        }
        return copy;
    }
}