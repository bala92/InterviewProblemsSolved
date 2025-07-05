package interview.problems.solved;

import java.util.*;

public class CourseSchedule {

    /**
     * Determines if all courses can be finished given the prerequisites.
     *
     * @param numCourses The total number of courses.
     * @param prerequisites The list of prerequisite pairs.
     * @return True if all courses can be finished, false otherwise.
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Build the graph and in-degree array
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisite = prereq[1];
            graph.get(prerequisite).add(course);
            inDegree[course]++;
        }

        // Step 2: Initialize the queue with courses having in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 3: Process the courses
        int processedCourses = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            processedCourses++;

            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 4: Check if all courses were processed
        return processedCourses == numCourses;
    }

    public static void main(String[] args) {
        // Example 1
        int numCourses1 = 4;
        int[][] prerequisites1 = {{1, 0}, {1,2}, {1,3}, {2,3}};
        System.out.println("Can finish courses (Example 1): " + canFinish(numCourses1, prerequisites1)); // Output: true

        // Example 2
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println("Can finish courses (Example 2): " + canFinish(numCourses2, prerequisites2)); // Output: false
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use Kahn's Algorithm (BFS) for Topological Sorting.
 * - Build the graph using an adjacency list and track the in-degree of each course.
 * - Add courses with in-degree 0 to a queue and process them.
 * - Reduce the in-degree of neighbors and add them to the queue if their in-degree becomes 0.
 * - If all courses are processed, return true. Otherwise, return false (cycle detected).
 *
 * Example Walkthrough:
 * Input: numCourses = 2, prerequisites = [[1, 0]]
 * - Step 1: Build the graph:
 *   - Graph: {0 -> [1], 1 -> []}
 *   - In-degree: [0, 1]
 * - Step 2: Initialize the queue:
 *   - Queue: [0]
 * - Step 3: Process the courses:
 *   - Process 0: Queue = [], In-degree = [0, 0], Add 1 to queue.
 *   - Process 1: Queue = [], In-degree = [0, 0].
 * - Step 4: All courses processed → Return true.
 *
 * Complexity:
 * - Time Complexity: O(V + E), where V is the number of courses and E is the number of prerequisites.
 * - Space Complexity: O(V + E), for the graph and queue.
 */