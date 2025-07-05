package interview.problems.solved;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Problem: 1094 - Car Pooling
 * 
 * Description:
 * You are driving a vehicle that can hold a limited number of passengers. The
 * task is to determine if it is possible to pick up and drop off all passengers
 * for all the given trips.
 * 
 * Each trip is represented as an array `[numPassengers, from, to]`:
 * - `numPassengers` is the number of passengers to pick up.
 * - `from` is the starting location of the trip.
 * - `to` is the ending location of the trip.
 * 
 * The vehicle has a maximum capacity, and you need to ensure that the number of
 * passengers in the vehicle does not exceed this capacity at any point.
 * 
 * Example Input:
 * trips = [[2, 1, 5], [3, 3, 7]]
 * capacity = 4
 * 
 * At location 1, 2 passengers are picked up.
 * At location 3, 3 more passengers are picked up (total = 5).
 * At location 5, 2 passengers are dropped off (total = 3).
 * At location 7, 3 passengers are dropped off (total = 0).
 * The capacity is exceeded at location 3 (5 > 4), so the output is false.
 * 
 * Example Output:
 * false
 * 
 * Approach:
 * 1. Use a **difference array** to track the number of passengers picked up and
 * dropped off at each location.
 * 2. Iterate through the trips and update the difference array:
 * - Add `numPassengers` at the `from` location.
 * - Subtract `numPassengers` at the `to` location.
 * 3. Compute the prefix sum of the difference array to track the number of
 * passengers in the vehicle at each location.
 * 4. If the number of passengers exceeds the capacity at any point, return
 * `false`. Otherwise, return `true`.
 * 
 * Complexity:
 * - Time Complexity: O(N + M), where N is the number of trips and M is the
 * maximum location value.
 * - Space Complexity: O(M), for the difference array.
 * 
 * Author: GitHub Copilot
 * Date: May 5, 2025
 */
public class CarPooling {

        /**
     * Determines if it is possible to complete all trips without exceeding capacity using a priority queue.
     *
     * @param trips    The list of trips, where each trip is [numPassengers, from, to].
     * @param capacity The maximum capacity of the car.
     * @return True if all trips can be completed without exceeding capacity, otherwise false.
     */
    public static boolean carPoolingPriorityQueue(int[][] trips, int capacity) {
        // Step 1: Sort trips by start location
        Arrays.sort(trips, (a, b) -> Integer.compare(a[1], b[1]));

        // Step 2: Use a priority queue to track ongoing trips
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        int currentPassengers = 0;

        for (int[] trip : trips) {
            int numPassengers = trip[0];
            int from = trip[1];
            //int to = trip[2];

            // Step 3: Remove trips that have ended
            while (!pq.isEmpty() && pq.peek()[2] <= from) {
                currentPassengers -= pq.poll()[0];
            }

            // Step 4: Add the current trip
            pq.offer(trip);
            currentPassengers += numPassengers;

            // Step 5: Check if capacity is exceeded
            if (currentPassengers > capacity) {
                return false;
            }
        }

        return true; // All trips can be completed within capacity
    }

    public static boolean carPooling(int[][] trips, int capacity) {
        // Find the maximum location to determine the size of the difference array
        int maxLocation = 0;
        for (int[] trip : trips) {
            maxLocation = Math.max(maxLocation, trip[2]);
        }

        // Create a difference array to track passenger changes at each location
        int[] diff = new int[maxLocation + 1];

        // Update the difference array based on the trips
        for (int[] trip : trips) {
            int numPassengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            diff[from] += numPassengers; // Pick up passengers
            diff[to] -= numPassengers; // Drop off passengers
        }

        // Compute the prefix sum to track the number of passengers at each location
        int currentPassengers = 0;
        for (int passengers : diff) {
            currentPassengers += passengers;
            if (currentPassengers > capacity) {
                return false; // Capacity exceeded
            }
        }

        return true; // All trips can be completed within capacity
    }

    public static void main(String[] args) {
        // Example test cases
        int[][] trips1 = { { 2, 1, 5 }, { 3, 3, 7 } };
        int capacity1 = 4;
        System.out.println(carPooling(trips1, capacity1)); // Output: false
        System.out.println("Priority Queue Solution: " + carPoolingPriorityQueue(trips1, capacity1)); // Output: false

        int[][] trips2 = { { 2, 1, 5 }, { 3, 3, 7 } };
        int capacity2 = 5;
        System.out.println(carPooling(trips2, capacity2)); // Output: true
        System.out.println("Priority Queue Solution: " + carPoolingPriorityQueue(trips2, capacity2)); // Output: true


        int[][] trips3 = { { 3, 2, 7 }, { 3, 7, 9 }, { 8, 3, 9 } };
        int capacity3 = 11;
        System.out.println(carPooling(trips3, capacity3)); // Output: true
        System.out.println("Priority Queue Solution: " + carPoolingPriorityQueue(trips3, capacity3)); // Output: true

    }
}