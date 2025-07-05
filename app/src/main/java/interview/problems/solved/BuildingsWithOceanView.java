package interview.problems.solved;

import java.util.*;

public class BuildingsWithOceanView {

    /**
     * Finds the indices of buildings with an ocean view.
     *
     * @param heights The array of building heights.
     * @return A list of indices of buildings with an ocean view.
     */
    public static int[] findBuildings(int[] heights) {
        List<Integer> result = new ArrayList<>();
        int maxHeight = 0;

        // Traverse from right to left
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > maxHeight) {
                result.add(i);
                maxHeight = heights[i];
            }
        }

        // Reverse the result to get indices in increasing order
        Collections.reverse(result);

        // Convert the result to an array
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        // Example 1
        int[] heights1 = {4, 2, 3, 1};
        System.out.println("Buildings with Ocean View: " + Arrays.toString(findBuildings(heights1)));
        // Output: [0, 2, 3]

        // Example 2
        int[] heights2 = {4, 3, 2, 1};
        System.out.println("Buildings with Ocean View: " + Arrays.toString(findBuildings(heights2)));
        // Output: [0, 1, 2, 3]

        // Example 3
        int[] heights3 = {1, 3, 2, 4};
        System.out.println("Buildings with Ocean View: " + Arrays.toString(findBuildings(heights3)));
        // Output: [3]
    }
}