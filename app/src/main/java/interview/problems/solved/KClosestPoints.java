package interview.problems.solved;

import java.util.PriorityQueue;

public class KClosestPoints {

    /**
     * Finds the k closest points to the origin.
     *
     * @param points The array of points.
     * @param k The number of closest points to return.
     * @return The k closest points to the origin.
     */
    public static int[][] kClosest(int[][] points, int k) {
        // Max-Heap to store the k closest points
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> 
            Integer.compare(distance(b), distance(a))
        );

        // Iterate through all points
        for (int[] point : points) {
            maxHeap.offer(point);

            // If the heap size exceeds k, remove the farthest point
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // Prepare the result array
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }

        return result;
    }

    /**
     * Calculates the squared distance of a point from the origin.
     *
     * @param point The point [x, y].
     * @return The squared distance from the origin.
     */
    private static int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    public static void main(String[] args) {
        // Example 1
        int[][] points1 = {{1, 3}, {-2, 2}};
        int k1 = 1;
        int[][] result1 = kClosest(points1, k1);
        System.out.println("Closest Points (Example 1):");
        for (int[] point : result1) {
            System.out.println("[" + point[0] + ", " + point[1] + "]");
        }

        // Example 2
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        int k2 = 2;
        int[][] result2 = kClosest(points2, k2);
        System.out.println("Closest Points (Example 2):");
        for (int[] point : result2) {
            System.out.println("[" + point[0] + ", " + point[1] + "]");
        }
    }
}