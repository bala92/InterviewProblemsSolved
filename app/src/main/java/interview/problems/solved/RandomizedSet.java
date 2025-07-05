package interview.problems.solved;

import java.util.*;

public class RandomizedSet {

    private final Map<Integer, Integer> valueToIndex; // Maps value to its index in the list
    private final List<Integer> values; // Stores the values
    private final Random random; // Random number generator

    /**
     * Initializes the RandomizedSet object.
     */
    public RandomizedSet() {
        valueToIndex = new HashMap<>();
        values = new ArrayList<>();
        random = new Random();
    }

    /**
     * Inserts a value into the set. Returns true if the value was successfully inserted.
     *
     * @param val The value to insert.
     * @return True if the value was not already present, false otherwise.
     */
    public boolean insert(int val) {
        if (valueToIndex.containsKey(val)) {
            return false; // Value already exists
        }

        // Add the value to the list and map its index
        values.add(val);
        valueToIndex.put(val, values.size() - 1);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the value was successfully removed.
     *
     * @param val The value to remove.
     * @return True if the value was present, false otherwise.
     */
    public boolean remove(int val) {
        if (!valueToIndex.containsKey(val)) {
            return false; // Value does not exist
        }

        // Get the index of the value to remove
        int indexToRemove = valueToIndex.get(val);
        int lastValue = values.get(values.size() - 1);

        // Swap the value to remove with the last value in the list
        values.set(indexToRemove, lastValue);
        valueToIndex.put(lastValue, indexToRemove);

        // Remove the last value from the list and map
        values.remove(values.size() - 1);
        valueToIndex.remove(val);

        return true;
    }

    /**
     * Returns a random element from the set.
     *
     * @return A random element.
     */
    public int getRandom() {
        int randomIndex = random.nextInt(values.size());
        return values.get(randomIndex);
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();

        // Example usage
        System.out.println(randomizedSet.insert(1)); // Output: true
        System.out.println(randomizedSet.insert(2)); // Output: true
        System.out.println(randomizedSet.insert(1)); // Output: false
        System.out.println(randomizedSet.getRandom()); // Output: 1 or 2
        System.out.println(randomizedSet.remove(1)); // Output: true
        System.out.println(randomizedSet.getRandom()); // Output: 2
    }
}

/**
 * Explanation:
 *
 * Data Structures:
 * - HashMap (valueToIndex): Maps each value to its index in the ArrayList.
 * - ArrayList (values): Stores the actual values for O(1) random access.
 *
 * Operations:
 * 1. Insert:
 *    - Add the value to the ArrayList.
 *    - Map the value to its index in the HashMap.
 *    - Time Complexity: O(1).
 *
 * 2. Remove:
 *    - Swap the value to remove with the last value in the ArrayList.
 *    - Update the index of the swapped value in the HashMap.
 *    - Remove the last value from the ArrayList and HashMap.
 *    - Time Complexity: O(1).
 *
 * 3. GetRandom:
 *    - Generate a random index and return the value at that index from the ArrayList.
 *    - Time Complexity: O(1).
 *
 * Example Walkthrough:
 * - Insert 1: values = [1], valueToIndex = {1: 0}.
 * - Insert 2: values = [1, 2], valueToIndex = {1: 0, 2: 1}.
 * - Remove 1: values = [2], valueToIndex = {2: 0}.
 * - GetRandom: Randomly returns 2.
 *
 * Complexity:
 * - Time Complexity: O(1) for all operations.
 * - Space Complexity: O(n), where n is the number of elements in the set.
 */