/**
 * Problem: Sum of Left Leaves
 * 
 * Description:
 * Given the root of a binary tree, return the sum of all left leaves.
 * A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.
 * 
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 *       3
 *      / \
 *     9  20
 *        / \
 *       15  7
 * Output: 24
 * Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
 * 
 * Example 2:
 * Input: root = [1]
 * Output: 0
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 1000].
 * - -1000 <= Node.val <= 1000
 * 
 * Approach:
 * 1. Use DFS (Depth-First Search) to traverse the binary tree
 * 2. For each node, check if it has a left child
 * 3. If the left child is a leaf (no children), add its value to the sum
 * 4. Recursively process the left and right subtrees
 * 5. Return the total sum of all left leaves
 * 
 * Key Insight:
 * - A left leaf is a node that is both a leaf (no children) AND the left child of its parent
 * - We need to pass information about whether the current node is a left child to our recursive calls
 * 
 * Complexity:
 * - Time Complexity: O(N), where N is the number of nodes in the tree
 * - Space Complexity: O(H), where H is the height of the tree (due to recursion stack)
 * 
 * Author: Balaji
 * Date: May 1, 2025
 */
package interview.problems.solved;

public class SumOfLeftLeaves {

    /**
     * Main method to calculate sum of left leaves
     * @param root The root of the binary tree
     * @return Sum of all left leaves
     */
    public static int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }
    
    /**
     * DFS helper method to traverse the tree and calculate sum of left leaves
     * @param node Current node being processed
     * @param isLeftChild Whether the current node is a left child of its parent
     * @return Sum of left leaves in the subtree rooted at this node
     */
    private static int dfs(TreeNode node, boolean isLeftChild) {
        // Base case: if node is null, return 0
        if (node == null) {
            return 0;
        }
        
        // Check if current node is a left leaf
        // A left leaf is a leaf (no children) that is the left child of its parent
        if (isLeftChild && node.left == null && node.right == null) {
            return node.val;
        }
        
        // Recursively process left and right subtrees
        // For left child, pass isLeftChild = true
        // For right child, pass isLeftChild = false
        int leftSum = dfs(node.left, true);
        int rightSum = dfs(node.right, false);
        
        return leftSum + rightSum;
    }
    
    /**
     * Alternative approach using iterative BFS
     * @param root The root of the binary tree
     * @return Sum of all left leaves
     */
    public static int sumOfLeftLeavesIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int sum = 0;
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        java.util.Queue<Boolean> isLeftQueue = new java.util.LinkedList<>();
        
        queue.offer(root);
        isLeftQueue.offer(false); // Root is not a left child
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            boolean isLeftChild = isLeftQueue.poll();
            
            // Check if current node is a left leaf
            if (isLeftChild && node.left == null && node.right == null) {
                sum += node.val;
            }
            
            // Add left child to queue
            if (node.left != null) {
                queue.offer(node.left);
                isLeftQueue.offer(true);
            }
            
            // Add right child to queue
            if (node.right != null) {
                queue.offer(node.right);
                isLeftQueue.offer(false);
            }
        }
        
        return sum;
    }
    
    /**
     * Alternative recursive DFS solution - checking left child directly
     * This approach checks if the left child of each node is a leaf
     * @param root The root of the binary tree
     * @return Sum of all left leaves
     */
    public static int sumOfLeftLeavesAlternative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int sum = 0;
        
        // Check if left child is a leaf
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
        
        // Recursively process left and right subtrees
        sum += sumOfLeftLeavesAlternative(root.left);
        sum += sumOfLeftLeavesAlternative(root.right);
        
        return sum;
    }
    
    /**
     * Utility method to create a binary tree from an array
     * @param values Array representation of the tree (level-order)
     * @return Root of the created binary tree
     */
    public static TreeNode createTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }
        
        TreeNode root = new TreeNode(values[0]);
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();
            
            // Add left child
            if (i < values.length && values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.offer(node.left);
            }
            i++;
            
            // Add right child
            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.offer(node.right);
            }
            i++;
        }
        
        return root;
    }
    
    /**
     * Utility method to print the tree in level-order (for debugging)
     */
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Example from problem description
        System.out.println("Test Case 1:");
        Integer[] values1 = {3, 9, 20, null, null, 15, 7};
        TreeNode root1 = createTree(values1);
        System.out.println("Tree structure:");
        printTree(root1);
        System.out.println("Sum of left leaves (recursive): " + sumOfLeftLeaves(root1));
        System.out.println("Sum of left leaves (iterative): " + sumOfLeftLeavesIterative(root1));
        System.out.println("Sum of left leaves (alternative): " + sumOfLeftLeavesAlternative(root1));
        System.out.println("Expected: 24");
        System.out.println();
        
        // Test Case 2: Single node
        System.out.println("Test Case 2:");
        Integer[] values2 = {1};
        TreeNode root2 = createTree(values2);
        System.out.println("Tree structure:");
        printTree(root2);
        System.out.println("Sum of left leaves (recursive): " + sumOfLeftLeaves(root2));
        System.out.println("Sum of left leaves (iterative): " + sumOfLeftLeavesIterative(root2));
        System.out.println("Sum of left leaves (alternative): " + sumOfLeftLeavesAlternative(root2));
        System.out.println("Expected: 0");
        System.out.println();
        
        // Test Case 3: More complex tree
        System.out.println("Test Case 3:");
        Integer[] values3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        TreeNode root3 = createTree(values3);
        System.out.println("Tree structure:");
        printTree(root3);
        System.out.println("Sum of left leaves (recursive): " + sumOfLeftLeaves(root3));
        System.out.println("Sum of left leaves (iterative): " + sumOfLeftLeavesIterative(root3));
        System.out.println("Sum of left leaves (alternative): " + sumOfLeftLeavesAlternative(root3));
        System.out.println("Expected: 24 (4 + 8 + 12)");
        System.out.println();
        
        // Test Case 4: Tree with no left leaves
        System.out.println("Test Case 4:");
        Integer[] values4 = {1, null, 2, null, null, null, 3};
        TreeNode root4 = createTree(values4);
        System.out.println("Tree structure:");
        printTree(root4);
        System.out.println("Sum of left leaves (recursive): " + sumOfLeftLeaves(root4));
        System.out.println("Sum of left leaves (iterative): " + sumOfLeftLeavesIterative(root4));
        System.out.println("Sum of left leaves (alternative): " + sumOfLeftLeavesAlternative(root4));
        System.out.println("Expected: 0");
    }
}

/*
 * Detailed Explanation:
 * 
 * The key insight for this problem is understanding what constitutes a "left leaf":
 * 1. It must be a leaf node (no children)
 * 2. It must be the left child of its parent
 * 
 * Algorithm Breakdown:
 * 
 * 1. **Base Case**: If the current node is null, return 0
 * 
 * 2. **Left Leaf Check**: 
 *    - Check if the current node is a left child (isLeftChild = true)
 *    - Check if it's a leaf (both left and right children are null)
 *    - If both conditions are met, add the node's value to the sum
 * 
 * 3. **Recursive Calls**:
 *    - For the left child: pass isLeftChild = true
 *    - For the right child: pass isLeftChild = false
 *    - Sum up the results from both recursive calls
 * 
 * Time Complexity Analysis:
 * - We visit each node exactly once: O(N)
 * - At each node, we perform constant time operations: O(1)
 * - Total time complexity: O(N)
 * 
 * Space Complexity Analysis:
 * - Recursion stack space: O(H) where H is the height of the tree
 * - In worst case (skewed tree): O(N)
 * - In best case (balanced tree): O(log N)
 * 
 * Edge Cases Handled:
 * - Empty tree (null root)
 * - Single node tree
 * - Tree with no left leaves
 * - Tree with multiple left leaves
 * 
 * Alternative Approaches:
 * 1. **Iterative BFS**: Use a queue to process nodes level by level
 * 2. **Iterative DFS**: Use a stack for depth-first traversal
 * 3. **Morris Traversal**: In-order traversal without using extra space
 * 
 * The recursive approach is the most intuitive and commonly used solution
 * for this problem, as it naturally follows the tree structure and makes
 * the logic clear and easy to understand.
 */ 