package interview.problems.solved;

import java.util.Arrays;

public class MergeSortedArray {

    /**
     * Merges two sorted arrays into one sorted array in-place.
     *
     * @param nums1 The first sorted array with enough space to hold elements from nums2.
     * @param m     The number of initialized elements in nums1.
     * @param nums2 The second sorted array.
     * @param n     The number of initialized elements in nums2.
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Pointers for nums1, nums2, and the end of nums1
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;

        // Merge nums2 into nums1 starting from the end
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }

        // If there are remaining elements in nums2, copy them
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }
    }

    public static void main(String[] args) {
        // Example input
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        // Merge nums2 into nums1
        merge(nums1, m, nums2, n);

        // Print the result
        System.out.println("Merged Array: " + Arrays.toString(nums1)); // Output: [1, 2, 2, 3, 5, 6]
    }
}