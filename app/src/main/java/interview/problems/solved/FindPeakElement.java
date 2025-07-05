package interview.problems.solved;

public class FindPeakElement {

    /**
     * Finds a peak element in the array using binary search.
     *
     * @param nums The input array.
     * @return The index of a peak element.
     */
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                // Peak is on the left side (including mid)
                right = mid;
            } else {
                // Peak is on the right side
                left = mid + 1;
            }
        }

        // Left and right converge to the peak element
        return left;
    }

        /**
     * Finds min element in the array using binary search.
     *
     * @param nums The input array.
     * @return The index of a min element.
     */
    public static int findMinimumElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // Left and right converge to the peak element
        return left;
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {1, 2, 3, 1, 5};
        System.out.println("Peak Element Index: " + findPeakElement(nums1)); // Output: 2

        // Example 2
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println("Peak Element Index: " + findPeakElement(nums2)); // Output: 5

        // Example 3
        int[] nums3 = {0, 1, 0, 7, 5, 3, 2};
        System.out.println("Min Element Index: " + findMinimumElement(nums3)); // Output: 2
    }
}