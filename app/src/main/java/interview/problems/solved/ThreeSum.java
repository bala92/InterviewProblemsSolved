package interview.problems.solved;

import java.util.*;

public class ThreeSum {

    /**
     * Finds all unique triplets in the array that sum to zero.
     *
     * @param nums The input array.
     * @return A list of unique triplets that sum to zero.
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Iterate through the array
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate elements for the first number
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Step 3: Use two pointers to find pairs that sum to -nums[i]
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Add the triplet to the result
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicate elements for the second and third numbers
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // Move both pointers
                    left++;
                    right--;
                } else if (sum < 0) {
                    // Move the left pointer to increase the sum
                    left++;
                } else {
                    // Move the right pointer to decrease the sum
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("3Sum Result: " + threeSum(nums1));
        // Output: [[-1, -1, 2], [-1, 0, 1]]

        // Example 2
        int[] nums2 = {};
        System.out.println("3Sum Result: " + threeSum(nums2));
        // Output: []

        // Example 3
        int[] nums3 = {1,2,3,-3};
        System.out.println("3Sum Result: " + threeSum(nums3));
        // Output: []
    }
}