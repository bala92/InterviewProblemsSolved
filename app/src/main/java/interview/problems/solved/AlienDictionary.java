package interview.problems.solved;

import java.util.*;

/**
 * Problem Description:
 * ---------------------
 * Given a sorted dictionary of an alien language, find the order of characters in the alien alphabet.
 *
 * Approach:
 * ---------
 * 1. Build a graph where each character is a node, and a directed edge (u, v) indicates that
 *    character u comes before character v in the alien language.
 * 2. Use Kahn's Algorithm (BFS-based topological sorting) to find the order of characters.
 * 3. If the graph contains a cycle, the dictionary is invalid.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(C + V + E), where C is the total number of characters in all words,
 *   V is the number of unique characters, and E is the number of edges in the graph.
 * - Space Complexity: O(V + E), for storing the graph and in-degree array.
 */
public class AlienDictionary {

    /**
     * Function to find the order of characters in the alien language.
     *
     * @param words The sorted dictionary of the alien language.
     * @param numChars The number of unique characters in the alien language.
     * @return The order of characters in the alien alphabet, or an empty string if invalid.
     */
    public static String findOrder(String[] words, int numChars) {
        // Step 1: Build the graph
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        // Initialize the graph and in-degree map
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new ArrayList<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        // Compare adjacent words to determine the order of characters
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int minLength = Math.min(word1.length(), word2.length());

            boolean foundOrder = false;
            for (int j = 0; j < minLength; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);

                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    inDegree.put(c2, inDegree.get(c2) + 1);
                    foundOrder = true;
                    break;
                }
            }

            // If no order is found and word2 is a prefix of word1, the dictionary is invalid
            if (!foundOrder && word1.length() > word2.length()) {
                return "";
            }
        }

        // Step 2: Perform topological sorting using Kahn's Algorithm
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.add(c);
            }
        }

        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            char current = queue.poll();
            order.append(current);

            for (char neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // If the order does not contain all characters, the graph contains a cycle
        if (order.length() != inDegree.size()) {
            return "";
        }

        return order.toString();
    }

    public static void main(String[] args) {
        // Example 1
        String[] words1 = {"baa", "abcd", "abca", "cab", "cad"};
        int numChars1 = 4; // 'a', 'b', 'c', 'd'
        System.out.println("Order of characters (Example 1): " + findOrder(words1, numChars1));
        // Output: "bdac" (or any valid topological order)

        // Example 2
        String[] words2 = {"caa", "aaa", "aab"};
        int numChars2 = 3; // 'a', 'b', 'c'
        System.out.println("Order of characters (Example 2): " + findOrder(words2, numChars2));
        // Output: "cab"

        // Example 3: Invalid dictionary
        String[] words3 = {"abc", "ab"};
        int numChars3 = 3; // 'a', 'b', 'c'
        System.out.println("Order of characters (Example 3): " + findOrder(words3, numChars3));
        // Output: ""
    }
}