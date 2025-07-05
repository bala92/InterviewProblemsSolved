package interview.problems.solved;

import java.util.*;

/**
 * Problem: Top K Frequent Words
 * 
 * Given an array of strings `words` and an integer `k`, return the `k` most frequent words.
 * The answer should be sorted by frequency from highest to lowest. If two words have the same
 * frequency, sort them alphabetically.
 * 
 * Example:
 * Input:
 * words = ["i", "love", "leetcode", "i", "love", "coding"]
 * k = 2
 * 
 * Output:
 * ["i", "love"]
 * 
 * Explanation:
 * - The word "i" appears 2 times, and the word "love" appears 2 times.
 * - Since "i" comes before "love" alphabetically, it is placed first.
 * - The word "leetcode" and "coding" appear only once, so they are not included in the top 2.
 * 
 * Approach:
 * 1. Use a `HashMap` to count the frequency of each word.
 * 2. Use a `PriorityQueue` (min-heap) to store the top `k` frequent words.
 *    - The heap is sorted by frequency, and for words with the same frequency, it is sorted alphabetically in reverse order.
 * 3. Add words to the heap. If the heap size exceeds `k`, remove the least frequent word.
 * 4. Extract words from the heap into a list and reverse the list to get the correct order.
 * 
 * Time Complexity: O(N log k), where N is the number of words and k is the size of the heap.
 * Space Complexity: O(N) for the frequency map and heap.
 */

public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        // Step 1: Count the frequency of each word
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Step 2: Use a priority queue (min-heap) to store the top k frequent words
        PriorityQueue<String> heap = new PriorityQueue<>((word1, word2) -> {
            int freq1 = frequencyMap.get(word1);
            int freq2 = frequencyMap.get(word2);
            if (freq1 == freq2) {
                return word2.compareTo(word1); // Sort alphabetically in reverse order
            }
            return freq1 - freq2; // Sort by frequency
        });

        // Step 3: Add words to the heap
        for (String word : frequencyMap.keySet()) {
            heap.offer(word);
            if (heap.size() > k) {
                heap.poll(); // Remove the least frequent word
            }
        }

        // Step 4: Extract words from the heap into a list (in reverse order)
        List<String> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            result.add(heap.poll());
        }
        Collections.reverse(result); // Reverse to get the correct order

        return result;
    }

    public static void main(String[] args) {
        TopKFrequentWords solution = new TopKFrequentWords();

        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;

        List<String> topKWords = solution.topKFrequent(words, k);
        System.out.println("Top " + k + " Frequent Words: " + topKWords);

        // Example Output: ["i", "love"]
    }
}