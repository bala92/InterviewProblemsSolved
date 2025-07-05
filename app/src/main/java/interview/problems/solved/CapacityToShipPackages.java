package interview.problems.solved;

/**
 * Problem: 1011 - Capacity To Ship Packages Within D Days
 * 
 * Description:
 * A conveyor belt has packages that must be shipped from one port to another
 * within `D` days.
 * The `i-th` package on the conveyor belt has a weight of `weights[i]`. Each
 * day, we load the ship with packages in the order given by `weights`.
 * We may not split a package across days, and the ship's capacity must be
 * sufficient to carry all packages assigned for that day.
 * 
 * Return the minimum capacity of the ship that will allow all packages to be
 * shipped within `D` days.
 * 
 * Example Input:
 * weights = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 * D = 5
 * 
 * Example Output:
 * 15
 * 
 * Explanation:
 * A ship capacity of 15 is the minimum required to ship all packages within 5
 * days:
 * - Day 1: [1, 2, 3, 4, 5]
 * - Day 2: [6, 7]
 * - Day 3: [8]
 * - Day 4: [9]
 * - Day 5: [10]
 * 
 * Approach:
 * 1. Use binary search to find the minimum ship capacity.
 * 2. The lower bound of the ship's capacity is the maximum weight in the
 * `weights` array (since the ship must carry at least one package).
 * 3. The upper bound is the sum of all weights (if the ship carries all
 * packages in one day).
 * 4. For a given capacity, simulate the shipping process to check if it is
 * possible to ship all packages within `D` days.
 * 
 * Complexity:
 * - Time Complexity: O(N * log(S)), where N is the number of packages and S is
 * the range of possible capacities (sum of weights - max weight).
 * - Space Complexity: O(1), as we use constant extra space.
 * 
 * Author: GitHub Copilot
 * Date: May 8, 2025
 */

public class CapacityToShipPackages {

    public static int shipWithinDays(int[] weights, int D) {
        // Define the search range for the ship's capacity
        int left = 0, right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight); // Minimum capacity is the max weight
            right += weight; // Maximum capacity is the sum of all weights
        }

        // Perform binary search to find the minimum capacity
        while (left < right) {
            int mid = left + (right - left) / 2; // Midpoint of the current range
            if (canShip(weights, D, mid)) {
                right = mid; // Try a smaller capacity
            } else {
                left = mid + 1; // Increase the capacity
            }
        }

        return left; // The minimum capacity to ship all packages within D days
    }

    // Helper method to check if we can ship all packages within D days with the
    // given capacity
    private static boolean canShip(int[] weights, int D, int capacity) {
        int days = 1; // Start with the first day
        int currentLoad = 0;

        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                // Start a new day if the current package exceeds the capacity
                days++;
                currentLoad = 0;
            }
            currentLoad += weight;

            if (days > D) {
                return false; // Exceeded the allowed number of days
            }
        }

        return true; // All packages can be shipped within D days
    }

    public static void main(String[] args) {
        // Example test case
        int[] weights = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int D = 5;

        System.out.println("Minimum ship capacity: " + shipWithinDays(weights, D)); // Output: 15
    }
}