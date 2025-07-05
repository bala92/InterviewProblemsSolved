package interview.problems.solved;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem Description:
 * ---------------------
 * Given the root of a binary tree, find the most frequent subtree sums.
 * A subtree sum is the sum of all the node values in a subtree, including the root of that subtree.
 * Return all the subtree sums that occur most frequently. If there is a tie, return all the sums with the highest frequency.
 *
 * Approach:
 * ---------
 * 1. Use postorder traversal to calculate the sum of each subtree.
 * 2. Store the frequency of each subtree sum in a HashMap.
 * 3. Find the maximum frequency and collect all subtree sums with that frequency.
 * 4. Return the result as an array of integers.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the number of nodes in the tree.
 * - Space Complexity: O(h + k), where h is the height of the tree (recursion stack) and k is the number of unique subtree sums.
 */
public class MostFrequentSubtreeSum {

    /**
     * Function to find the most frequent subtree sums.
     *
     * @param root The root of the binary tree.
     * @return An array of the most frequent subtree sums.
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        // Map to store the frequency of each subtree sum
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Step 1: Calculate subtree sums and populate the frequency map
        calculateSubtreeSums(root, frequencyMap);

        // Step 2: Find the maximum frequency
        int maxFrequency = 0;
        for (int freq : frequencyMap.values()) {
            maxFrequency = Math.max(maxFrequency, freq);
        }

        // Step 3: Collect all subtree sums with the maximum frequency
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                result.add(entry.getKey());
            }
        }

        // Step 4: Convert the result list to an array
        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Helper function to calculate subtree sums using postorder traversal.
     *
     * @param node The current node.
     * @param frequencyMap The map to store the frequency of each subtree sum.
     * @return The subtree sum of the current node.
     */
    private int calculateSubtreeSums(TreeNode node, Map<Integer, Integer> frequencyMap) {
        // Base case: If the node is null, return 0
        if (node == null) {
            return 0;
        }

        // Recursively calculate the sum of the left and right subtrees
        int leftSum = calculateSubtreeSums(node.left, frequencyMap);
        int rightSum = calculateSubtreeSums(node.right, frequencyMap);

        // Calculate the subtree sum for the current node
        int subtreeSum = leftSum + rightSum + node.val;

        // Update the frequency map with the subtree sum
        frequencyMap.put(subtreeSum, frequencyMap.getOrDefault(subtreeSum, 0) + 1);

        // Return the subtree sum
        return subtreeSum;
    }

    public static void main(String[] args) {
        MostFrequentSubtreeSum solution = new MostFrequentSubtreeSum();

        // Example 1
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(-3);
        System.out.println("Most frequent subtree sums (Example 1): ");
        printArray(solution.findFrequentTreeSum(root1));
        // Output: [2, -3, 4]

        // Example 2
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(-5);
        System.out.println("Most frequent subtree sums (Example 2): ");
        printArray(solution.findFrequentTreeSum(root2));
        // Output: [2]
    }

    /**
     * Helper function to print an array.
     *
     * @param arr The array to print.
     */
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

    // Using shared TreeNode class
