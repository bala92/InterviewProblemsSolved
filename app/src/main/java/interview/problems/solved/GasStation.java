package interview.problems.solved;

public class GasStation {

    /**
     * LeetCode 134: Gas Station
     *
     * There are n gas stations along a circular route, where the amount of gas at the i-th station is gas[i].
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the i-th station to its next.
     * Return the starting gas station’s index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
     * If there exists a solution, it is guaranteed to be unique.
     *
     * Approach:
     * 1. If the total gas is less than the total cost, it’s impossible to complete the circuit.
     * 2. Otherwise, there is exactly one solution.
     * 3. Greedy:
     *    - Track the total tank and the current tank as you iterate.
     *    - If at any point the current tank drops below zero, you cannot start from any station between the previous candidate and the current station.
     *    - Reset the starting station to the next index and reset the current tank.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0, currTank = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            totalTank += gas[i] - cost[i]; // Track total net gas
            currTank += gas[i] - cost[i];  // Track current net gas from the current start
            // If current tank is negative, cannot reach station i+1 from current start
            if (currTank < 0) {
                start = i + 1; // Set next station as new start
                currTank = 0;  // Reset current tank
            }
        }
        // If total gas is enough, return start index; otherwise, return -1
        return totalTank >= 0 ? start : -1;
    }

    /**
     * Main method to test canCompleteCircuit and min methods.
     */
    public static void main(String[] args) {
        GasStation gs = new GasStation();

        // Test canCompleteCircuit
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println("Start index (should be 3): " + gs.canCompleteCircuit(gas1, cost1));

        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println("Start index (should be -1): " + gs.canCompleteCircuit(gas2, cost2));
    }
}