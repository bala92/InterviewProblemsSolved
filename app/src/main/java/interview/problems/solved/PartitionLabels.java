package interview.problems.solved;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Description:
 * ---------------------
 * Partition a string into as many parts as possible so that each letter appears in at most one part.
 * Return a list of integers representing the size of these parts.
 *
 * Approach:
 * ---------
 * 1. Use an array to store the last occurrence of each character in the string.
 * 2. Traverse the string and keep track of the maximum index of the current partition.
 * 3. When the current index matches the maximum index, complete the partition and store its size.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the length of the string.
 * - Space Complexity: O(1), since the array for storing character indices has a fixed size of 26.
 */
public class PartitionLabels {

    /**
     * Function to partition the string into as many parts as possible.
     *
     * @param s The input string.
     * @return A list of integers representing the size of each partition.
     */
    public List<Integer> partitionLabels(String s) {
        // Step 1: Store the last occurrence of each character
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        // Step 2: Traverse the string and partition it
        List<Integer> result = new ArrayList<>();
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, lastIndex[s.charAt(i) - 'a']); // Update the end of the current partition

            if (i == end) { // If the current index matches the end of the partition
                result.add(end - start + 1); // Add the size of the partition
                start = i + 1; // Move the start to the next partition
            }
        }

        return result;
    }

    public static void main(String[] args) {
        PartitionLabels solution = new PartitionLabels();

        // Example 1
        String s1 = "ababcbacadefegdehijhklij";
        System.out.println("Partition sizes (Example 1): " + solution.partitionLabels(s1));
        // Output: [9, 7, 8]

        // Example 2
        String s2 = "eccbbbbdec";
        System.out.println("Partition sizes (Example 2): " + solution.partitionLabels(s2));
        // Output: [10]
    }
}