package interview.problems.solved;

import java.util.*;

public class AccountsMergeUnionFind {

    /**
     * Merges accounts using the Union-Find method.
     *
     * @param accounts The list of accounts.
     * @return The merged accounts.
     */
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>(); // Maps email to account owner
        Map<String, String> parent = new HashMap<>(); // Union-Find parent map

        // Step 1: Initialize Union-Find structure
        for (List<String> account : accounts) {
            String name = account.get(0); // Account owner's name
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.put(email, name); // Map email to name
                parent.putIfAbsent(email, email); // Initialize each email as its own parent
                if (i > 1) {
                    union(parent, account.get(i - 1), email); // Union emails in the same account
                }
            }
        }

        // Step 2: Group emails by their root parent
        Map<String, List<String>> groups = new HashMap<>();
        for (String email : parent.keySet()) {
            String root = find(parent, email); // Find the root parent of the email
            groups.putIfAbsent(root, new ArrayList<>());
            groups.get(root).add(email);
        }

        // Step 3: Build the result
        List<List<String>> result = new ArrayList<>();
        for (String root : groups.keySet()) {
            List<String> emails = groups.get(root);
            Collections.sort(emails); // Sort emails lexicographically
            emails.add(0, emailToName.get(root)); // Prepend the account owner's name
            result.add(emails);
        }

        return result;
    }

    /**
     * Finds the root parent of a node in the Union-Find structure.
     *
     * @param parent The parent map.
     * @param email  The email to find the root for.
     * @return The root parent of the email.
     */
    private static String find(Map<String, String> parent, String email) {
        if (!parent.get(email).equals(email)) {
            parent.put(email, find(parent, parent.get(email))); // Path compression
        }
        return parent.get(email);
    }

    /**
     * Unions two nodes in the Union-Find structure.
     *
     * @param parent The parent map.
     * @param email1 The first email.
     * @param email2 The second email.
     */
    private static void union(Map<String, String> parent, String email1, String email2) {
        String root1 = find(parent, email1);
        String root2 = find(parent, email2);
        if (!root1.equals(root2)) {
            parent.put(root1, root2); // Union the two roots
        }
    }

    public static void main(String[] args) {
        // Example input
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("Mary", "mary@mail.com"));

        // Call the accountsMerge method
        List<List<String>> mergedAccounts = accountsMerge(accounts);

        // Print the result
        for (List<String> account : mergedAccounts) {
            System.out.println(account);
        }
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use Union-Find to group emails that belong to the same connected component.
 * - Each email is treated as a node, and edges connect emails that belong to the same account.
 * - After grouping emails, sort them lexicographically and prepend the account owner's name.
 *
 * Example Walkthrough:
 * Input:
 * [
 *     ["John", "johnsmith@mail.com", "john00@mail.com"],
 *     ["John", "johnnybravo@mail.com"],
 *     ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 *     ["Mary", "mary@mail.com"]
 * ]
 * - Step 1: Map emails to names and initialize Union-Find structure.
 * - Step 2: Union emails in the same account.
 * - Step 3: Group emails by their root parent.
 * - Step 4: Sort emails and prepend the account owner's name.
 *
 * Complexity:
 * - Time Complexity: O(N * α(N)), where N is the total number of emails and α is the inverse Ackermann function.
 * - Space Complexity: O(N), for storing the parent map and email-to-name map.
 * 
 * Intution:
 * - The Union-Find structure efficiently groups connected components (emails).
 * - Path compression optimizes the find operation, making it nearly constant time.
 * - Sorting emails ensures the final output is in lexicographical order.
 * 
 * 
 */