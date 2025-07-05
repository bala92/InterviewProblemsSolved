package interview.problems.solved;

/**
 * LeetCode 463: Island Perimeter
 *
 * Problem:
 * You are given a 2D grid map of '1's (land) and '0's (water). Grid cells are connected horizontally/vertically (not diagonally).
 * The grid is completely surrounded by water, and there is exactly one island (one or more connected land cells).
 * Return the perimeter of the island.
 *
 * Example:
 * Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * Output: 16
 *
 * Approach:
 * - Iterate through each cell in the grid.
 * - For each land cell ('1'), add 4 to the perimeter.
 * - Subtract 2 for each adjacent land cell (to the right and down) to avoid double-counting shared edges.
 *
 * Time Complexity: O(m * n), where m and n are the grid dimensions.
 * Space Complexity: O(1), only constant extra space is used.
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int rows = grid.length, cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;
                    if (i > 0 && grid[i - 1][j] == 1) perimeter -= 2; // Up neighbor
                    if (j > 0 && grid[i][j - 1] == 1) perimeter -= 2; // Left neighbor
                }
            }
        }
        return perimeter;
    }

    // Example usage
    public static void main(String[] args) {
        IslandPerimeter solver = new IslandPerimeter();
        int[][] grid = {
            {0,1,0,0},
            {1,1,1,0},
            {0,1,0,0},
            {1,1,0,0}
        };
        System.out.println("Island perimeter: " + solver.islandPerimeter(grid)); // Output: 16
    }
}
