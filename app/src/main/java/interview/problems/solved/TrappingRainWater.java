/*
 * LeetCode 42: Trapping Rain Water
 *
 * Problem Description:
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * Example:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * Explanation:
 * The water trapped at each index is determined by the minimum of the maximum height to the left and right,
 * minus the height at that index. The total trapped water is the sum of water trapped at each index.
 *
 * Approach (Two Pointers):
 * - Use two pointers (left and right) starting from both ends of the array.
 * - Maintain leftMax and rightMax to keep track of the maximum height seen so far from the left and right.
 * - At each step, move the pointer with the smaller height inward.
 *   - If the current height is greater than or equal to the max so far, update the max.
 *   - Otherwise, water can be trapped at this position: add (max - current height) to the result.
 * - Continue until the two pointers meet.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

package interview.problems.solved;

public class TrappingRainWater {

    public static void main(String[] args) {
        // Test case 1: Example from LeetCode
        int[] heights1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Trapped Rain Water (Test 1): " + trappingRainWater(heights1)); // Output: 6

        // Test case 2: No trapped water
        int[] heights2 = {1, 2, 3, 4, 5};
        System.out.println("Trapped Rain Water (Test 2): " + trappingRainWater(heights2)); // Output: 0

        // Test case 3: All bars of same height
        int[] heights3 = {2, 2, 2, 2};
        System.out.println("Trapped Rain Water (Test 3): " + trappingRainWater(heights3)); // Output: 0

        // Test case 4: Valley in the middle
        int[] heights4 = {3, 0, 2, 0, 4};
        System.out.println("Trapped Rain Water (Test 4): " + trappingRainWater(heights4)); // Output: 7

        // Test case 5: Single bar
        int[] heights5 = {4};
        System.out.println("Trapped Rain Water (Test 5): " + trappingRainWater(heights5)); // Output: 0

        // Test case 6: Empty array
        int[] heights6 = {};
        System.out.println("Trapped Rain Water (Test 6): " + trappingRainWater(heights6)); // Output: 0

        // Test case 7: Custom
        int[] heights7 = {1,2,1,1,2,3,2,1,3};
        System.out.println("Trapped Rain Water (Test 7): " + trappingRainWater(heights7));
    }

    public static int trappingRainWater(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;
        int left = 0, right = n - 1;
        int leftMax = 0, rightMax = 0;
        int waterTrapped = 0;

        while (left <= right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    waterTrapped += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    waterTrapped += rightMax - height[right];
                }
                right--;
            }
        }

        return waterTrapped;
    }
}

/*
     * Example Walkthrough:
     * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
     *
     * Step-by-step:
     * left = 0, right = 11, leftMax = 0, rightMax = 0, waterTrapped = 0
     *
     * 1. left=0, right=11: height[left]=0 < height[right]=1
     *    leftMax = max(0,0) = 0, waterTrapped += 0-0 = 0, left++
     * 2. left=1, right=11: height[left]=1 >= leftMax=0, leftMax=1, left++
     * 3. left=2, right=11: height[left]=0 < leftMax=1, waterTrapped += 1-0 = 1, left++
     * 4. left=3, right=11: height[left]=2 >= leftMax=1, leftMax=2, left++
     * 5. left=4, right=11: height[left]=1 < leftMax=2, waterTrapped += 2-1 = 1, left++
     * 6. left=5, right=11: height[left]=0 < leftMax=2, waterTrapped += 2-0 = 2, left++
     * 7. left=6, right=11: height[left]=1 < leftMax=2, waterTrapped += 2-1 = 1, left++
     * 8. left=7, right=11: height[left]=3 >= leftMax=2, leftMax=3, left++
     * 9. left=8, right=11: height[left]=2 < leftMax=3, waterTrapped += 3-2 = 1, left++
     * 10. left=9, right=11: height[left]=1 < leftMax=3, waterTrapped += 3-1 = 2, left++
     * 11. left=10, right=11: height[left]=2 < leftMax=3, waterTrapped += 3-2 = 1, left++
     * 12. left=11, right=11: height[left]=1 < leftMax=3, waterTrapped += 3-1 = 2, left++
     *
     * Total water trapped = 1+1+2+1+1+2+1+2 = 6
     *
     * At each step, we move the pointer with the smaller height, update the max, and add trapped water if possible.
     */

/*
     * Example Walkthrough:
     * Input: height = {3, 0, 2, 0, 4}
     *
     * Step-by-step:
     * left = 0, right = 4, leftMax = 0, rightMax = 0, waterTrapped = 0
     *
     * 1. left=0, right=4: height[left]=3 < height[right]=4
     *    leftMax = max(0,3) = 3, left++
     * 2. left=1, right=4: height[left]=0 < leftMax=3, waterTrapped += 3-0 = 3, left++
     * 3. left=2, right=4: height[left]=2 < leftMax=3, waterTrapped += 3-2 = 1, left++
     * 4. left=3, right=4: height[left]=0 < leftMax=3, waterTrapped += 3-0 = 3, left++
     * 5. left=4, right=4: height[left]=4 >= leftMax=3, leftMax=4, left++
     *
     * Total water trapped = 3 + 1 + 3 = 7
     *
     * At each step, we move the pointer with the smaller height, update the max, and add trapped water if possible.
     */