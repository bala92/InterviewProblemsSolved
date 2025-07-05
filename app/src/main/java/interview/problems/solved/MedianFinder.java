package interview.problems.solved;

import java.util.PriorityQueue;

public class MedianFinder {

    private PriorityQueue<Integer> maxHeap; // Max-Heap for the smaller half
    private PriorityQueue<Integer> minHeap; // Min-Heap for the larger half

    /** Initialize the MedianFinder. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a); // Max-Heap
        minHeap = new PriorityQueue<>(); // Min-Heap
    }

    /**
     * Adds a number to the data stream.
     *
     * @param num The number to add.
     */
    public void addNum(int num) {
        // Add to maxHeap first
        maxHeap.offer(num);

        // Balance: Ensure all elements in maxHeap are <= elements in minHeap
        minHeap.offer(maxHeap.poll());

        // Balance: Ensure maxHeap has at most one more element than minHeap
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * Finds the median of the current data stream.
     *
     * @return The median.
     */
    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek(); // Odd number of elements
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0; // Even number of elements
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();

        // Example usage
        medianFinder.addNum(1);
        System.out.println("Median: " + medianFinder.findMedian()); // Output: 1.0

        medianFinder.addNum(2);
        System.out.println("Median: " + medianFinder.findMedian()); // Output: 1.5

        medianFinder.addNum(3);
        System.out.println("Median: " + medianFinder.findMedian()); // Output: 2.0
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use two heaps:
 *   - Max-Heap (maxHeap) stores the smaller half of the numbers.
 *   - Min-Heap (minHeap) stores the larger half of the numbers.
 * - Add numbers to the heaps and balance them to ensure:
 *   - maxHeap.size() >= minHeap.size().
 *   - All elements in maxHeap are <= all elements in minHeap.
 * - To find the median:
 *   - If maxHeap has more elements, the median is maxHeap.peek().
 *   - If both heaps have the same size, the median is the average of maxHeap.peek() and minHeap.peek().
 *
 * Example Walkthrough:
 * Input: [1, 2, 3]
 * - Step 1: Add 1
 *   - maxHeap = [1], minHeap = []
 *   - Median = 1.0
 * - Step 2: Add 2
 *   - maxHeap = [1], minHeap = [2]
 *   - Median = (1 + 2) / 2 = 1.5
 * - Step 3: Add 3
 *   - maxHeap = [2, 1], minHeap = [3]
 *   - Median = 2.0
 *
 * Complexity:
 * - Time Complexity:
 *   - addNum: O(log n), where n is the number of elements in the heaps.
 *   - findMedian: O(1).
 * - Space Complexity: O(n), for storing the elements in the heaps.
 */