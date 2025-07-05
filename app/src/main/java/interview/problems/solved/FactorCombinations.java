package interview.problems.solved;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {

    /**
     * Function to find all factor combinations of a given number.
     *
     * @param n The input number.
     * @return A list of all possible factor combinations.
     */
    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(n, 2, new ArrayList<>(), result);
        return result;
    }

    /**
     * Helper function to perform backtracking.
     *
     * @param n       The remaining number to factorize.
     * @param start   The starting factor to consider.
     * @param current The current combination of factors.
     * @param result  The list to store all valid combinations.
     */
    private static void backtrack(int n, int start, List<Integer> current, List<List<Integer>> result) {
        // Base case: if n becomes 1 and the current combination is not empty
        if (n == 1 && current.size() > 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Iterate through possible factors starting from 'start'
        for (int i = start; i <= Math.sqrt(n); i++) {
            if (n % i == 0 && n/i >= i) { // If 'i' is a factor of 'n'
                current.add(i); // Add the factor to the current combination
                current.add(n / i); // Add the corresponding factor
                result.add(new ArrayList<>(current)); // Add the current combination to the result
                current.remove(current.size() - 1); // Backtrack by removing the last factor
                backtrack(n / i, i, current, result); // Recurse with the reduced number
                current.remove(current.size() - 1); // Backtrack by removing the last factor
            }
        }

        // Include the remaining factor (if it's greater than 1 and not the original number)
        // if (n >= start) {
        //     current.add(n);
        //     result.add(new ArrayList<>(current));
        //     current.remove(current.size() - 1);
        // }
    }

    public static void main(String[] args) {
        // Example input
        int n = 100;

        // Call the getFactors function
        List<List<Integer>> result = getFactors(n);

        // Print the result
        System.out.println("Factor combinations of " + n + ": " + result);
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **Backtracking**:
 *    - The `backtrack` function explores all possible factor combinations by dividing `n` with factors starting from `2`.
 *    - It recursively reduces `n` and adds valid factors to the current combination.
 *    - When `n` becomes `1`, the current combination is added to the result list.
 *
 * 2. **Base Case**:
 *    - If `n == 1` and the current combination is not empty, add the combination to the result.
 *
 * 3. **Iterating Factors**:
 *    - Start iterating from `2` up to `sqrt(n)` to find valid factors.
 *    - For each valid factor, add it to the current combination and recurse with `n / factor`.
 *    - Backtrack by removing the last added factor.
 *
 * 4. **Including Remaining Factor**:
 *    - If `n` is greater than or equal to the starting factor, add it as a valid combination.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * n = 12
 *
 * Steps:
 * 1. Start with `n = 12`, `start = 2`, `current = []`.
 * 2. Factor `2` divides `12`:
 *    - Add `2` to `current`: `current = [2]`.
 *    - Recurse with `n = 6`.
 * 3. Factor `2` divides `6`:
 *    - Add `2` to `current`: `current = [2, 2]`.
 *    - Recurse with `n = 3`.
 * 4. Factor `3` divides `3`:
 *    - Add `3` to `current`: `current = [2, 2, 3]`.
 *    - Add `[2, 2, 3]` to the result.
 * 5. Backtrack and explore other combinations like `[2, 6]` and `[3, 4]`.
 *
 * Output:
 * Factor combinations of 12: [[2, 2, 3], [2, 6], [3, 4]]
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - The number of recursive calls depends on the number of factors and their combinations.
 *    - In the worst case, it explores all possible combinations of factors.
 *    - Approximate complexity: `O(2^sqrt(n))`.
 *
 * 2. **Space Complexity**:
 *    - The space used by the recursion stack is proportional to the depth of the recursion tree.
 *    - Space complexity: `O(log(n))` for the recursion stack.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: n = 12
 * Output: [[2, 2, 3], [2, 6], [3, 4]]
 *
 * Test Case 2:
 * Input: n = 16
 * Output: [[2, 2, 2, 2], [2, 8], [4, 4]]
 *
 * Test Case 3:
 * Input: n = 37 (prime number)
 * Output: []
 */