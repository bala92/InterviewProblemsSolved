/**
 * Problem: Accounts Merge
 * 
 * Description:
 * Given a list of accounts where each account is represented as a list of strings, 
 * the first element is the name of the account owner, and the rest are email addresses.
 * Accounts can belong to the same person if they share at least one email address.
 * The goal is to merge such accounts and return the merged accounts in the format:
 * [name, email1, email2, ...], where emails are sorted lexicographically.
 * 
 * Example Input:
 * [
 *     ["John", "johnsmith@mail.com", "john00@mail.com"],
 *     ["John", "johnnybravo@mail.com"],
 *     ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 *     ["Mary", "mary@mail.com"]
 * ]
 * 
 * Example Output:
 * [
 *     ["John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"],
 *     ["John", "johnnybravo@mail.com"],
 *     ["Mary", "mary@mail.com"]
 * ]
 * 
 * Approach:
 * 1. Build a graph where each email is a node, and edges connect emails belonging to the same account.
 * 2. Use DFS to find connected components in the graph, which represent merged accounts.
 * 3. Sort the emails in each connected component lexicographically and prepend the account owner's name.
 * 
 * Complexity:
 * - Time Complexity: O(N * logN), where N is the total number of emails. Sorting dominates the complexity.
 * - Space Complexity: O(N), for storing the graph and visited set.
 * 
 * Author: Balaji
 * Date: May 1, 2025
 */
package interview.problems.solved;

import java.util.*;

public class AccountsMerge {

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Map to store email -> account owner (name)
        Map<String, String> emailToName = new HashMap<>();
        // Graph adjacency list to connect emails
        Map<String, List<String>> graph = new HashMap<>();

        // Step 1: Build the graph
        for (List<String> account : accounts) {
            String name = account.get(0); // First element is the account owner's name
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.put(email, name); // Map email to name
                graph.putIfAbsent(email, new ArrayList<>());
                if (i > 1) {
                    // Connect the current email with the previous email
                    graph.get(account.get(i - 1)).add(email);
                    graph.get(email).add(account.get(i - 1));

                }
            }
        }

        // Step 2: Perform DFS/BFS to find connected components (emails belonging to the
        // same person)
        Set<String> visited = new HashSet<>();
        List<List<String>> result = new ArrayList<>();

        for (String email : graph.keySet()) {
            if (!visited.contains(email)) {
                List<String> connectedEmails = new ArrayList<>();
                bfs(email, graph, visited, connectedEmails); // BFS implementation
                // dfs(email, graph, visited, connectedEmails);
                Collections.sort(connectedEmails); // Sort emails lexicographically
                connectedEmails.add(0, emailToName.get(email)); // Add the name at the beginning
                result.add(connectedEmails);
            }
        }

        return result;
    }

    // Helper method for DFS traversal
    @SuppressWarnings("unused")
    private static void dfs(String email, Map<String, List<String>> graph, Set<String> visited,
            List<String> connectedEmails) {
        visited.add(email);
        connectedEmails.add(email);
        for (String neighbor : graph.get(email)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, connectedEmails);
            }
        }
    }

    // Helper method for BFS traversal
    private static void bfs(String email, Map<String, List<String>> graph, Set<String> visited,
            List<String> connectedEmails) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(email);
        visited.add(email);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            connectedEmails.add(current);
            for (String neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Example input
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "q@mail.com", "john00@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("John", "x@mail.com", "y@mail.com", "john00@mail.com"),

                Arrays.asList("Mary", "mary@mail.com"));

        // Call the accountsMerge method
        List<List<String>> mergedAccounts = accountsMerge(accounts);

        // Print the result
        for (List<String> account : mergedAccounts) {
            System.out.println(account);
        }
    }
}

/*
 * 
 * Time Complexity:
 * O(N + E)
 * 
 * Where:
 * 
 * N is the total number of emails (nodes in the graph).
 * E is the total number of edges in the graph (connections between emails).
 * Breakdown:
 * Building the Graph:
 * 
 * Each email is treated as a node, and edges are added between emails belonging
 * to the same account.
 * This step involves iterating through all accounts and emails, which takes O(N
 * + E) time because we process each email and each connection.
 * Finding Connected Components (BFS):
 * 
 * BFS is used to traverse the graph and find connected components.
 * Each node (email) and edge is visited once during the BFS traversal, which
 * takes O(N + E) time.
 * Sorting Emails:
 * 
 * Since the emails are already sorted, this step is skipped, saving O(N * K log
 * K) time (where K is the average number of emails per account).
 * Overall Complexity:
 * The overall time complexity is O(N + E) because the graph traversal dominates
 * the computation.
 * Space Complexity:
 * O(N + E) for storing the graph (adjacency list).
 * O(N) for the visited set and BFS queue.
 * Conclusion:
 * If the emails are already sorted, the time complexity of the Accounts Merge
 * problem using BFS is O(N + E), where N is the number of emails and E is the
 * number of connections between emails. This is efficient and avoids the
 * additional sorting step.
 */