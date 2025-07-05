package interview.problems.solved;

/**
 * Problem: Flood Fill
 * 
 * You are given a 2D grid (image) represented as a matrix of integers, a starting pixel (sr, sc),
 * and a new color. Replace the color of the starting pixel and all connected pixels with the same
 * color with the new color.
 * 
 * Example:
 * Input:
 * image = [
 *     [1, 1, 1],
 *     [1, 1, 0],
 *     [1, 0, 1]
 * ]
 * sr = 1, sc = 1, newColor = 2
 * 
 * Output:
 * [
 *     [2, 2, 2],
 *     [2, 2, 0],
 *     [2, 0, 1]
 * ]
 * 
 * Approach:
 * - Use Depth-First Search (DFS) to traverse the grid.
 * - Start from the given pixel (sr, sc) and recursively visit all connected pixels with the same color.
 * - Replace the color of each visited pixel with the new color.
 * 
 * Time Complexity: O(N), where N is the total number of pixels in the grid.
 * Space Complexity: O(N) for the recursion stack in the worst case.
 */

public class FloodFill {

    public static void main(String[] args) {
        int[][] image = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int sr = 1, sc = 1, newColor = 2;

        int[][] result = floodFill(image, sr, sc, newColor);

        // Print the result
        for (int[] row : result) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        if (originalColor != newColor) {
            dfs(image, sr, sc, originalColor, newColor);
        }
        return image;
    }

    private static void dfs(int[][] image, int row, int col, int originalColor, int newColor) {
        // Check boundaries and if the current pixel matches the original color
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length || image[row][col] != originalColor) {
            return;
        }

        // Change the color of the current pixel
        image[row][col] = newColor;

        // Recursively call for all 4 directions
        dfs(image, row + 1, col, originalColor, newColor); // Down
        dfs(image, row - 1, col, originalColor, newColor); // Up
        dfs(image, row, col + 1, originalColor, newColor); // Right
        dfs(image, row, col - 1, originalColor, newColor); // Left
    }
}