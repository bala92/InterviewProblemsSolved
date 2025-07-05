package interview.problems.solved;

import java.util.*;

public class DiagonalTraverse {

    /**
     * Returns the elements of the matrix in diagonal order.
     *
     * @param mat The input matrix.
     * @return An array of elements in diagonal order.
     */
    public static int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) {
            return new int[0];
        }

        int m = mat.length; // Number of rows
        int n = mat[0].length; // Number of columns

        // Map to store elements grouped by their diagonal index (r + c)
        Map<Integer, List<Integer>> diagonalMap = new HashMap<>();

        // Traverse the matrix and group elements by diagonal index
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int diagonalIndex = r + c;
                diagonalMap.putIfAbsent(diagonalIndex, new ArrayList<>());
                diagonalMap.get(diagonalIndex).add(mat[r][c]);
            }
        }

        // Prepare the result array
        int[] result = new int[m * n];
        int index = 0;

        // Traverse the diagonals in order
        for (int key = 0; key <= m + n - 2; key++) {
            List<Integer> diagonal = diagonalMap.get(key);

            // Reverse the diagonal if the index is even
            if (key % 2 == 0) {
                Collections.reverse(diagonal);
            }

            // Add the diagonal elements to the result
            for (int num : diagonal) {
                result[index++] = num;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1
        int[][] mat1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Diagonal Order: " + Arrays.toString(findDiagonalOrder(mat1)));
        // Output: [1, 2, 4, 7, 5, 3, 6, 8, 9]

        // Example 2
        int[][] mat2 = {
            {1, 2},
            {3, 4}
        };
        System.out.println("Diagonal Order: " + Arrays.toString(findDiagonalOrder(mat2)));
        // Output: [1, 2, 3, 4]
    }
}

/**
 * Explanation:
 *
 * Step 1: Group Elements by Diagonal Index
 * - Traverse the matrix and group elements by their diagonal index (r + c) using a Map<Integer, List<Integer>>.
 * - Example for mat = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]:
 *   Diagonal 0: [1]
 *   Diagonal 1: [2, 4]
 *   Diagonal 2: [3, 5, 7]
 *   Diagonal 3: [6, 8]
 *   Diagonal 4: [9]
 *
 * Step 2: Traverse Diagonals
 * - For each diagonal:
 *   - If the diagonal index is even, reverse the list (to traverse up-right).
 *   - If the diagonal index is odd, keep the list as is (to traverse down-left).
 *
 * Step 3: Build the Result
 * - Append the elements of each diagonal to the result array.
 *
 * Complexity:
 * - Time Complexity: O(m * n), where m is the number of rows and n is the number of columns.
 * - Space Complexity: O(m * n), for the Map and result array.
 */