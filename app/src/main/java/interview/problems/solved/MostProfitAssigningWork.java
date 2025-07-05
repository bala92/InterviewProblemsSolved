package interview.problems.solved;

import java.util.Arrays;

/**
 * LeetCode 826: Most Profit Assigning Work
 *
 * Problem:
 * You have n jobs. Every job has a difficulty and a profit. The profit of a job is independent of the person who does the job.
 * If a worker is assigned a job with difficulty X, they earn the profit of that job if their ability is >= X, else they earn 0.
 * Each worker can be assigned at most one job, but one job can be assigned to many workers.
 * Return the maximum profit we can achieve by assigning jobs to workers.
 *
 * Example:
 * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * Output: 100
 * Explanation: Workers are assigned jobs with difficulty [4,4,6,6] and get profit [20,20,30,30] respectively.
 *
 * Approach:
 * - Pair up each job's difficulty and profit, then sort jobs by difficulty.
 * - Sort the worker array.
 * - For each worker, assign the most profitable job they can do (difficulty <= ability).
 * - Use a greedy approach: as you iterate through workers, keep track of the best profit so far for jobs they can do.
 *
 * Time Complexity: O(n log n + m log m), where n = number of jobs, m = number of workers (for sorting).
 * Space Complexity: O(n), for storing job pairs.
 */
public class MostProfitAssigningWork {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profit[i];
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]); // Sort jobs by difficulty
        Arrays.sort(worker); // Sort workers by ability
        int res = 0, best = 0, j = 0;
        for (int w : worker) {
            while (j < n && jobs[j][0] <= w) {
                best = Math.max(best, jobs[j][1]);
                j++;
            }
            res += best;
        }
        return res;
    }

    // Example usage
    public static void main(String[] args) {
        MostProfitAssigningWork solver = new MostProfitAssigningWork();
        int[] difficulty = {2, 4, 6, 8, 10};
        int[] profit = {10, 20, 30, 40, 50};
        int[] worker = {4, 5, 6, 7};
        System.out.println("Maximum profit: " + solver.maxProfitAssignment(difficulty, profit, worker)); // Output: 100
    }
}
