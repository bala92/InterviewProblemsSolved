package interview.problems.solved;

import java.util.*;

/**
 * Problem Description:
 * ---------------------
 * Implement serialization and deserialization of a binary tree using iterative level-order traversal.
 *
 * Approach:
 * ---------
 * 1. Use a queue to perform level-order traversal for serialization.
 * 2. Represent null nodes with "null" and separate values with a delimiter (e.g., ",").
 * 3. Use a queue to reconstruct the tree during deserialization.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the number of nodes in the tree.
 * - Space Complexity: O(n), for storing the serialized string or the queue during deserialization.
 */
public class SerializeDeserializeBinaryTreeIterative {

    /**
     * Serializes a binary tree to a single string using level-order traversal.
     *
     * @param root The root of the binary tree.
     * @return The serialized string representation of the tree.
     */
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "null";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        // Remove trailing comma
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Deserializes a string to reconstruct the binary tree using level-order traversal.
     *
     * @param data The serialized string representation of the tree.
     * @return The root of the reconstructed binary tree.
     */
    public static TreeNode deserialize(String data) {
        if (data.equals("null")) {
            return null;
        }

        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();

            // Process left child
            if (!values[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(values[i]));
                queue.add(node.left);
            }
            i++;

            // Process right child
            if (i < values.length && !values[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(values[i]));
                queue.add(node.right);
            }
            i++;
        }

        return root;
    }

    /**
     * Helper function to perform in-order traversal of a binary tree.
     *
     * @param root The root of the binary tree.
     */
    private static void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.val + " ");
            inOrderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        // Example: Create a binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        DrawBinaryTreeWithSpacing.drawTree(root); // Visualize the tree

        
        // Serialize the tree
        String serialized = serialize(root);
        System.out.print("In-order Traversal of before Deserialized Tree: ");
        inOrderTraversal(root); // Output: 2 1 4 3 5
        System.out.println("\n Serialized Tree: " + serialized);

        // Deserialize the string back to a tree
        TreeNode deserializedRoot = deserialize(serialized);
        System.out.print("In-order Traversal of Deserialized Tree: ");
        inOrderTraversal(deserializedRoot); // Output: 2 1 4 3 5
    }
}