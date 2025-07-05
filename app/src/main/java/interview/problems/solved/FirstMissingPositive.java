package interview.problems.solved;

/**
 * Problem Description:
 * ---------------------
 * Given an unsorted integer array `nums`, find the smallest missing positive integer.
 *
 * Constraints:
 * 1. The algorithm must run in O(n) time.
 * 2. The algorithm must use constant extra space.
 *
 * Approach:
 * ---------
 * 1. The smallest missing positive integer must be in the range [1, n+1], where `n` is the size of the array.
 * 2. Rearrange the array such that each positive integer `x` is placed at index `x-1` (if `x` is within the range [1, n]).
 * 3. Iterate through the array to find the first index `i` where `nums[i] != i + 1`. The missing positive integer is `i + 1`.
 * 4. If all numbers are in their correct positions, the missing positive integer is `n + 1`.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), as each number is placed in its correct position at most once.
 * - Space Complexity: O(1), as the algorithm uses constant extra space.
 */
public class FirstMissingPositive {

    /**
     * Function to find the first missing positive integer.
     *
     * @param nums The input array.
     * @return The smallest missing positive integer.
     */
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Step 1: Place each number in its correct position
        for (int i = 0; i < n; i++) {
            // Take nums[i] and place it at its correct index (nums[i] - 1)
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        // Step 2: Find the first missing positive integer
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // If all numbers are in their correct positions, return n + 1
        return n + 1;
    }

    /**
     * Helper function to swap two elements in the array.
     *
     * @param nums The array.
     * @param i    The first index.
     * @param j    The second index.
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {1, 2, 0};
        System.out.println("First missing positive: " + firstMissingPositive(nums1)); // Output: 3

        // Example 2
        int[] nums2 = {3, 4, -1, 1};
        System.out.println("First missing positive: " + firstMissingPositive(nums2)); // Output: 2

        // Example 3
        int[] nums3 = {7, 8, 9, 11, 12};
        System.out.println("First missing positive: " + firstMissingPositive(nums3)); // Output: 1
    }
}

/**
 * Explanation of the Code:
 * -------------------------
 * 1. **Key Insight**:
 *    - The smallest missing positive integer must be in the range [1, n+1], where `n` is the size of the array.
 *    - Rearrange the array such that each positive integer `x` is placed at index `x-1` if `x` is within the range [1, n].
 *
 * 2. **Rearranging the Array**:
 *    - Use a `while` loop to place each number in its correct position.
 *    - For example, if `nums[i] = 3`, place it at index `2` (i.e., `nums[2]`).
 *    - Skip numbers that are out of range (e.g., negative numbers or numbers greater than `n`).
 *
 * 3. **Finding the Missing Positive**:
 *    - After rearranging, iterate through the array to find the first index `i` where `nums[i] != i + 1`.
 *    - The missing positive integer is `i + 1`.
 *    - If all numbers are in their correct positions, return `n + 1`.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * nums = [3, 4, -1, 1]
 *
 * Steps:
 * 1. Initial Array:
 *    nums = [3, 4, -1, 1]
 *
 * 2. Rearrange the Array:
 *    - Place `3` at index `2`: nums = [-1, 4, 3, 1]
 *    - Place `4` at index `3`: nums = [-1, 1, 3, 4]
 *    - Place `1` at index `0`: nums = [1, -1, 3, 4]
 *
 * 3. Find the Missing Positive:
 *    - Iterate through the array:
 *      - At index `0`: nums[0] = 1 (correct).
 *      - At index `1`: nums[1] = -1 (incorrect).
 *    - The first missing positive is `2`.
 *
 * Output:
 * 2
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - Rearranging the array: Each number is placed in its correct position at most once, so O(n).
 *    - Finding the missing positive: A single pass through the array, so O(n).
 *    - Overall: O(n).
 *
 * 2. **Space Complexity**:
 *    - The algorithm uses constant extra space, so O(1).
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: nums = [1, 2, 0]
 * Output: 3
 *
 * Test Case 2:
 * Input: nums = [3, 4, -1, 1]
 * Output: 2
 *
 * Test Case 3:
 * Input: nums = [7, 8, 9, 11, 12]
 * Output: 1
 *
 * Test Case 4:
 * Input: nums = [2, 1]
 * Output: 3
 */