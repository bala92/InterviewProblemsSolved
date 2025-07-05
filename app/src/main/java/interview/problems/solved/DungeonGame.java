package interview.problems.solved;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem Description:
 * ---------------------
 * Given a 2D grid representing a dungeon, determine the minimum initial health required for a knight
 * to rescue the princess. The knight starts at the top-left corner and must reach the bottom-right corner.
 *
 * Approach:
 * ---------
 * 1. Use dynamic programming to calculate the minimum health required at each cell.
 * 2. Traverse the grid in reverse (from bottom-right to top-left).
 * 3. At each cell, calculate the minimum health required to move to the next cell (right or down).
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(m * n), where m is the number of rows and n is the number of columns.
 * - Space Complexity: O(m * n), for the DP table.
 */
public class DungeonGame {

    /**
     * Function to calculate the minimum initial health required for the knight.
     *
     * @param dungeon The 2D grid representing the dungeon.
     * @return The minimum initial health required.
     */
    public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // DP table to store the minimum health required at each cell
        int[][] dp = new int[m][n];

        // Base case: Bottom-right corner
        dp[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);

        // Fill the last row (right to left)
        for (int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] = Math.max(1, dp[m - 1][j + 1] - dungeon[m - 1][j]);
        }
        printDP(dp, "After filling the last row");

        // Fill the last column (bottom to top)
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(1, dp[i + 1][n - 1] - dungeon[i][n - 1]);
        }
        printDP(dp, "After filling the last column");

        // Fill the rest of the table (bottom-right to top-left)
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int minHealthOnExit = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(1, minHealthOnExit - dungeon[i][j]);
            }
            printDP(dp, "After processing row " + i);
        }

        printPath(dp);

        // The top-left cell contains the minimum initial health required
        return dp[0][0];
    }

    private static void printDP(int[][] dp, String message) {
        System.out.println(message);
        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

        /**
     * Helper function to print the path taken by the knight.
     *
     * @param dp The DP matrix.
     */
    private static void printPath(int[][] dp) {
        System.out.println("Path taken by the knight:");
        int i = 0, j = 0;
        List<String> path = new ArrayList<>();
        path.add("(" + i + ", " + j + ")");

        while (i < dp.length - 1 || j < dp[0].length - 1) {
            if (i == dp.length - 1) {
                // If at the last row, move right
                j++;
            } else if (j == dp[0].length - 1) {
                // If at the last column, move down
                i++;
            } else if (dp[i + 1][j] < dp[i][j + 1]) {
                // Move down if the health required below is less
                i++;
            } else {
                // Otherwise, move right
                j++;
            }
            path.add("(" + i + ", " + j + ")");
        }

        System.out.println(String.join(" -> ", path));
    }
    

    public static void main(String[] args) {
        // Example 1
        int[][] dungeon1 = {
            {-2, -3, 3},
            {-5, -10, 1},
            {10, 30, -5}
        };
        System.out.println("Minimum initial health (Example 1): " + calculateMinimumHP(dungeon1));
        // Output: 7

        // Example 2
        int[][] dungeon2 = {
            {0, -3}
        };
        System.out.println("Minimum initial health (Example 2): " + calculateMinimumHP(dungeon2));
        // Output: 4
    }
}