package interview.problems.solved;

import java.util.HashMap;
import java.util.Map;


public class CopyListWithRandomPointerHashMap {

    // Define the Node class as static to avoid scoping issues
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * Creates a deep copy of a linked list with random pointers using a HashMap.
     *
     * @param head The head of the original linked list.
     * @return The head of the deep copy of the linked list.
     */
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // Step 1: Create a mapping from original nodes to their clones
        Map<Node, Node> map = new HashMap<>();
        Node current = head;

        while (current != null) {
            map.put(current, new Node(current.val)); // Clone the node
            current = current.next;
        }

        // Step 2: Assign next and random pointers for the cloned nodes
        current = head;
        while (current != null) {
            Node clone = map.get(current);
            clone.next = map.get(current.next); // Set the next pointer
            clone.random = map.get(current.random); // Set the random pointer
            current = current.next;
        }

        // Return the head of the cloned list
        return map.get(head);
    }

    public static void main(String[] args) {
        // Example: Create a list with random pointers
        Node head = new Node(7);
        head.next = new Node(13);
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(1);

        head.random = null;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;

        // Call the copyRandomList method
        Node copiedList = copyRandomList(head);

        // Print the copied list
        Node current = copiedList;
        while (current != null) {
            System.out.print("Node value: " + current.val);
            if (current.random != null) {
                System.out.println(", Random points to: " + current.random.val);
            } else {
                System.out.println(", Random points to: null");
            }
            current = current.next;
        }
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use a HashMap to store the mapping between original nodes and their cloned nodes.
 * - First Pass:
 *   - Traverse the original list and create a clone of each node.
 *   - Store the mapping (original node -> cloned node) in the HashMap.
 * - Second Pass:
 *   - Traverse the original list again and set the `next` and `random` pointers for each cloned node using the HashMap.
 *
 * Example Walkthrough:
 * Input: Original list with random pointers
 * - Step 1: Create a mapping:
 *   - Original Node 7 -> Cloned Node 7
 *   - Original Node 13 -> Cloned Node 13
 *   - Original Node 11 -> Cloned Node 11
 *   - Original Node 10 -> Cloned Node 10
 *   - Original Node 1 -> Cloned Node 1
 * - Step 2: Assign `next` and `random` pointers:
 *   - Cloned Node 7: next -> Cloned Node 13, random -> null
 *   - Cloned Node 13: next -> Cloned Node 11, random -> Cloned Node 7
 *   - Cloned Node 11: next -> Cloned Node 10, random -> Cloned Node 1
 *   - Cloned Node 10: next -> Cloned Node 1, random -> Cloned Node 11
 *   - Cloned Node 1: next -> null, random -> Cloned Node 7
 *
 * Complexity:
 * - Time Complexity: O(n), where n is the number of nodes in the list.
 * - Space Complexity: O(n), for storing the mapping in the HashMap.
 */