package interview.problems.solved;

import java.util.HashMap;

/**
 * Problem Description:
 * ---------------------
 * Design and implement a data structure for an LRU (Least Recently Used) Cache.
 * The cache should support the following operations in O(1) time:
 * 1. `get(key)`: Retrieve the value of the key if it exists in the cache, otherwise return -1.
 * 2. `put(key, value)`: Insert or update the value of the key. If the cache reaches its capacity,
 *    it should invalidate the least recently used item before inserting a new item.
 *
 * Approach:
 * ---------
 * To achieve O(1) time complexity for both `get` and `put` operations:
 * 1. Use a **HashMap** to store the key-value pairs and provide O(1) lookups.
 * 2. Use a **Doubly Linked List** to maintain the order of usage:
 *    - The most recently used node is at the head.
 *    - The least recently used node is at the tail.
 *    - Adding and removing nodes from the doubly linked list is O(1).
 *
 * Key Operations:
 * ---------------
 * - **`get(key)`**:
 *   - If the key exists in the cache, move the corresponding node to the head of the doubly linked list
 *     (to mark it as recently used) and return its value.
 *   - If the key does not exist, return -1.
 * - **`put(key, value)`**:
 *   - If the key exists, update its value and move the node to the head.
 *   - If the key does not exist:
 *     - If the cache is at capacity, remove the least recently used node (from the tail).
 *     - Insert the new key-value pair at the head of the doubly linked list.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(1) for both `get` and `put` operations.
 * - Space Complexity: O(capacity), where `capacity` is the maximum number of items the cache can hold.
 */
public class LRUCache {

    // Node class for the doubly linked list
    private class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity; // Maximum capacity of the cache
    private final HashMap<Integer, Node> map; // HashMap for O(1) lookups
    private final Node head, tail; // Dummy head and tail for the doubly linked list

    /**
     * Constructor to initialize the LRU Cache.
     *
     * @param capacity The maximum capacity of the cache.
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0); // Dummy head
        this.tail = new Node(0, 0); // Dummy tail
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Get the value of the key if it exists in the cache.
     *
     * @param key The key to retrieve.
     * @return The value of the key, or -1 if the key does not exist.
     */
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            moveToHead(node); // Move the accessed node to the head
            return node.value;
        }
        return -1; // Key not found
    }

    /**
     * Put a key-value pair into the cache.
     *
     * @param key   The key to insert or update.
     * @param value The value to associate with the key.
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value; // Update the value
            moveToHead(node); // Move the node to the head
        } else {
            if (map.size() == capacity) {
                removeTail(); // Remove the least recently used node
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode); // Add the new node to the head
        }
    }

    /**
     * Move a node to the head of the doubly linked list.
     *
     * @param node The node to move.
     */
    private void moveToHead(Node node) {
        removeNode(node); // Remove the node from its current position
        addToHead(node); // Add the node to the head
    }

    /**
     * Add a node to the head of the doubly linked list.
     *
     * @param node The node to add.
     */
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * Remove a node from the doubly linked list.
     *
     * @param node The node to remove.
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * Remove the least recently used node (from the tail).
     */
    private void removeTail() {
        Node lru = tail.prev; // The node before the dummy tail is the least recently used
        removeNode(lru);
        map.remove(lru.key); // Remove it from the map
    }

    public static void main(String[] args) {
        // Example usage
        LRUCache cache = new LRUCache(2); // Capacity = 2
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // Output: 1
        cache.put(3, 3); // Evicts key 2
        System.out.println(cache.get(2)); // Output: -1
        cache.put(4, 4); // Evicts key 1
        System.out.println(cache.get(1)); // Output: -1
        System.out.println(cache.get(3)); // Output: 3
        System.out.println(cache.get(4)); // Output: 4
    }
}