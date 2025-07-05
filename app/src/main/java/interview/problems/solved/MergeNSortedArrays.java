package interview.problems.solved;

import java.util.*;

public class MergeNSortedArrays {

    /**
     * Merges n sorted arrays into a single sorted array.
     *
     * @param arrays The list of sorted arrays.
     * @return A single sorted array containing all elements.
     */
    public static List<Integer> mergeNSortedArrays(List<int[]> arrays) {
        // Min-heap to store (value, arrayIndex, elementIndex)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Add the first element of each array to the heap
        for (int i = 0; i < arrays.size(); i++) {
            if (arrays.get(i).length > 0) {
                minHeap.offer(new int[]{arrays.get(i)[0], i, 0}); // {value, arrayIndex, elementIndex}
            }
        }

        List<Integer> result = new ArrayList<>();

        // Merge the arrays
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int value = current[0];
            int arrayIndex = current[1];
            int elementIndex = current[2];

            // Add the smallest element to the result
            result.add(value);

            // If there is a next element in the same array, add it to the heap
            if (elementIndex + 1 < arrays.get(arrayIndex).length) {
                minHeap.offer(new int[]{arrays.get(arrayIndex)[elementIndex + 1], arrayIndex, elementIndex + 1});
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example input: 3 sorted arrays
        List<int[]> arrays = Arrays.asList(
            new int[]{1, 4, 7},
            new int[]{2, 5, 8},
            new int[]{3, 6, 9}
        );

        // Call the mergeNSortedArrays method
        List<Integer> mergedArray = mergeNSortedArrays(arrays);

        // Print the result
        System.out.println("Merged Array: " + mergedArray);
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use a min-heap (priority queue) to efficiently extract the smallest element among the arrays.
 * - Each element in the heap is a triplet: (value, arrayIndex, elementIndex).
 * - Add the first element of each array to the heap.
 * - Extract the smallest element from the heap and add it to the result list.
 * - If the extracted element has a next element in its array, add the next element to the heap.
 * - Repeat until the heap is empty.
 *
 * Example Walkthrough:
 * Input:
 * arrays = [
 *     [1, 4, 7],
 *     [2, 5, 8],
 *     [3, 6, 9]
 * ]
 * - Step 1: Initialize the heap with the first element of each array:
 *   - Heap: [(1, 0, 0), (2, 1, 0), (3, 2, 0)]
 * - Step 2: Extract the smallest element (1) and add it to the result:
 *   - Result: [1]
 *   - Add the next element from array 0 (4) to the heap.
 *   - Heap: [(2, 1, 0), (3, 2, 0), (4, 0, 1)]
 * - Step 3: Extract the smallest element (2) and add it to the result:
 *   - Result: [1, 2]
 *   - Add the next element from array 1 (5) to the heap.
 *   - Heap: [(3, 2, 0), (4, 0, 1), (5, 1, 1)]
 * - Repeat until the heap is empty.
 * Output: [1, 2, 3, 4, 5, 6, 7, 8, 9]
 *
 * Complexity:
 * - Time Complexity: O(N * log K), where N is the total number of elements and K is the number of arrays.
 * - Space Complexity: O(K), for storing elements in the heap.
 */