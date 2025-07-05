package interview.problems.solved;

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverage {

    private final int size; // Maximum size of the window
    private final Queue<Integer> queue; // Queue to store the last k elements
    private double sum; // Running sum of the elements in the queue

    /**
     * Initializes the MovingAverage object with a window size.
     *
     * @param size The size of the moving window.
     */
    public MovingAverage(int size) {
        this.size = size;
        this.queue = new LinkedList<>();
        this.sum = 0.0;
    }

    /**
     * Adds a new value to the stream and returns the moving average.
     *
     * @param val The new value to add.
     * @return The moving average of the last k elements.
     */
    public double next(int val) {
        // Add the new value to the queue and update the sum
        queue.offer(val);
        sum += val;

        // If the queue size exceeds the window size, remove the oldest element
        if (queue.size() > size) {
            sum -= queue.poll();
        }

        // Return the moving average
        return sum / queue.size();
    }

    public static void main(String[] args) {
        // Example usage
        MovingAverage movingAverage = new MovingAverage(3);

        System.out.println("Next(1): " + movingAverage.next(1)); // Output: 1.0
        System.out.println("Next(10): " + movingAverage.next(10)); // Output: 5.5
        System.out.println("Next(3): " + movingAverage.next(3)); // Output: 4.66667
        System.out.println("Next(5): " + movingAverage.next(5)); // Output: 6.0
    }
}