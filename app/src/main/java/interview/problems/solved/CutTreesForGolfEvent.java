package interview.problems.solved;

import java.util.*;

/**
 * Problem Description:
 * ---------------------
 * Find the minimum steps required to cut all trees in a forest in increasing order of their height.
 * If it is impossible to cut all the trees, return -1.
 *
 * Approach:
 * ---------
 * 1. Extract all tree positions and their heights.
 * 2. Sort the trees in ascending order of their height.
 * 3. Use BFS to calculate the shortest path between two points in the grid.
 * 4. Accumulate the steps required to cut all trees.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n^2 * m^2), where n and m are the dimensions of the grid.
 * - Space Complexity: O(n * m), for the BFS queue and visited array.
 */
public class CutTreesForGolfEvent {

    /**
     * Function to find the minimum steps to cut all trees in the forest.
     *
     * @param forest The 2D grid representing the forest.
     * @return The minimum steps required to cut all trees, or -1 if impossible.
     */
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0 || forest.get(0).size() == 0) {
            return -1;
        }

        int rows = forest.size();
        int cols = forest.get(0).size();

        // Extract all tree positions and their heights
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }

        // Sort trees by height
        trees.sort(Comparator.comparingInt(a -> a[2]));

        // Start at (0, 0)
        int totalSteps = 0;
        int startX = 0, startY = 0;

        // Iterate through the sorted trees
        for (int[] tree : trees) {
            int targetX = tree[0];
            int targetY = tree[1];

            // Find the shortest path to the next tree
            int steps = bfs(forest, startX, startY, targetX, targetY);
            if (steps == -1) {
                return -1; // Tree is unreachable
            }

            totalSteps += steps;
            startX = targetX;
            startY = targetY;
        }

        return totalSteps;
    }

    /**
     * Helper function to perform BFS and find the shortest path between two points.
     *
     * @param forest The 2D grid representing the forest.
     * @param startX The starting x-coordinate.
     * @param startY The starting y-coordinate.
     * @param targetX The target x-coordinate.
     * @param targetY The target y-coordinate.
     * @return The shortest path between the two points, or -1 if unreachable.
     */
    private int bfs(List<List<Integer>> forest, int startX, int startY, int targetX, int targetY) {
        if (startX == targetX && startY == targetY) {
            return 0; // Already at the target
        }

        int rows = forest.size();
        int cols = forest.get(0).size();
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        int steps = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                for (int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols &&
                            !visited[newX][newY] && forest.get(newX).get(newY) > 0) {
                        if (newX == targetX && newY == targetY) {
                            return steps; // Reached the target
                        }

                        queue.add(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
        }

        return -1; // Target is unreachable
    }

    public static void main(String[] args) {
        CutTreesForGolfEvent solution = new CutTreesForGolfEvent();

        // Example 1
        List<List<Integer>> forest1 = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(0, 0, 4),
                Arrays.asList(7, 6, 5)
        );
        System.out.println("Minimum steps (Example 1): " + solution.cutOffTree(forest1));
        // Output: 6

        // Example 2
        List<List<Integer>> forest2 = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(0, 0, 0),
                Arrays.asList(7, 6, 5)
        );
        System.out.println("Minimum steps (Example 2): " + solution.cutOffTree(forest2));
        // Output: -1

        // Example 3
        List<List<Integer>> forest3 = Arrays.asList(
                Arrays.asList(2, 3, 4),
                Arrays.asList(0, 0, 5),
                Arrays.asList(8, 7, 6)
        );
        System.out.println("Minimum steps (Example 3): " + solution.cutOffTree(forest3));
        // Output: 6
    }
}