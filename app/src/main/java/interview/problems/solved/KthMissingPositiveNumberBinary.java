package interview.problems.solved;

/**
 * LeetCode 1539: Kth Missing Positive Number
 *
 * Problem:
 * Given a sorted array arr of distinct positive integers and an integer k, return the kth missing positive integer.
 *
 * Example:
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,...]. The 5th missing is 9.
 *
 * Approach:
 * ----------
 * Binary Search:
 * - For each index i, the number of missing positive integers before arr[i] is arr[i] - (i + 1).
 *   - Example: For arr = [2,3,4,7,11], at i=3 (arr[3]=7), missing = 7 - (3+1) = 3 (missing numbers before 7 are 1,5,6).
 * - We want the smallest index i such that the number of missing numbers before arr[i] is >= k.
 * - Use binary search to find this index:
 *   - If missingBeforeMid < k, move left to mid+1 (not enough missing numbers yet).
 *   - Else, move right to mid-1 (too many missing numbers, look left).
 * - After the loop, left is the position where the kth missing number would fit.
 * - The answer is left + k (since left numbers are present, and k more are missing).
 *
 * Time Complexity: O(log n), where n is the length of arr (binary search).
 * Space Complexity: O(1), only constant extra space is used.
 */

public class KthMissingPositiveNumberBinary {

    /**
     * Finds the kth missing positive number using a binary search approach.
     *                                                                      
     * Use binary search to find the smallest index i such that the number of missing integers before arr[i] is greater than or equal to k.
     * Calculate the kth missing number as left + k.
     *
     * @param arr The sorted array of distinct positive integers.
     * @param k   The kth missing positive number to find.
     * @return The kth missing positive number.
     */
    public static int findKthPositiveBinary(int[] arr, int k) {
        //Meta 
        int left = 0, right = arr.length - 1;

        // Perform binary search
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Calculate the number of missing integers before arr[mid]
            int missingBeforeMid = arr[mid] - (mid + 1);

            if (missingBeforeMid < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // The kth missing number is in the gap after arr[right]
        //Since left is the first index where the number of missing integers is >= k,
        // the kth missing number is left + k.
        // Example: If left = 4 and k = 5, the kth missing number is 4 + 5 = 9.
        // If arr = [2,3,4,7,11], then the missing numbers are [1,5,6,8,9,10,12,...]
        // The 5th missing number is 9.
        // At index 4 (arr[4] = 11), the missing count is 11 - (4 + 1) = 6, so there are enough missing numbers which is >= k (5).
        // Thus, the kth missing number is left + k.
        // If left = 4, then the kth missing number is 4 + 5 = 9.
        // This works because left is the first index where the number of missing integers is >= k
        // and we need to add k to find the kth missing number.
        return left + k;
    }

    public static void main(String[] args) {
        // Example 1
        int[] arr1 = {2, 3, 4, 7, 11};
        int k1 = 5;
        System.out.println("Kth Missing Positive Number (Binary): " + findKthPositiveBinary(arr1, k1)); // Output: 9

        // Example 2
        int[] arr2 = {1, 2, 3, 4};
        int k2 = 2;
        System.out.println("Kth Missing Positive Number (Binary): " + findKthPositiveBinary(arr2, k2)); // Output: 6
    }
}