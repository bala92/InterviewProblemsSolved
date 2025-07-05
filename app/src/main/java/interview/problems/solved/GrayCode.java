package interview.problems.solved;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 89: Gray Code
 *
 * Problem:
 * Given a non-negative integer n, generate a sequence of Gray codes for n bits.
 * A Gray code sequence is a sequence where two successive values differ in only one bit.
 *
 * Approach (Recursive Reflection):
 * - The Gray code for n bits can be built from the Gray code for n-1 bits.
 * - For n = 0, the sequence is [0].
 * - For n > 0:
 *   1. Generate the Gray code for n-1 bits (call this list S).
 *   2. Reflect S (reverse it) and prepend a 1 to the highest bit of each value in the reflected list.
 *   3. Concatenate the original S and the reflected, bit-prepended S.
 * - This ensures that each step only changes one bit.
 *
 * Example (n = 2):
 *   - Gray code for 1 bit: [0, 1]
 *   - Reflect: [1, 0]
 *   - Prepend 1 to reflected: [1+2=3, 0+2=2] => [3, 2]
 *   - Combine: [0, 1, 3, 2]
 *
 * Time Complexity: O(2^n)
 * Space Complexity: O(2^n)
 */

public class GrayCode {

    /**
     * Function to generate the Gray Code sequence for n bits using recursion.
     *
     * @param n The number of bits.
     * @return A list of integers representing the Gray Code sequence.
     */
    public List<Integer> grayCode(int n) {
        // Base case: Gray code for n = 0 is [0]
        if (n == 0) {
            List<Integer> baseCase = new ArrayList<>();
            baseCase.add(0);
            return baseCase;
        }

        // Recursive step: Get the Gray Code sequence for n-1 bits
        List<Integer> smallerGrayCode = grayCode(n - 1);

        // Reflect the smaller Gray Code sequence
        List<Integer> result = new ArrayList<>(smallerGrayCode);
        int addBit = 1 << (n - 1); // Prepend 1 to the reflected sequence

        for (int i = smallerGrayCode.size() - 1; i >= 0; i--) {
            result.add(smallerGrayCode.get(i) + addBit);
        }

        return result;
    }

    public static void main(String[] args) {
        GrayCode solution = new GrayCode();

        // Example 1
        int n1 = 2;
        System.out.println("Gray Code (n = 2): " + solution.grayCode(n1));
        // Output: [0, 1, 3, 2]

        // Example 2
        int n2 = 3;
        System.out.println("Gray Code (n = 3): " + solution.grayCode(n2));
        // Output: [0, 1, 3, 2, 6, 7, 5, 4]
    }
}