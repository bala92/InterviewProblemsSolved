package interview.problems.solved;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KSmallestPairs {

    /**
     * Function to find k pairs with the smallest sums.
     *
     * @param nums1 The first sorted array.
     * @param nums2 The second sorted array.
     * @param k     The number of pairs to find.
     * @return A list of k pairs with the smallest sums.
     */
    public static List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || k <= 0) {
            return result;
        }

        // Min-Heap to store pairs along with their sums
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0] + a[1], b[0] + b[1]));

        // Initialize the heap with the first k pairs (nums1[i], nums2[0])
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            minHeap.offer(new int[]{nums1[i], nums2[0], 0}); // {nums1[i], nums2[0], index in nums2}
        }

        // Extract the smallest pairs from the heap
        while (k > 0 && !minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            result.add(new int[]{current[0], current[1]});
            k--;

            // If there are more elements in nums2 for the current nums1[i], add the next pair
            int nums2Index = current[2];
            if (nums2Index + 1 < nums2.length) {
                minHeap.offer(new int[]{current[0], nums2[nums2Index + 1], nums2Index + 1});
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6, 6};
        int k = 4;

        List<int[]> result = kSmallestPairs(nums1, nums2, k);

        System.out.println("K pairs with the smallest sums:");
        for (int[] pair : result) {
            System.out.println("[" + pair[0] + ", " + pair[1] + "]");
        }
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **Min-Heap**:
 *    - The heap stores pairs `(nums1[i], nums2[j])` along with their sum.
 *    - The heap is sorted by the sum of the pairs.
 *
 * 2. **Initialization**:
 *    - Push the first `min(k, nums1.length)` pairs `(nums1[i], nums2[0])` into the heap.
 *    - Each pair is represented as `{nums1[i], nums2[0], index in nums2}`.
 *
 * 3. **Extracting Pairs**:
 *    - Extract the smallest pair from the heap and add it to the result.
 *    - If there are more elements in `nums2` for the current `nums1[i]`, push the next pair `(nums1[i], nums2[j+1])` into the heap.
 *
 * 4. **Stopping Condition**:
 *    - Stop when `k` pairs are found or the heap is empty.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * nums1 = [1, 7, 11], nums2 = [2, 4, 6], k = 3
 *
 * Steps:
 * 1. Initialize the heap with:
 *    - (1, 2), (7, 2), (11, 2)
 * 2. Extract (1, 2), add (1, 4) to the heap.
 * 3. Extract (1, 4), add (1, 6) to the heap.
 * 4. Extract (1, 6).
 * 5. Result: [[1, 2], [1, 4], [1, 6]].
 *
 * Output:
 * K pairs with the smallest sums:
 * [1, 2]
 * [1, 4]
 * [1, 6]
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - Inserting into and extracting from the heap takes `O(log(k))`.
 *    - We perform this operation up to `k` times.
 *    - Overall time complexity: `O(k * log(k))`.
 *
 * 2. **Space Complexity**:
 *    - The heap can contain up to `min(k, nums1.length)` elements.
 *    - Space complexity: `O(k)`.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: nums1 = [1, 7, 11], nums2 = [2, 4, 6], k = 3
 * Output: [[1, 2], [1, 4], [1, 6]]
 *
 * Test Case 2:
 * Input: nums1 = [1, 1, 2], nums2 = [1, 2, 3], k = 2
 * Output: [[1, 1], [1, 1]]
 *
 * Test Case 3:
 * Input: nums1 = [1, 2], nums2 = [3], k = 3
 * Output: [[1, 3], [2, 3]]
 */