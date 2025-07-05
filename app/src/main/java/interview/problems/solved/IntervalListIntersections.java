package interview.problems.solved;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {

    /**
     * Finds the intersection of two interval lists.
     *
     * @param firstList The first list of intervals.
     * @param secondList The second list of intervals.
     * @return A list of intersected intervals.
     */
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;

        // Use two pointers to traverse both lists
        while (i < firstList.length && j < secondList.length) {
            // Find the intersection of firstList[i] and secondList[j]
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);

            // If there is an intersection, add it to the result
            if (start <= end) {
                result.add(new int[]{start, end});
            }

            // Move the pointer of the interval that ends first
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        // Convert the result list to an array
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        // Example 1
        int[][] firstList1 = {{0, 2}, {5, 10}, {13, 23}, {24, 35}};
        int[][] secondList1 = {{1, 5}, {8, 12}, {15, 29}, {30, 36}};
        int[][] result1 = intervalIntersection(firstList1, secondList1);
        System.out.println("Result 1:");
        for (int[] interval : result1) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }

        // Example 2
        int[][] firstList2 = {{1, 3}, {5, 9}};
        int[][] secondList2 = {};
        int[][] result2 = intervalIntersection(firstList2, secondList2);
        System.out.println("Result 2:");
        for (int[] interval : result2) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use two pointers to traverse both interval lists.
 * - For each pair of intervals, calculate the intersection:
 *   - Intersection = [max(start1, start2), min(end1, end2)].
 *   - Add the intersection to the result if it exists (i.e., start <= end).
 * - Move the pointer of the interval that ends first.
 *
 * Example Walkthrough:
 * Input:
 * firstList = [[0,2],[5,10],[13,23],[24,25]]
 * secondList = [[1,5],[8,12],[15,24],[25,26]]
 * - Step 1: Compare [0,2] and [1,5]:
 *   - Intersection = [1,2].
 *   - Move pointer of [0,2] (ends first).
 * - Step 2: Compare [5,10] and [1,5]:
 *   - Intersection = [5,5].
 *   - Move pointer of [1,5] (ends first).
 * - Step 3: Compare [5,10] and [8,12]:
 *   - Intersection = [8,10].
 *   - Move pointer of [5,10] (ends first).
 * - Step 4: Compare [13,23] and [15,24]:
 *   - Intersection = [15,23].
 *   - Move pointer of [13,23] (ends first).
 * - Step 5: Compare [24,25] and [15,24]:
 *   - Intersection = [24,24].
 *   - Move pointer of [15,24] (ends first).
 * - Step 6: Compare [24,25] and [25,26]:
 *   - Intersection = [25,25].
 *   - Move pointer of [24,25] (ends first).
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]].
 *
 * Complexity:
 * - Time Complexity: O(M + N), where M and N are the lengths of the two lists.
 * - Space Complexity: O(M + N), for storing the result.
 */