package interview.problems.solved;

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {

    // Directions for the 8 possible neighbors
    private static final int[][] DIRECTIONS = {
        {-1, -1}, {-1, 0}, {-1, 1},
        {0, -1},          {0, 1},
        {1, -1}, {1, 0}, {1, 1}
    };

    /**
     * Function to update the Minesweeper board after a click using BFS.
     *
     * @param board The 2D board representing the Minesweeper game.
     * @param click The position of the click.
     * @return The updated board.
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0];
        int col = click[1];

        // If the clicked square is a mine, reveal it as 'X'
        if (board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }

        // Use BFS to reveal squares
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currRow = current[0];
            int currCol = current[1];

            // Count the number of adjacent mines
            int mineCount = countAdjacentMines(board, currRow, currCol);

            if (mineCount > 0) {
                // If there are adjacent mines, reveal the square with the mine count
                board[currRow][currCol] = (char) (mineCount + '0');
            } else {
                // If there are no adjacent mines, reveal the square as 'B'
                board[currRow][currCol] = 'B';

                // Add all unrevealed neighbors to the queue
                for (int[] dir : DIRECTIONS) {
                    int newRow = currRow + dir[0];
                    int newCol = currCol + dir[1];

                    if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length && board[newRow][newCol] == 'E') {
                        board[newRow][newCol] = 'B'; // Mark as visited
                        queue.add(new int[]{newRow, newCol});
                    }
                }
            }
        }

        return board;
    }

    /**
     * Helper function to count the number of adjacent mines.
     *
     * @param board The 2D board.
     * @param row The current row.
     * @param col The current column.
     * @return The number of adjacent mines.
     */
    private int countAdjacentMines(char[][] board, int row, int col) {
        int count = 0;

        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length && board[newRow][newCol] == 'M') {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Minesweeper solution = new Minesweeper();

        // Example 1
        char[][] board1 = {
            {'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'M', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E'}
        };
        int[] click1 = {3, 0};
        char[][] result1 = solution.updateBoard(board1, click1);
        System.out.println("Updated Board (Example 1):");
        printBoard(result1);

        // Example 2
        char[][] board2 = {
            {'B', '1', 'E', '1', 'B'},
            {'B', '1', 'M', '1', 'B'},
            {'B', '1', '1', '1', 'B'},
            {'B', 'B', 'B', 'B', 'B'}
        };
        int[] click2 = {1, 2};
        char[][] result2 = solution.updateBoard(board2, click2);
        System.out.println("Updated Board (Example 2):");
        printBoard(result2);
    }

    /**
     * Helper function to print the board.
     *
     * @param board The 2D board.
     */
    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}