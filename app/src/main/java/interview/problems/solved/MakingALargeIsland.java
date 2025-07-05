package interview.problems.solved;

import java.util.*;

public class MakingALargeIsland {

    /**
     * Finds the size of the largest island after changing at most one 0 to 1.
     *
     * @param grid The input binary grid.
     * @return The size of the largest island.
     */
    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Map<Integer, Integer> islandSizes = new HashMap<>();
        int islandId = 2; // Start labeling islands from 2
        int maxIslandSize = 0;

        // Step 1: Label each island with a unique ID and calculate its size using BFS
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = bfs(grid, i, j, islandId, directions);
                    islandSizes.put(islandId, size);
                    maxIslandSize = Math.max(maxIslandSize, size);
                    islandId++;
                }
            }
        }

        // Step 2: Check each 0 and calculate the potential island size
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> uniqueIslands = new HashSet<>();
                    int potentialSize = 1; // Changing this 0 to 1

                    // Check all 4 neighbors
                    for (int[] dir : directions) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] > 1) {
                            uniqueIslands.add(grid[ni][nj]);
                        }
                    }

                    // Add the sizes of all unique neighboring islands
                    for (int id : uniqueIslands) {
                        potentialSize += islandSizes.get(id);
                    }

                    maxIslandSize = Math.max(maxIslandSize, potentialSize);
                }
            }
        }

        return maxIslandSize;
    }

    /**
     * Performs BFS to label the island and calculate its size.
     *
     * @param grid The input grid.
     * @param i The starting row.
     * @param j The starting column.
     * @param islandId The unique ID for the island.
     * @param directions The possible directions for movement.
     * @return The size of the island.
     */
    private static int bfs(int[][] grid, int i, int j, int islandId, int[][] directions) {
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        grid[i][j] = islandId; // Mark the cell with the island ID
        int size = 0;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            size++;

            for (int[] dir : directions) {
                int ni = row + dir[0];
                int nj = col + dir[1];
                if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] == 1) {
                    grid[ni][nj] = islandId; // Mark as visited
                    queue.offer(new int[]{ni, nj});
                }
            }
        }

        return size;
    }

    /**
     * Performs DFS to label the island and calculate its size.
     *
     * @param grid The input grid.
     * @param i The current row.
     * @param j The current column.
     * @param islandId The unique ID for the island.
     * @param directions The possible directions for movement.
     * @return The size of the island.
     */
    @SuppressWarnings("unused")
    private static int dfs(int[][] grid, int i, int j, int islandId, int[][] directions) {
        int n = grid.length;
        if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] != 1) {
            return 0;
        }

        grid[i][j] = islandId; // Mark the cell with the island ID
        int size = 1;

        for (int[] dir : directions) {
            size += dfs(grid, i + dir[0], j + dir[1], islandId, directions);
        }

        return size;
    }
    

    public static void main(String[] args) {
        int[][] grid1 = {
            {1, 0},
            {0, 1}
        };
        System.out.println("Largest Island (Example 1): " + largestIsland(grid1)); // Output: 3

        int[][] grid2 = {
            {1, 1},
            {1, 0}
        };
        System.out.println("Largest Island (Example 2): " + largestIsland(grid2)); // Output: 4

        int[][] grid3 = {
            {1, 1},
            {1, 1}
        };
        System.out.println("Largest Island (Example 3): " + largestIsland(grid3)); // Output: 4
    }
}