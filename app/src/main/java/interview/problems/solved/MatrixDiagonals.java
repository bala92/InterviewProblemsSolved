package interview.problems.solved;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixDiagonals {

    /**
     * Prints all primary and secondary diagonals of the given matrix.
     *
     * @param matrix The input 2D matrix.
     */
    public static void printDiagonals(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Maps to store primary and secondary diagonals
        Map<Integer, List<Integer>> primaryDiagonals = new HashMap<>();
        Map<Integer, List<Integer>> secondaryDiagonals = new HashMap<>();

        // Traverse the matrix to group elements by their diagonal indices
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Group elements for primary diagonals (i - j)
                int primaryKey = i - j;
                primaryDiagonals.putIfAbsent(primaryKey, new ArrayList<>());
                primaryDiagonals.get(primaryKey).add(matrix[i][j]);

                // Group elements for secondary diagonals (i + j)
                int secondaryKey = i + j;
                secondaryDiagonals.putIfAbsent(secondaryKey, new ArrayList<>());
                secondaryDiagonals.get(secondaryKey).add(matrix[i][j]);
            }
        }

        // Print primary diagonals
        System.out.println("Primary Diagonals:");
        for (int key : primaryDiagonals.keySet()) {
            System.out.println(primaryDiagonals.get(key));
        }

        // Print secondary diagonals
        System.out.println("Secondary Diagonals:");
        for (int key : secondaryDiagonals.keySet()) {
            System.out.println(secondaryDiagonals.get(key));
        }
    }

    public static void main(String[] args) {
        // Example 1
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Diagonals of matrix1:");
        printDiagonals(matrix1);

        // Example 2
        int[][] matrix2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        System.out.println("Diagonals of matrix2:");
        printDiagonals(matrix2);
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Primary Diagonals:
 *   - Elements in the same primary diagonal share the same value of i - j (row index - column index).
 *   - Use a HashMap to group elements by their i - j value.
 * - Secondary Diagonals:
 *   - Elements in the same secondary diagonal share the same value of i + j (row index + column index).
 *   - Use a HashMap to group elements by their i + j value.
 *
 * Example Walkthrough:
 * Input:
 * matrix = [
 *     [1, 2, 3],
 *     [4, 5, 6],
 *     [7, 8, 9]
 * ]
 * - Primary Diagonals:
 *   - i - j = 0 → [1, 5, 9]
 *   - i - j = -1 → [2, 6]
 *   - i - j = -2 → [3]
 *   - i - j = 1 → [4, 8]
 *   - i - j = 2 → [7]
 * - Secondary Diagonals:
 *   - i + j = 0 → [1]
 *   - i + j = 1 → [2, 4]
 *   - i + j = 2 → [3, 5, 7]
 *   - i + j = 3 → [6, 8]
 *   - i + j = 4 → [9]
 *
 * Complexity:
 * - Time Complexity: O(N * M), where N is the number of rows and M is the number of columns.
 * - Space Complexity: O(N * M), for storing the diagonals in the maps.
 */