package interview.problems.solved;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharactersByFrequency {

    /**
     * Function to sort characters in a string by frequency.
     *
     * @param s The input string.
     * @return A string with characters sorted by frequency in descending order.
     */
    public String frequencySort(String s) {
        // Step 1: Count the frequency of each character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Use a max-heap to sort characters by frequency
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue()
        );
        maxHeap.addAll(frequencyMap.entrySet());

        // Step 3: Build the result string
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            char character = entry.getKey();
            int frequency = entry.getValue();
            for (int i = 0; i < frequency; i++) {
                result.append(character);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        SortCharactersByFrequency solution = new SortCharactersByFrequency();

        // Example 1
        String s1 = "tree";
        System.out.println("Sorted by frequency (Example 1): " + solution.frequencySort(s1));
        // Output: "eert" or "eetr"

        // Example 2
        String s2 = "cccaaa";
        System.out.println("Sorted by frequency (Example 2): " + solution.frequencySort(s2));
        // Output: "cccaaa" or "aaaccc"

        // Example 3
        String s3 = "Aabb";
        System.out.println("Sorted by frequency (Example 3): " + solution.frequencySort(s3));
        // Output: "bbAa" or "bbaA"
    }
}