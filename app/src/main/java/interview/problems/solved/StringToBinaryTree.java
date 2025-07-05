package interview.problems.solved;

public class StringToBinaryTree {

    /**
     * Function to convert a string representation of a binary tree to a binary tree.
     *
     * @param s The string representation of the binary tree.
     * @return The root of the reconstructed binary tree.
     */
    public TreeNode str2tree(String s) {
        return buildTree(s, new int[]{0});
    }

    /**
     * Helper function to recursively build the binary tree.
     *
     * @param s The string representation of the binary tree.
     * @param index The current index pointer (wrapped in an array to allow modification).
     * @return The root of the current subtree.
     */
    private TreeNode buildTree(String s, int[] index) {
        // Base case: If the index is out of bounds, return null
        if (index[0] >= s.length()) {
            return null;
        }

        // Parse the root value (handle negative numbers as well)
        int start = index[0];
        while (index[0] < s.length() && (Character.isDigit(s.charAt(index[0])) || s.charAt(index[0]) == '-')) {
            index[0]++;
        }

        // If the index is at the end of the string, return null
        if (start == index[0]) {
            return null;
        }

        int rootVal = Integer.parseInt(s.substring(start, index[0]));
        TreeNode root = new TreeNode(rootVal);

        // If the next character is '(', parse the left subtree
        if (index[0] < s.length() && s.charAt(index[0]) == '(') {
            index[0]++; // Move past '('
            root.left = buildTree(s, index);
            index[0]++; // Move past ')'
        }

        // If the next character is '(', parse the right subtree
        if (index[0] < s.length() && s.charAt(index[0]) == '(') {
            index[0]++; // Move past '('
            root.right = buildTree(s, index);
            index[0]++; // Move past ')'
        }

        return root;
    }

    /**
     * Helper function to print the binary tree in preorder traversal.
     *
     * @param root The root of the binary tree.
     */
    public void printPreorder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    public static void main(String[] args) {
        StringToBinaryTree solution = new StringToBinaryTree();

        // Example 1
        String s1 = "1(2(4))(3)";
        TreeNode root1 = solution.str2tree(s1);
        System.out.print("Preorder traversal (Example 1): ");
        solution.printPreorder(root1);
        // Output: 1 2 4 3

        // Example 2
        String s2 = "1(2()(4))(3)";
        TreeNode root2 = solution.str2tree(s2);
        System.out.print("\nPreorder traversal (Example 2): ");
        solution.printPreorder(root2);
        // Output: 1 2 4 3
    }
}