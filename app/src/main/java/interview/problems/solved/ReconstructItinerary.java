package interview.problems.solved;

import java.util.*;

/**
 * LeetCode 332: Reconstruct Itinerary
 *
 * Problem:
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from "JFK".
 * The itinerary must use all the tickets exactly once and must be lexicographically smallest.
 *
 * Approach:
 * ---------
 * Use Hierholzer's algorithm for Eulerian path:
 * 1. Build a graph where each node's adjacency list is a min-heap (PriorityQueue) to ensure lexical order.
 * 2. Perform DFS from "JFK". When you reach a node with no outgoing edges, add it to the itinerary.
 * 3. Reverse the itinerary at the end.
 *
 * Time Complexity: O(E log E), where E is the number of tickets (due to heap operations).
 * Space Complexity: O(E)
 */
public class ReconstructItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }
        LinkedList<String> itinerary = new LinkedList<>();
        dfs("JFK", graph, itinerary);
        return itinerary;
    }

    private void dfs(String airport, Map<String, PriorityQueue<String>> graph, LinkedList<String> itinerary) {
        PriorityQueue<String> dests = graph.get(airport);
        while (dests != null && !dests.isEmpty()) {
            dfs(dests.poll(), graph, itinerary);
        }
        itinerary.addFirst(airport);
    }

    // Example usage
    public static void main(String[] args) {
        ReconstructItinerary solution = new ReconstructItinerary();
        List<List<String>> tickets = Arrays.asList(
            Arrays.asList("MUC", "LHR"),
            Arrays.asList("JFK", "MUC"),
            Arrays.asList("SFO", "SJC"),
            Arrays.asList("LHR", "SFO")
        );
        System.out.println("Itinerary: " + solution.findItinerary(tickets));
        // Output: [JFK, MUC, LHR, SFO, SJC]
    }
}
