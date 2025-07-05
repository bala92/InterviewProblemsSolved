package interview.problems.solved;

/**
 * LeetCode 606: Construct String from Binary Tree
 *
 * Problem:
 * Given the root of a binary tree, construct a string consisting of parenthesis and integers from a binary tree with the preorder traversal way.
 * Omit all the empty parenthesis pairs that do not affect the one-to-one mapping between the string and the original binary tree.
 *
 * Example:
 * Input: root = [1,2,3,4]
 * Output: "1(2(4))(3)"
 *
 * Approach:
 * - Use preorder traversal (root, left, right).
 * - For each node:
 *   - Add the node's value.
 *   - If the left child is null but the right child exists, add empty parenthesis for the left child.
 *   - Recursively build the string for left and right children.
 *
 * Why do we need empty parentheses?
 * - To preserve the structure of the tree, so that the string can be uniquely mapped back to the original tree.
 * - If a node has a right child but no left child, we must include () for the left child.
 *
 * Time Complexity: O(n), where n is the number of nodes.
 * Space Complexity: O(h), where h is the height of the tree (recursion stack).
 */
public class BinaryTreeToString {

    /**
     * Function to convert a binary tree to a string representation.
     *
     * @param root The root of the binary tree.
     * @return The string representation of the binary tree.
     */
    public String tree2str(TreeNode root) {
        // Base case: If the node is null, return an empty string
        if (root == null) {
            return "";
        }

        // Convert the current node's value to a string
        String result = String.valueOf(root.val);

        // If the node has a left child, process the left subtree
        if (root.left != null) {
            result += "(" + tree2str(root.left) + ")";
        }

        // If the node has a right child but no left child, add empty parentheses for the left child
        if (root.right != null) {
            if (root.left == null) {
                result += "()";
            }
            result += "(" + tree2str(root.right) + ")";
        }

        return result;
    }

    public static void main(String[] args) {
        BinaryTreeToString solution = new BinaryTreeToString();

        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        System.out.println("Tree to String (Example 1): " + solution.tree2str(root1));
        // Output: "1(2(4))(3)"

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        System.out.println("Tree to String (Example 2): " + solution.tree2str(root2));
        // Output: "1(2()(4))(3)"
    }
}