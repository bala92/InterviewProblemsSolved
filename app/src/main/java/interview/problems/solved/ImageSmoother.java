package interview.problems.solved;

import java.util.Arrays;

/**
 * Problem Description:
 * ---------------------
 * Given a 2D integer matrix M representing a grayscale image, smooth the image by replacing
 * each cell's value with the average of its neighbors (including itself).
 *
 * Approach:
 * ---------
 * - Iterate through the matrix and calculate the average of each cell's neighbors.
 * - Use a temporary matrix to store the smoothed values.
 * - Handle boundary conditions by ignoring neighbors outside the matrix.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(m * n), where m and n are the dimensions of the matrix.
 * - Space Complexity: O(m * n), for the temporary matrix.
 */
public class ImageSmoother {

    /**
     * Function to smooth a 2D matrix (image).
     *
     * @param M The input 2D matrix.
     * @return The smoothed 2D matrix.
     */
    public int[][] imageSmoother(int[][] M) {
        int rows = M.length;
        int cols = M[0].length;

        // Temporary matrix to store the smoothed values
        int[][] result = new int[rows][cols];

        // Directions for the 8 neighbors and the cell itself
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},  {0, 0},  {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        // Iterate through each cell in the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int sum = 0;
                int count = 0;

                // Check all neighbors
                for (int[] dir : directions) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];

                    // Check if the neighbor is within bounds
                    if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                        sum += M[ni][nj];
                        count++;
                    }
                }

                // Calculate the average and store it in the result matrix
                result[i][j] = sum / count;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ImageSmoother solution = new ImageSmoother();

        // Example 1
        int[][] M1 = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        System.out.println("Smoothed Image (Example 1):");
        printMatrix(solution.imageSmoother(M1));
        // Output:
        // [[0, 0, 0],
        //  [0, 0, 0],
        //  [0, 0, 0]]

        // Example 2
        int[][] M2 = {
            {100, 200, 100},
            {200, 50, 200},
            {100, 200, 100}
        };
        System.out.println("Smoothed Image (Example 2):");
        printMatrix(solution.imageSmoother(M2));
        // Output:
        // [[137, 141, 137],
        //  [141, 138, 141],
        //  [137, 141, 137]]
    }

    /**
     * Helper function to print a 2D matrix.
     *
     * @param matrix The 2D matrix to print.
     */
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}