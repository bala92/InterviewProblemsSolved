
package interview.problems.solved;

/**
 * LeetCode 75: Sort Colors
 *
 * Problem Description:
 * ---------------------
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white, and blue.
 * We use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function.
 *
 * Examples:
 * ---------
 * Example 1:
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Example 2:
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 *
 * Approach:
 * ---------
 * - Use the Dutch National Flag algorithm (three pointers).
 * - Maintain three regions: [0..low-1] are 0s, [low..mid-1] are 1s, [high+1..n-1] are 2s.
 * - Traverse the array with a pointer mid:
 *   - If nums[mid] == 0: swap nums[low] and nums[mid], increment low and mid.
 *   - If nums[mid] == 1: just increment mid.
 *   - If nums[mid] == 2: swap nums[mid] and nums[high], decrement high.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the length of nums.
 * - Space Complexity: O(1), in-place sorting.
 */

public class SortColors {

    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        // Dutch National Flag algorithm
        while (mid <= high) {
            if (nums[mid] == 0) {
                // Swap 0 to the front
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // Leave 1 in the middle
                mid++;
            } else { // nums[mid] == 2
                // Swap 2 to the end
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.print("Sorted colors: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        // Output: Sorted colors: 0 0 1 1 2 2
    }
}