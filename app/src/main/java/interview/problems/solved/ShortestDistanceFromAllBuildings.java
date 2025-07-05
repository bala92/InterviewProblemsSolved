package interview.problems.solved;

import java.util.*;

public class ShortestDistanceFromAllBuildings {

    public static int shortestDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        int[][] reach = new int[m][n];
        int totalBuildings = 0;

        // Directions for BFS
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        // Count total buildings and perform BFS from each building
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    totalBuildings++;
                    Queue<int[]> queue = new LinkedList<>();
                    boolean[][] visited = new boolean[m][n];
                    queue.offer(new int[]{i, j, 0});
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int x = curr[0], y = curr[1], d = curr[2];

                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k], ny = y + dy[k];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n &&
                                grid[nx][ny] == 0 && !visited[nx][ny]) {
                                dist[nx][ny] += d + 1;
                                reach[nx][ny]++;
                                visited[nx][ny] = true;
                                queue.offer(new int[]{nx, ny, d + 1});
                            }
                        }
                    }
                }
            }
        }

        // Find the minimum distance
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reach[i][j] == totalBuildings) {
                    minDist = Math.min(minDist, dist[i][j]);
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 0, 2, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0}
        };
        System.out.println("Shortest Distance: " + shortestDistance(grid)); // Output: 7
    }
}

/**
 * Explanation:
 *
 * - For each building, perform BFS to calculate the shortest distance to every empty land.
 * - Use dist[i][j] to store the sum of distances from all buildings to cell (i, j).
 * - Use reach[i][j] to store the number of buildings that can reach cell (i, j).
 * - After BFS from all buildings, find the minimum dist[i][j] where reach[i][j] == totalBuildings.
 *
 * Complexity:
 * - Time Complexity: O(m * n * m * n), as BFS is performed from each building.
 * - Space Complexity: O(m * n), for dist, reach, and visited arrays.
 */