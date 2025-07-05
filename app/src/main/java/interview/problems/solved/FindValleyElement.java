package interview.problems.solved;

public class FindValleyElement {

    /**
     * Finds a valley element in the array.
     *
     * @param nums The input array.
     * @return The index of a valley element, or -1 if no valley exists.
     */
    public static int findValleyElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1; // No valley exists
        }

        int n = nums.length;

        // // Edge cases
        // if (n == 1) {
        //     return 0; // Single element is a valley
        // }
        // if (nums[0] < nums[1]) {
        //     return 0; // First element is a valley
        // }
        // if (nums[n - 1] < nums[n - 2]) {
        //     return n - 1; // Last element is a valley
        // }

        // Binary search for valley
        int left = 0, right = n - 1; // Avoid edges already checked
        while (left < right) {
            int mid = left + (right - left) / 2 + 1; // Midpoint to the right is because we are checking the mid - 1.
            // if it was without the +1 then we would be checking the mid + 1 during the binary search.

            // // Check if mid is a valley
            // if (nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) {
            //     return mid;
            // }

            // Move to the side with a smaller neighbor
            if (nums[mid - 1] < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {5, 2, 8, 6, 1, 3},        // Valley at index 1 or 4
            {10, 8, 6, 4, 2},          // Strictly decreasing
            {1, 3, 5, 7, 9},           // Strictly increasing
            {5},                       // Single element
            {2, 1},                    // Two elements
            {3, 1, 2},                  // Valley in the middle
            {0, 1, 8, 7, 5, 3, 2}
        };

        for (int i = 0; i < testCases.length; i++) {
            int result = findValleyElement(testCases[i]);
            System.out.printf("Test case %d: Array: %s\n", 
                i + 1, 
                java.util.Arrays.toString(testCases[i]));
            if (result != -1) {
                System.out.printf("Valley element found at index %d: %d\n", 
                    result, testCases[i][result]);
            } else {
                System.out.println("No valley element found");
            }
            System.out.println();
        }
    }
}