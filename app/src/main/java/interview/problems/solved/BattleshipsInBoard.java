package interview.problems.solved;

/**
 * LeetCode 419: Battleships in a Board
 *
 * Problem Description:
 * ---------------------
 * Given an `m x n` board where each cell is a battleship ('X') or empty ('.'), return the number of battleships on the board.
 * Battleships can only be placed horizontally or vertically on the board. In other words, they can only occupy consecutive cells
 * in a row or column. There must be at least one cell separating two battleships (either horizontally or vertically).
 *
 * Examples:
 * ---------
 * Example 1:
 * Input:
 * board = [
 *   ['X', '.', '.', 'X'],
 *   ['.', '.', '.', 'X'],
 *   ['.', '.', '.', 'X']
 * ]
 * Output: 2
 *
 * Example 2:
 * Input:
 * board = [
 *   ['X', 'X', '.', 'X'],
 *   ['.', '.', '.', 'X'],
 *   ['.', '.', '.', 'X']
 * ]
 * Output: 2
 *
 * Approach:
 * ---------
 * - Use a greedy approach to count battleships.
 * - Traverse the board cell by cell.
 * - Count a battleship only if:
 *   1. The current cell is 'X'.
 *   2. It is the **start of a battleship**, i.e., there is no 'X' directly above or to the left of the current cell.
 * - This ensures that each battleship is counted exactly once.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(m * n), where `m` is the number of rows and `n` is the number of columns.
 *   - We traverse each cell of the board once.
 * - Space Complexity: O(1).
 *   - No additional space is used.
 */

public class BattleshipsInBoard {

    /**
     * Counts the number of battleships on the board.
     *
     * @param board The input board represented as a 2D character array.
     * @return The number of battleships on the board.
     */
    public static int countBattleships(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0; // Handle edge case of an empty board
        }

        int count = 0;

        // Traverse the board row by row and column by column
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // Check if the current cell is 'X'
                if (board[i][j] == 'X') {
                    // Check if it's the start of a battleship
                    // A cell is the start of a battleship if:
                    // 1. There is no 'X' directly above it (i == 0 or board[i-1][j] != 'X').
                    // 2. There is no 'X' directly to the left of it (j == 0 or board[i][j-1] != 'X').
                    if ((i == 0 || board[i - 1][j] != 'X') && (j == 0 || board[i][j - 1] != 'X')) {
                        count++; // Increment the count for a new battleship
                    }
                }
            }
        }

        return count; // Return the total number of battleships
    }

    public static void main(String[] args) {
        // Example 1
        char[][] board1 = {
            {'X', '.', '.', 'X'},
            {'.', '.', '.', 'X'},
            {'.', '.', '.', 'X'}
        };
        System.out.println("Number of battleships (Example 1): " + countBattleships(board1)); // Output: 2

        // Example 2
        char[][] board2 = {
            {'X', 'X', '.', 'X'},
            {'.', '.', '.', 'X'},
            {'.', '.', '.', 'X'}
        };
        System.out.println("Number of battleships (Example 2): " + countBattleships(board2)); // Output: 2
    }
}