package interview.problems.solved;

import java.util.PriorityQueue;

/**
 * LeetCode 378: Kth Smallest Element in a Sorted Matrix
 *
 * Problem:
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order,
 * return the kth smallest element in the matrix.
 *
 * Example:
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 *
 * Approach:
 * - Use a min-heap (priority queue) to efficiently get the next smallest element.
 * - Start by adding the first element of each row to the heap.
 * - Pop the smallest element from the heap and add its right neighbor (if any) to the heap.
 * - Repeat k times; the kth popped element is the answer.
 *
 * Time Complexity: O(k log n), where n is the number of rows (or columns).
 * Space Complexity: O(n), for the heap.
 */
public class KthSmallestElementSortedMatrix {
    static class HeapNode implements Comparable<HeapNode> {
        int val, row, col;
        HeapNode(int val, int row, int col) {
            this.val = val; this.row = row; this.col = col;
        }
        public int compareTo(HeapNode other) {
            return Integer.compare(this.val, other.val);
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>();
        // Add the first element of each row
        for (int i = 0; i < n; i++) {
            minHeap.offer(new HeapNode(matrix[i][0], i, 0));
        }
        int count = 0, result = 0;
        while (count < k) {
            HeapNode node = minHeap.poll();
            result = node.val;
            if (node.col + 1 < n) {
                minHeap.offer(new HeapNode(matrix[node.row][node.col + 1], node.row, node.col + 1));
            }
            count++;
        }
        return result;
    }

    // Example usage
    public static void main(String[] args) {
        KthSmallestElementSortedMatrix solver = new KthSmallestElementSortedMatrix();
        int[][] matrix = {
            {1, 5, 9},
            {10, 11, 13},
            {12, 13, 15}
        };
        int k = 8;
        System.out.println("Kth smallest element: " + solver.kthSmallest(matrix, k)); // Output: 13
    }
}
