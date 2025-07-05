package interview.problems.solved;

public class LongestOnesWithWildcard {

    /**
     * Finds the longest contiguous subarray of 1s with at most one 0 flipped to 1.
     *
     * @param nums The binary array.
     * @return The length of the longest subarray of 1s.
     */
    public static int longestOnesWithWildcard(int[] nums) {
        int left = 0; // Left pointer of the sliding window
        int maxLength = 0; // Maximum length of subarray
        int zeroCount = 0; // Count of 0s in the current window

        // Iterate through the array with the right pointer
        for (int right = 0; right < nums.length; right++) {
            // If the current element is 0, increment the zero count
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If the zero count exceeds 1, shrink the window from the left
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Update the maximum length of the window
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        // Test cases
        int[] nums1 = {1, 0, 1, 1, 0, 1, 0,};
        System.out.println("Longest 1s with wildcard (Example 1): " + longestOnesWithWildcard(nums1)); // Output: 4

        int[] nums2 = {1, 1, 0, 1};
        System.out.println("Longest 1s with wildcard (Example 2): " + longestOnesWithWildcard(nums2)); // Output: 4

        int[] nums3 = {1, 1, 1, 1};
        System.out.println("Longest 1s with wildcard (Example 3): " + longestOnesWithWildcard(nums3)); // Output: 4

        int[] nums4 = {0, 0, 0, 1};
        System.out.println("Longest 1s with wildcard (Example 4): " + longestOnesWithWildcard(nums4)); // Output: 2
    }
}