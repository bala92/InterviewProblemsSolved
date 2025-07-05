package interview.problems.solved;

/**
 * LeetCode 875: Koko Eating Bananas
 * 
 * Problem: Koko loves eating bananas. There are N piles of bananas, the i-th pile has piles[i] bananas.
 * The guards have gone and will come back in H hours. Koko can decide her bananas-per-hour eating speed K.
 * Each hour, she chooses some pile of bananas and eats K bananas from that pile.
 * If the pile has less than K bananas, she eats all of them and won't eat any more bananas during this hour.
 * 
 * Goal: Find minimum K such that Koko can eat all bananas within H hours.
 */

public class KokoEatingBananas {

    /**
     * Returns the minimum eating speed k such that Koko can eat all bananas within h hours.
     *
     * @param piles The array of piles.
     * @param h The number of hours.
     * @return The minimum integer k.
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1; // Minimum possible speed
        int right = 0; // Maximum possible speed (largest pile)

        // Find the maximum pile size
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        // Binary search to find the minimum speed
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canFinish(piles, h, mid)) {
                right = mid; // Try a smaller speed
            } else {
                left = mid + 1; // Try a larger speed
            }
        }

        return left; // The minimum speed
    }

    /**
     * Helper function to check if Koko can finish all bananas at speed k within h hours.
     *
     * @param piles The array of piles.
     * @param h The number of hours.
     * @param k The eating speed.
     * @return True if Koko can finish all bananas, false otherwise.
     */
    private static boolean canFinish(int[] piles, int h, int k) {
        int hours = 0;

        for (int pile : piles) {
            // Calculate the hours needed for this pile
            hours += (pile + k - 1) / k; // Equivalent to Math.ceil(pile / k)
        }

        return hours <= h;
    }

    public static void main(String[] args) {
        // Example 1
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        System.out.println("Minimum Eating Speed (Example 1): " + minEatingSpeed(piles1, h1)); // Output: 4

        // Example 2
        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        System.out.println("Minimum Eating Speed (Example 2): " + minEatingSpeed(piles2, h2)); // Output: 30

        // Example 3
        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 6;
        System.out.println("Minimum Eating Speed (Example 3): " + minEatingSpeed(piles3, h3)); // Output: 23
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use binary search to find the minimum eating speed `k`.
 * - For each `k`, calculate the total hours required to eat all bananas.
 * - If the total hours are less than or equal to `h`, try a smaller `k`.
 * - Otherwise, try a larger `k`.
 *
 * Example Walkthrough:
 * Input: piles = [3, 6, 7, 11], h = 8
 * - Step 1: Define the range for `k`: left = 1, right = 11.
 * - Step 2: Binary search:
 *   - mid = 6 → Total hours = 2 + 1 + 2 + 2 = 7 (<= 8) → Try smaller `k`: right = 6.
 *   - mid = 3 → Total hours = 3 + 2 + 3 + 4 = 12 (> 8) → Try larger `k`: left = 4.
 *   - mid = 5 → Total hours = 3 + 2 + 2 + 3 = 10 (> 8) → Try larger `k`: left = 6.
 * - Output: left = 4.
 *
 * Complexity:
 * - Time Complexity: O(N * log M), where N is the number of piles and M is the maximum pile size.
 * - Space Complexity: O(1), as no additional space is used.
 */