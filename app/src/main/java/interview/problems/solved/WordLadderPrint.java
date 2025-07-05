package interview.problems.solved;

import java.util.*;

/**
 * Problem Description:
 * ---------------------
 * Given two words, `beginWord` and `endWord`, and a dictionary of words `wordList`,
 * return the length of the shortest transformation sequence from `beginWord` to `endWord`,
 * and print the transformation sequence.
 *
 * Constraints:
 * 1. Only one letter can be changed at a time.
 * 2. Each transformed word must exist in the word list.
 *
 * Approach:
 * ---------
 * 1. Use Breadth-First Search (BFS) to find the shortest path in the graph.
 * 2. Track the predecessor of each word to reconstruct the transformation sequence.
 * 3. Two words are connected if they differ by exactly one letter.
 * 4. Use a queue to explore all possible transformations level by level.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(M^2 * N), where M is the length of each word and N is the number of words in the word list.
 * - Space Complexity: O(M * N), for storing the graph, queue, and predecessor map.
 */
public class WordLadderPrint {

    /**
     * Function to find the length of the shortest transformation sequence and print the transformation path.
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

        // Map to track the predecessor of each word
        Map<String, String> predecessor = new HashMap<>();
        predecessor.put(beginWord, null);

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

                        // If the transformed word is the endWord, print the path and return the steps
                        if (transformedWord.equals(endWord)) {
                            predecessor.put(transformedWord, currentWord);
                            printPath(predecessor, transformedWord);
                            return steps + 1;
                        }

                        // If the transformed word is in the word set, add it to the queue
                        if (wordSet.contains(transformedWord)) {
                            queue.add(transformedWord);
                            wordSet.remove(transformedWord); // Remove to prevent revisiting
                            predecessor.put(transformedWord, currentWord);
                        }
                    }
                }
            }
            steps++; // Increment the step count after processing the current level
        }

        return 0; // If no transformation sequence is found, return 0
    }

    /**
     * Helper function to print the transformation path.
     *
     * @param predecessor The map of each word to its predecessor.
     * @param endWord     The target word.
     */
    private static void printPath(Map<String, String> predecessor, String endWord) {
        List<String> path = new LinkedList<>();
        String currentWord = endWord;

        // Backtrack from the endWord to the beginWord
        while (currentWord != null) {
            path.add(0, currentWord);
            currentWord = predecessor.get(currentWord);
        }

        System.out.println("Transformation sequence: " + path);
    }

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