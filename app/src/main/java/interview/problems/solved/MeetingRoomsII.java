package interview.problems.solved;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LeetCode 253: Meeting Rooms II
 *
 * Problem Description:
 * Given an array of meeting time intervals consisting of start and end times [[start1,end1],[start2,end2],...],
 * find the minimum number of conference rooms required.
 *
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 *
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 *
 * Constraints:
 * - 1 <= intervals.length <= 10^4
 * - intervals[i].length == 2
 * - 0 <= starti < endi <= 10^6
 *
 * Approach:
 * - Sort the intervals by start time.
 * - Use a min-heap (priority queue) to keep track of the end times of meetings currently using a room.
 * - For each meeting, if the room due to free earliest is free before the meeting starts, reuse it (pop from heap).
 *   Otherwise, allocate a new room (add to heap).
 * - The size of the heap at the end is the minimum number of rooms required.
 */

public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Min-heap to track the earliest end time of ongoing meetings
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] interval : intervals) {
            // If the room is free (the earliest end time <= current start), reuse it
            if (!minHeap.isEmpty() && minHeap.peek() <= interval[0]) {
                minHeap.poll();
            }
            // Allocate a new room (add current meeting's end time)
            minHeap.offer(interval[1]);
        }

        // The size of the heap is the minimum number of rooms required
        return minHeap.size();
    }

    // Example usage
    public static void main(String[] args) {
        MeetingRoomsII solution = new MeetingRoomsII();

        int[][] intervals1 = {{0,30},{5,10},{15,20}, {21,32}, {31,35}};
        System.out.println("Minimum meeting rooms (Example 1): " + solution.minMeetingRooms(intervals1)); // 2

        int[][] intervals2 = {{7,10},{2,4}};
        System.out.println("Minimum meeting rooms (Example 2): " + solution.minMeetingRooms(intervals2)); // 1
    }
}