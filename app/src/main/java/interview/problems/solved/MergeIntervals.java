package interview.problems.solved;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    /**
     * Function to merge overlapping intervals.
     *
     * @param intervals 2D array of intervals where each interval is [start, end].
     * @return A 2D array of merged intervals.
     */
    public static int[][] merge(int[][] intervals) {
        // Step 1: Sort intervals by their start time
        // Sorting ensures that overlapping intervals are adjacent
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Initialize a list to store merged intervals
        List<int[]> merged = new ArrayList<>();

        // Step 3: Iterate through the intervals
        for (int[] interval : intervals) {
            // If the list is empty or the current interval does not overlap with the last one
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                // Add the current interval to the list
                merged.add(interval);
            } else {
                // Overlap exists, merge the intervals by updating the end time
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        // Convert the list to a 2D array and return
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        // Example input
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        // Call the merge function
        int[][] result = merge(intervals);

        // Print the result
        System.out.print("Merged Intervals: ");
        for (int[] interval : result) {
            System.out.print(Arrays.toString(interval) + " ");
        }
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **Sorting the Intervals**:
 *    - The intervals are sorted by their start times using `Arrays.sort()`.
 *    - Sorting ensures that overlapping intervals are adjacent in the array.
 *
 * 2. **Merging Logic**:
 *    - A `List<int[]>` is used to store the merged intervals.
 *    - For each interval:
 *      - If the list is empty or the current interval does not overlap with the last interval in the list,
 *        add it to the list.
 *      - If the current interval overlaps with the last interval, merge them by updating the end time of
 *        the last interval to the maximum of the two overlapping intervals.
 *
 * 3. **Converting the Result**:
 *    - The `List<int[]>` is converted to a 2D array using `toArray()` before returning.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
 *
 * Steps:
 * 1. **Sort the Intervals**:
 *    [[1, 3], [2, 6], [8, 10], [15, 18]]
 *
 * 2. **Iterate and Merge**:
 *    - Start with an empty list: `merged = []`.
 *    - Add `[1, 3]` to `merged`: `merged = [[1, 3]]`.
 *    - Merge `[2, 6]` with `[1, 3]`: `merged = [[1, 6]]`.
 *    - Add `[8, 10]` to `merged`: `merged = [[1, 6], [8, 10]]`.
 *    - Add `[15, 18]` to `merged`: `merged = [[1, 6], [8, 10], [15, 18]]`.
 *
 * 3. **Convert to Array**:
 *    [[1, 6], [8, 10], [15, 18]]
 *
 * Output:
 * Merged Intervals: [1, 6] [8, 10] [15, 18]
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - Sorting the intervals takes `O(n log n)`.
 *    - Iterating through the intervals takes `O(n)`.
 *    - Overall: **`O(n log n)`**.
 *
 * 2. **Space Complexity**:
 *    - The result list uses `O(n)` space.
 *    - Overall: **`O(n)`**.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: int[][] intervals = {{1, 4}, {4, 5}};
 * Output: [[1, 5]]
 *
 * Test Case 2:
 * Input: int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
 * Output: [[1, 6], [8, 10], [15, 18]]
 *
 * Test Case 3:
 * Input: int[][] intervals = {{1, 4}, {2, 3}};
 * Output: [[1, 4]]
 */