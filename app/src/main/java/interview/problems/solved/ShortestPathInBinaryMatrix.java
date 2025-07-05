package interview.problems.solved;

import java.util.*;

/**
 * Meta interview Question Jun 30th 2025
 * Shortest Path in a Binary Matrix (0 = open, 1 = blocked)
 *
 * Problem:
 * Given a 2D matrix of 0's and 1's, find the shortest path from the top-left (0,0) to the bottom-right (m-1,n-1).
 * You can move up, down, left, or right (no diagonals). List the path taken.
 * Return the path as a list of coordinates, or an empty list if no path exists.
 *
 * Approach:
 * - Use BFS to find the shortest path.
 * - Track the path by storing the previous cell for each visited cell.
 * - Reconstruct the path from end to start using a map.
 *
 * Time Complexity: O(m*n)
 * Space Complexity: O(m*n)
 */
public class ShortestPathInBinaryMatrix {
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
        public String toString() { return "(" + x + "," + y + ")"; }
    }

    public static List<Point> shortestPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1 || grid[m-1][n-1] == 1) return Collections.emptyList();
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        Queue<Point> queue = new LinkedList<>();
        Map<Point, Point> prev = new HashMap<>();
        boolean[][] visited = new boolean[m][n];
        queue.offer(new Point(0,0));
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            if (curr.x == m-1 && curr.y == n-1) {
                // Reconstruct path
                LinkedList<Point> path = new LinkedList<>();
                for (Point p = curr; p != null; p = prev.get(p)) path.addFirst(p);
                return path;
            }
            for (int[] d : dirs) {
                int nx = curr.x + d[0], ny = curr.y + d[1];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n && grid[nx][ny] == 0 && !visited[nx][ny]) {
                    Point next = new Point(nx, ny);
                    queue.offer(next);
                    visited[nx][ny] = true;
                    prev.put(next, curr);
                }
            }
        }
        return Collections.emptyList();
    }

    // Example usage
    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 1, 0},
            {1, 0, 1, 0},
            {0, 0, 0, 0},
            {1, 1, 1, 0}
        };
        List<Point> path = shortestPath(grid);
        if (path.isEmpty()) {
            System.out.println("No path exists.");
        } else {
            System.out.println("Shortest path:");
            for (Point p : path) System.out.print(p + " ");
        }
    }
}
