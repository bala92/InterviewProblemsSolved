package interview.problems.solved;

/**
 * LeetCode 74: Search a 2D Matrix
 *
 * Problem Description:
 * ---------------------
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * - Integers in each row are sorted from left to right.
 * - The first integer of each row is greater than the last integer of the previous row.
 *
 * Example 1:
 * Input: matrix = [
 *   [1, 3, 5, 7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 60]
 * ], target = 3
 * Output: true
 *
 * Example 2:
 * Input: matrix = [
 *   [1, 3, 5, 7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 60]
 * ], target = 13
 * Output: false
 *
 * Approach:
 * ---------
 * - Treat the 2D matrix as a 1D sorted array.
 * - Use binary search to find the target.
 * - Calculate the row and column indices using:
 *   - row = mid / number of columns
 *   - col = mid % number of columns
 * 
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(log(m * n)), where m is the number of rows and n is the number of columns.
 * - Space Complexity: O(1).
 */
public class Search2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / cols][mid % cols];

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public static boolean searchMatrixAlternative(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Step 1: Binary search to find the potential row
        int top = 0, bottom = rows - 1;
        while (top <= bottom) {
            int midRow = top + (bottom - top) / 2;
            if (target < matrix[midRow][0]) {
                bottom = midRow - 1; // Target is smaller, move up
            } else if (target > matrix[midRow][cols - 1]) {
                top = midRow + 1; // Target is larger, move down
            } else {
                // Target is within the range of this row
                top = midRow;
                break;
            }
        }

        // If no valid row is found
        if (top > bottom) {
            return false;
        }

        // Step 2: Binary search within the identified row
        int row = top;
        int left = 0, right = cols - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false; // Target not found
    }

    // linear search alternative
    public static boolean searchMatrixAnotherAlternative(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int col = matrix[0].length - 1;
        int row = 0;

        while (col >= 0 && row < matrix.length) {
            if (target == matrix[row][col]) return true;
            else if (target < matrix[row][col]) col--;
            else if (target > matrix[row][col]) row++;
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] matrix1 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        System.out.println("Search 3 in matrix1: " + searchMatrix(matrix1, 3)); // Output: true
        System.out.println("Search 13 in matrix1: " + searchMatrix(matrix1, 13)); // Output: false

        int[][] matrix2 = {
            {1, 3, 5}
        };
        System.out.println("Search 5 in matrix2: " + searchMatrix(matrix2, 5)); // Output: true

        System.out.println("Search 5 in matrix2 alternative: " + searchMatrixAlternative(matrix2, 5)); // Output: true

        System.out.println("Search 5 in matrix2 another alternative: " + searchMatrixAnotherAlternative(matrix2, 5)); // Output: true
    }
}