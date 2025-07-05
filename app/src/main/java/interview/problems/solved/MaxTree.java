package interview.problems.solved;

public class MaxTree {

    /**
     * Function to construct the maximum binary tree.
     *
     * @param nums The input array.
     * @return The root of the maximum binary tree.
     */
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    /**
     * Helper function to construct the maximum binary tree for a given range.
     *
     * @param nums The input array.
     * @param left The starting index of the range.
     * @param right The ending index of the range.
     * @return The root of the maximum binary tree for the range.
     */
    private static TreeNode construct(int[] nums, int left, int right) {
        if (left > right) {
            return null; // Base case: no elements in the range
        }

        // Find the index of the maximum element in the range
        int maxIndex = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Create the root node with the maximum element
        TreeNode root = new TreeNode(nums[maxIndex]);

        // Recursively construct the left and right subtrees
        root.left = construct(nums, left, maxIndex - 1);
        root.right = construct(nums, maxIndex + 1, right);

        return root;
    }

    /**
     * Helper function to print the tree in-order (for testing purposes).
     */
    public static void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        // Example input
        int[] nums = {3, 2, 1, 6, 0, 5};

        // Construct the maximum binary tree
        TreeNode root = constructMaximumBinaryTree(nums);

        // Print the tree in-order
        System.out.print("In-order traversal of the tree: ");
        printInOrder(root); // Output: 3 2 1 6 0 5
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **Recursive Construction**:
 *    - The `construct` function finds the maximum element in the given range of the array.
 *    - It creates a root node with the maximum element and recursively constructs the left and right subtrees.
 *
 * 2. **Base Case**:
 *    - If the range is invalid (`left > right`), return `null`.
 *
 * 3. **Finding the Maximum**:
 *    - Iterate through the range `[left, right]` to find the index of the maximum element.
 *
 * 4. **Tree Construction**:
 *    - Create a new `TreeNode` with the maximum element.
 *    - Recursively construct the left subtree using the elements to the left of the maximum element.
 *    - Recursively construct the right subtree using the elements to the right of the maximum element.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * nums = [3, 2, 1, 6, 0, 5]
 *
 * Steps:
 * 1. Find the maximum element (6) at index 3. Create the root node with value 6.
 * 2. Recursively construct the left subtree for the range [0, 2]:
 *    - Maximum element is 3 at index 0. Create a node with value 3.
 *    - Recursively construct the right subtree for the range [1, 2]:
 *      - Maximum element is 2 at index 1. Create a node with value 2.
 *      - Recursively construct the right subtree for the range [2, 2]:
 *        - Maximum element is 1 at index 2. Create a node with value 1.
 * 3. Recursively construct the right subtree for the range [4, 5]:
 *    - Maximum element is 5 at index 5. Create a node with value 5.
 *    - Recursively construct the left subtree for the range [4, 4]:
 *      - Maximum element is 0 at index 4. Create a node with value 0.
 *
 * Output:
 * In-order traversal: 3 2 1 6 0 5
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - Finding the maximum element in the array takes `O(n)` time.
 *    - For each recursive call, the range decreases, leading to a total time complexity of `O(n^2)` in the worst case (e.g., sorted array).
 *
 * 2. **Space Complexity**:
 *    - The recursion stack uses `O(h)` space, where `h` is the height of the tree.
 *    - In the worst case (skewed tree), `h = n`. In the best case (balanced tree), `h = log(n)`.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: nums = [3, 2, 1, 6, 0, 5]
 * Output: In-order traversal: 3 2 1 6 0 5
 *
 * Test Case 2:
 * Input: nums = [1, 2, 3, 4]
 * Output: In-order traversal: 1 2 3 4
 *
 * Test Case 3:
 * Input: nums = [4, 3, 2, 1]
 * Output: In-order traversal: 4 3 2 1
 */