package interview.problems.solved;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem Description:
 * ---------------------
 * You are given an array `row` of even length `n`, where `row[i]` represents the person sitting in the `i-th` seat.
 * There are `n/2` couples, and each couple is represented by two consecutive numbers `(2x, 2x+1)` (e.g., `0` and `1`, `2` and `3`, etc.).
 *
 * The goal is to determine the minimum number of swaps required to arrange the row such that each couple is sitting together.
 *
 * Approach:
 * ---------
 * 1. Use a `HashMap` to store the position of each person in the row.
 *    - The key is the person, and the value is their index in the row.
 *    - This allows for constant-time lookups of a person's position.
 * 2. Iterate through the row in pairs:
 *    - For each person at index `i`, find their partner.
 *    - If the partner is not sitting next to them, swap the partner into the correct position and update the `HashMap`.
 * 3. Count the number of swaps performed.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where `n` is the length of the row.
 * - Space Complexity: O(n), for the `HashMap`.
 */
public class CouplesHoldingHands {

    /**
     * Function to find the minimum number of swaps required to arrange couples together.
     *
     * @param row The input array representing the seating arrangement.
     * @return The minimum number of swaps required.
     */
    public static int minSwapsCouples(int[] row) {
        int n = row.length;
        int swaps = 0;

        // Create a map to store the position of each person
        Map<Integer, Integer> position = new HashMap<>();
        for (int i = 0; i < n; i++) {
            position.put(row[i], i);
        }

        // Iterate through the row in pairs
        for (int i = 0; i < n; i += 2) {
            int first = row[i];
            int second = first % 2 == 0 ? first + 1 : first - 1; // Find the partner

            // If the partner is not next to the first person, swap
            if (row[i + 1] != second) {
                swaps++;

                // Find the current position of the partner
                int partnerIndex = position.get(second);

                // Swap the partner into the correct position
                swap(row, position, i + 1, partnerIndex);
            }
        }

        return swaps;
    }

    /**
     * Helper function to swap two elements in the row and update their positions in the map.
     *
     * @param row       The seating arrangement.
     * @param position  The position map.
     * @param i         The first index.
     * @param j         The second index.
     */
    private static void swap(int[] row, Map<Integer, Integer> position, int i, int j) {
        // Swap the elements in the row
        int temp = row[i];
        row[i] = row[j];
        row[j] = temp;

        // Update the positions in the map
        position.put(row[i], i);
        position.put(row[j], j);
    }

    public static void main(String[] args) {
        // Example 1
        int[] row1 = {0, 2, 1, 3};
        System.out.println("Minimum swaps: " + minSwapsCouples(row1)); // Output: 1

        // Example 2
        int[] row2 = {3, 2, 0, 1};
        System.out.println("Minimum swaps: " + minSwapsCouples(row2)); // Output: 0

        // Example 3
        int[] row3 = {5, 4, 2, 6, 3, 1, 0, 7};
        System.out.println("Minimum swaps: " + minSwapsCouples(row3)); // Output: 2
    }
}

/**
 * Explanation of the Code:
 * -------------------------
 * 1. **Position Map**:
 *    - The `position` map stores the index of each person in the row.
 *    - This allows us to quickly find the index of any person in constant time.
 *
 * 2. **Finding the Partner**:
 *    - For a person `x`, their partner is:
 *      - `x + 1` if `x` is even.
 *      - `x - 1` if `x` is odd.
 *
 * 3. **Swapping**:
 *    - If the partner of the person at index `i` is not at index `i+1`, swap the partner into the correct position.
 *    - Update the `position` map after each swap.
 *
 * 4. **Counting Swaps**:
 *    - Increment the `swaps` counter for each swap performed.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * row = [0, 2, 1, 3]
 *
 * Steps:
 * 1. Create the position map:
 *    - position = {0=0, 2=1, 1=2, 3=3}.
 * 2. Iterate through the row:
 *    - At index 0: `row[0] = 0`, partner is `1`. `row[1] = 2`, so swap `row[1]` and `row[2]`.
 *    - After swap: row = [0, 1, 2, 3], position = {0=0, 1=1, 2=2, 3=3}.
 * 3. No more swaps are needed.
 * 4. Output: 1 swap.
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - Building the position map: `O(n)`.
 *    - Iterating through the row: `O(n)`.
 *    - Swapping elements: `O(1)` per swap.
 *    - Overall: `O(n)`.
 *
 * 2. **Space Complexity**:
 *    - The `position` map uses `O(n)` space.
 *    - Overall: `O(n)`.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: row = [0, 2, 1, 3]
 * Output: 1
 *
 * Test Case 2:
 * Input: row = [3, 2, 0, 1]
 * Output: 0
 *
 * Test Case 3:
 * Input: row = [5, 4, 2, 6, 3, 1, 0, 7]
 * Output: 2
 */