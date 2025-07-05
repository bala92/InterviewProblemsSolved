package interview.problems.solved;

public class TicTacToe {

    private int[] rows; // Tracks row counts for each player
    private int[] cols; // Tracks column counts for each player
    private int diagonal; // Tracks diagonal count for each player
    private int antiDiagonal; // Tracks anti-diagonal count for each player
    private int n; // Size of the board

    /**
     * Initializes the Tic Tac Toe board.
     *
     * @param n The size of the board (n x n).
     */
    public TicTacToe(int n) {
        this.n = n;
        this.rows = new int[n];
        this.cols = new int[n];
        this.diagonal = 0;
        this.antiDiagonal = 0;
    }

    /**
     * Makes a move on the board.
     *
     * @param row The row index of the move.
     * @param col The column index of the move.
     * @param player The player making the move (1 or 2).
     * @return 0 if no one wins, 1 if player 1 wins, 2 if player 2 wins.
     */
    public int move(int row, int col, int player) {
        // Determine the value to add for the player
        int value = (player == 1) ? 1 : -1;

        // Update row and column counts
        rows[row] += value;
        cols[col] += value;

        // Update diagonal count if the move is on the main diagonal
        if (row == col) {
            diagonal += value;
        }

        // Update anti-diagonal count if the move is on the anti-diagonal
        if (row + col == n - 1) {
            antiDiagonal += value;
        }

        // Check if the player wins
        if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n ||
            Math.abs(diagonal) == n || Math.abs(antiDiagonal) == n) {
            return player;
        }

        // No winner yet
        return 0;
    }

    public static void main(String[] args) {
        // Example usage
        TicTacToe ticTacToe = new TicTacToe(3);

        System.out.println(ticTacToe.move(0, 0, 1)); // Output: 0
        System.out.println(ticTacToe.move(0, 2, 2)); // Output: 0
        System.out.println(ticTacToe.move(2, 2, 1)); // Output: 0
        System.out.println(ticTacToe.move(1, 1, 2)); // Output: 0
        System.out.println(ticTacToe.move(2, 0, 1)); // Output: 0
        System.out.println(ticTacToe.move(1, 0, 2)); // Output: 0
        System.out.println(ticTacToe.move(2, 1, 1)); // Output: 1 (Player 1 wins)
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use arrays to track the number of moves made by each player in each row, column, and diagonal.
 * - When a player makes a move, update the corresponding row, column, and diagonal counts.
 * - If any row, column, or diagonal count equals n (absolute value), that player wins.
 *
 * Example Walkthrough:
 * Input:
 * TicTacToe(3)
 * move(0, 0, 1) → Player 1 moves at (0, 0):
 *   - rows[0] = 1, cols[0] = 1, diagonal = 1, antiDiagonal = 0 → No winner.
 * move(0, 2, 2) → Player 2 moves at (0, 2):
 *   - rows[0] = 0, cols[2] = -1, diagonal = 1, antiDiagonal = -1 → No winner.
 * move(2, 2, 1) → Player 1 moves at (2, 2):
 *   - rows[2] = 1, cols[2] = 0, diagonal = 2, antiDiagonal = -1 → No winner.
 * move(1, 1, 2) → Player 2 moves at (1, 1):
 *   - rows[1] = -1, cols[1] = -1, diagonal = 1, antiDiagonal = -2 → No winner.
 * move(2, 0, 1) → Player 1 moves at (2, 0):
 *   - rows[2] = 2, cols[0] = 2, diagonal = 1, antiDiagonal = 0 → No winner.
 * move(1, 0, 2) → Player 2 moves at (1, 0):
 *   - rows[1] = -2, cols[0] = 1, diagonal = 1, antiDiagonal = 0 → No winner.
 * move(2, 1, 1) → Player 1 moves at (2, 1):
 *   - rows[2] = 3, cols[1] = 1, diagonal = 1, antiDiagonal = 0 → Player 1 wins.
 *
 * Complexity:
 * - Time Complexity: O(1) per move.
 * - Space Complexity: O(n), where n is the size of the board.
 */