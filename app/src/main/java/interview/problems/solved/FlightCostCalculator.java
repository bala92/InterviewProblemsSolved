package interview.problems.solved;

public class FlightCostCalculator {
    //Meta

    /**
     * Calculates the minimum round-trip flight cost.
     *
     * @param depart    Array of departure flight costs.
     * @param returnArr Array of return flight costs.
     * @return The minimum round-trip cost, or -1 if no valid round trip exists.
     */
    public int minFlightCost(int[] depart, int[] returnArr) {
        // Edge case: If either array is empty
        if (depart.length == 0 || returnArr.length == 0) {
            return -1;
        }

        // Initialize minimums
        int minDepart = depart[0];
        int minCost = Integer.MAX_VALUE;
        int i = 0;

        // Traverse the return array
        for (int j = 1; j < returnArr.length; j++) {
            // Update the minimum departure cost so far
            if (i < depart.length) {
                minDepart = Math.min(minDepart, depart[i]);
                i++;  // Move to the next departure flight
            }

            // Calculate the round-trip cost and update the minimum cost
            minCost = Math.min(minCost, minDepart + returnArr[j]);
        }

        // If no valid round trip was found, return -1
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    public static void main(String[] args) {
        FlightCostCalculator calculator = new FlightCostCalculator();

        // Example usage
        int[] depart = {100, 200, 300 };
        int[] returnArr = {50, 150, 250};

        int result = calculator.minFlightCost(depart, returnArr);
        System.out.println("Minimum Flight Cost: " + result); // Example output
    }
}