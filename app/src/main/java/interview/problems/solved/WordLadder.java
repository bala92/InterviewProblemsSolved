package interview.problems.solved;

import java.util.*;

/**
 * Problem Description:
 * ---------------------
 * Given two words, `beginWord` and `endWord`, and a dictionary of words `wordList`,
 * return the length of the shortest transformation sequence from `beginWord` to `endWord`.
 *
 * Constraints:
 * 1. Only one letter can be changed at a time.
 * 2. Each transformed word must exist in the word list.
 *
 * Approach:
 * ---------
 * 1. Use Breadth-First Search (BFS) to find the shortest path in the graph.
 * 2. Treat each word as a node in the graph.
 * 3. Two words are connected if they differ by exactly one letter.
 * 4. Use a queue to explore all possible transformations level by level.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(M^2 * N), where M is the length of each word and N is the number of words in the word list.
 * - Space Complexity: O(M * N), for storing the graph and the queue.
 */
public class WordLadder {

    /**
     * Function to find the length of the shortest transformation sequence.
     *
     * @param beginWord The starting word.
     * @param endWord   The target word.
     * @param wordList  The list of valid words.
     * @return The length of the shortest transformation sequence, or 0 if no sequence exists.
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert the word list to a set for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0; // If the endWord is not in the word list, no transformation is possible
        }

        // Initialize the BFS queue
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        // Track the number of transformation steps
        int steps = 1;

        // Perform BFS
        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Number of words at the current level
            for (int i = 0; i < levelSize; i++) {
                String currentWord = queue.poll();

                // Try all possible one-letter transformations
                for (int j = 0; j < currentWord.length(); j++) {
                    char[] wordChars = currentWord.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordChars[j] = c;
                        String transformedWord = new String(wordChars);

                        // If the transformed word is the endWord, return the steps
                        if (transformedWord.equals(endWord)) {
                            return steps + 1;
                        }

                        // If the transformed word is in the word set, add it to the queue
                        if (wordSet.contains(transformedWord)) {
                            queue.add(transformedWord);
                            wordSet.remove(transformedWord); // Remove to prevent revisiting
                        }
                    }
                }
            }
            steps++; // Increment the step count after processing the current level
        }

        return 0; // If no transformation sequence is found, return 0
    }

    /**
     * Time Complexity Explanation:
     *
     * Let M be the length of each word and N be the number of words in the word list.
     *
     * - For each word dequeued from the queue, we try all possible one-letter transformations.
     * - For each of the M positions in the word, we try 26 possible letters, so O(26 * M) = O(M) per word.
     * - In the worst case, we process every word in the word list, so O(N) words.
     * - For each transformation, checking and removing from the set is O(1).
     *
     * Therefore, the total time complexity is O(M^2 * N):
     * - For each word (O(N)), for each position (O(M)), for each letter (O(26)), and string creation (O(M)).
     * - The dominant term is O(M^2 * N).
     *
     * Space Complexity:
     * - O(M * N) for the word set and the queue.
     */
    public static void main(String[] args) {
        // Example 1
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println("Shortest transformation length: " + ladderLength(beginWord1, endWord1, wordList1)); // Output: 5

        // Example 2
        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        System.out.println("Shortest transformation length: " + ladderLength(beginWord2, endWord2, wordList2)); // Output: 0
    }
}