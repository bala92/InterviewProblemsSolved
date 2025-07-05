package interview.problems.solved;

import java.util.Random;

/**
 * Problem Description:
 * ---------------------
 * Given an integer array nums and an integer k, return the kth smallest element in the array.
 *
 * Approach:
 * ---------
 * Use the Quickselect algorithm:
 * - Partition the array around a pivot.
 * - Recursively narrow down the search to the side containing the kth smallest element.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n) on average, O(n²) in the worst case.
 * - Space Complexity: O(1) (in-place).
 */
public class KthSmallestInArray {

    private Random random = new Random();

    /**
     * Function to find the kth smallest element in an array.
     *
     * @param nums The input array.
     * @param k The kth position (1-based index).
     * @return The kth smallest element.
     */
    public int kthSmallest(int[] nums, int k) {
        return quickselect(nums, 0, nums.length - 1, k - 1);
    }

    /**
     * Quickselect algorithm to find the kth smallest element.
     *
     * @param nums The input array.
     * @param left The left boundary of the current partition.
     * @param right The right boundary of the current partition.
     * @param k The kth position (0-based index).
     * @return The kth smallest element.
     */
    private int quickselect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }

        // Choose a random pivot index
        int pivotIndex = left + random.nextInt(right - left + 1);

        // Partition the array around the pivot
        pivotIndex = partition(nums, left, right, pivotIndex);

        // Check if the pivot is the kth element
        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            // Search the left partition
            return quickselect(nums, left, pivotIndex - 1, k);
        } else {
            // Search the right partition
            return quickselect(nums, pivotIndex + 1, right, k);
        }
    }

    /**
     * Partition the array around the pivot.
     *
     * @param nums The input array.
     * @param left The left boundary of the current partition.
     * @param right The right boundary of the current partition.
     * @param pivotIndex The index of the pivot.
     * @return The final position of the pivot.
     */
    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];

        // Move the pivot to the end
        swap(nums, pivotIndex, right);

        int storeIndex = left;

        // Move all elements smaller than the pivot to the left
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }

        // Move the pivot to its final position
        swap(nums, storeIndex, right);

        return storeIndex;
    }

    /**
     * Helper function to swap two elements in the array.
     *
     * @param nums The input array.
     * @param i The first index.
     * @param j The second index.
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        KthSmallestInArray solution = new KthSmallestInArray();

        // Example 1
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println("Kth Smallest Element (k=2): " + solution.kthSmallest(nums1, k1));
        // Output: 2

        // Example 2
        int[] nums2 = {7, 10, 4, 3, 20, 15};
        int k2 = 4;
        System.out.println("Kth Smallest Element (k=4): " + solution.kthSmallest(nums2, k2));
        // Output: 10
    }
}