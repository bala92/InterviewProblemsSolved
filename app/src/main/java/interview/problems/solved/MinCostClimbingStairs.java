package interview.problems.solved;

import java.util.ArrayList;
import java.util.Arrays;

public class MinCostClimbingStairs {

    /**
     * Function to calculate the minimum cost to climb stairs using space optimization.
     *
     * @param cost The array representing the cost of each step.
     * @return The minimum cost to reach the top of the floor.
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        // Base cases
        int prev2 = 0; // Cost to reach two steps before
        int prev1 = 0; // Cost to reach one step before

        // Iterate through the steps
        for (int i = 0; i < n; i++) {
            int current = Math.min(prev1 , prev2) + cost[i];
            // Update the previous costs
            prev2 = prev1;
            prev1 = current;
        }

        return Math.min(prev1 , prev2); // Minimum cost to reach the top
    }

    public static void main(String[] args) {
        MinCostClimbingStairs solution = new MinCostClimbingStairs();

        // Example 1
        int[] cost1 = {10, 15, 20};
        System.out.println("Minimum cost (Example 1) input: " + new ArrayList<>(Arrays.asList(Arrays.stream(cost1).boxed().toArray(Integer[]::new))));
         // Output: 15
        System.out.println("Minimum cost (Example 1): " + solution.minCostClimbingStairs(cost1));
        // Output: 15

        // Example 2
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println("Minimum cost (Example 2): " + solution.minCostClimbingStairs(cost2));
        // Output: 6
    }
}