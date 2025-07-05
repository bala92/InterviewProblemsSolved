package interview.problems.solved;

import java.util.*;

/**
 * Problem: Merge Two Arrays of Intervals (Two-Pointer Approach)
 * 
 * Description:
 * Given two arrays of intervals, where intervals in each array do not overlap,
 * merge the two arrays into a single array of intervals.
 * The resulting intervals should also not overlap and should be sorted by their
 * start times.
 * 
 * Example Input:
 * intervals1 = [[1, 3], [5, 7], [9, 11]]
 * intervals2 = [[2, 4], [6, 8], [10, 12]]
 * 
 * Example Output:
 * [[1, 4], [5, 8], [9, 12]]
 * 
 * Approach:
 * 1. Use two pointers to iterate through both arrays of intervals.
 * 2. Compare intervals from both arrays and merge them as needed.
 * 3. Add any remaining intervals from either array after merging.
 * 
 * Complexity:
 * - Time Complexity: O(N + M), where N and M are the sizes of the two arrays.
 * - Space Complexity: O(N + M), for storing the merged intervals.
 * 
 * Explanation:
 * - The two-pointer approach ensures that we process both arrays in a single
 * pass.
 * - By comparing the start times of intervals, we can merge them in sorted
 * order without explicitly sorting the combined intervals.
 * - The helper method `mergeOrAdd` handles the logic for merging overlapping
 * intervals or adding non-overlapping intervals to the result.
 * - After processing one array, any remaining intervals in the other array are
 * directly added to the result.
 * 
 * Author: GitHub Copilot
 * Date: May 5, 2025
 */
public class MergeIntervalsTwoPointer {

    public static int[][] mergeIntervals(int[][] intervals1, int[][] intervals2) {
        List<int[]> merged = new ArrayList<>();
        int i = 0, j = 0;

        // Merge intervals using two pointers
        while (i < intervals1.length && j < intervals2.length) {
            int[] interval1 = intervals1[i];
            int[] interval2 = intervals2[j];

            // Choose the interval with the smaller start time
            if (interval1[0] <= interval2[0]) {
                mergeOrAdd(merged, interval1);
                i++;
            } else {
                mergeOrAdd(merged, interval2);
                j++;
            }
        }

        // Add remaining intervals from intervals1
        while (i < intervals1.length) {
            mergeOrAdd(merged, intervals1[i]);
            i++;
        }

        // Add remaining intervals from intervals2
        while (j < intervals2.length) {
            mergeOrAdd(merged, intervals2[j]);
            j++;
        }

        // Convert the merged list back to an array
        return merged.toArray(new int[merged.size()][]);
    }

    // Helper method to merge or add an interval to the merged list
    private static void mergeOrAdd(List<int[]> merged, int[] interval) {
        if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
            // No overlap, add the interval
            merged.add(interval);
        } else {
            // Overlap, merge the intervals
            merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
        }
    }

    public static void main(String[] args) {
        // Example input
        int[][] intervals1 = { { 1, 3 }, { 5, 7 }, { 9, 11 } };
        int[][] intervals2 = { { 2, 4 }, { 6, 8 }, { 10, 12 } };

        // Merge intervals
        int[][] result = mergeIntervals(intervals1, intervals2);

        // Print the result
        System.out.println("Merged Intervals:");
        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }
    }
}