package interview.problems.solved;

import java.util.*;

public class CourseScheduleII {

    /**
     * Finds the order of courses to finish all courses.
     *
     * @param numCourses The total number of courses.
     * @param prerequisites The list of prerequisite pairs.
     * @return The order of courses to finish all courses, or an empty array if impossible.
     */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
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
        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 4: Check if all courses were processed
        if (order.size() == numCourses) {
            return order.stream().mapToInt(i -> i).toArray();
        } else {
            return new int[0]; // Return an empty array if a cycle exists
        }
    }

    public static int[] findOrderWithoutIndegree(int numCourses, int[][] prerequisites) {
        // Step 1: Build the graph and in-degree array
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < numCourses; i++) {
            graph.putIfAbsent(i, new HashSet<>());
        }

        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisite = prereq[1];
            graph.get(course).add(prerequisite);
        }

        // Step 2: Initialize the queue with courses having 0 dependencies
        Queue<Integer> queue = new LinkedList<>();
        for (int course : graph.keySet()) {
            if (graph.get(course).isEmpty()) {
                queue.offer(course);
                visited.add(course); // Mark as visited
            }
        }

        // Step 3: Process the courses
        Set<Integer> order = new HashSet<>();

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int course : graph.keySet()) {
                graph.get(course).remove(current); // Remove the dependency
                if (graph.get(course).isEmpty() && !visited.contains(course)) {
                    queue.offer(course); // If no dependencies left, add to queue
                    visited.add(course); // Mark as visited
                }
            }
        }

        // Step 4: Check if all courses were processed
        if (order.size() == numCourses) {
            return order.stream().mapToInt(i -> i).toArray();
        } else {
            return new int[0]; // Return an empty array if a cycle exists
        }
    }

    public static void main(String[] args) {
        // Example 1
        int numCourses1 = 4;
        int[][] prerequisites1 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println("Course order (Example 1): " + Arrays.toString(findOrder(numCourses1, prerequisites1))); // Output: [0, 1, 2, 3] or [0, 2, 1, 3]

        // Example 2
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println("Course order (Example 2): " + Arrays.toString(findOrder(numCourses2, prerequisites2))); // Output: []

        // Example 3
        System.out.println("Course order (Example 1 findOrderWithoutIndegree): " + Arrays.toString(findOrderWithoutIndegree(numCourses1, prerequisites1))); // Output: [0, 1, 2, 3] or [0, 2, 1, 3]
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
 * - If all courses are processed, return the order. Otherwise, return an empty array (cycle detected).
 *
 * Example Walkthrough:
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * - Step 1: Build the graph:
 *   - Graph: {0 -> [1, 2], 1 -> [3], 2 -> [3], 3 -> []}
 *   - In-degree: [0, 1, 1, 2]
 * - Step 2: Initialize the queue:
 *   - Queue: [0]
 * - Step 3: Process the courses:
 *   - Process 0: Queue = [], In-degree = [0, 0, 0, 2], Add 1, 2 to queue.
 *   - Process 1: Queue = [2], In-degree = [0, 0, 0, 1], Add 3 to queue.
 *   - Process 2: Queue = [3], In-degree = [0, 0, 0, 0].
 *   - Process 3: Queue = [], In-degree = [0, 0, 0, 0].
 * - Step 4: All courses processed → Return [0, 1, 2, 3].
 *
 * Complexity:
 * - Time Complexity: O(V + E), where V is the number of courses and E is the number of prerequisites.
 * - Space Complexity: O(V + E), for the graph and queue.
 */