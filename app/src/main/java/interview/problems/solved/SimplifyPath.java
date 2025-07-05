package interview.problems.solved;

import java.util.*;

/**
 * Problem: Simplify Path
 * 
 * Description:
 * Given a string `path`, which is an absolute path (starting with a slash '/')
 * to a file or directory in a Unix-style file system,
 * convert it to the simplified canonical path.
 * 
 * In a Unix-style file system:
 * - A period '.' refers to the current directory.
 * - A double period '..' moves the directory up a level.
 * - Multiple consecutive slashes '//' are treated as a single slash '/'.
 * 
 * The canonical path should have the following format:
 * - The path starts with a single slash '/'.
 * - Any two directories are separated by a single slash '/'.
 * - The path does not end with a trailing '/'.
 * - The path only contains the directories on the path from the root directory
 * to the target file or directory (no period '.' or double period '..').
 * 
 * Example Input:
 * path = "/home/"
 * 
 * Example Output:
 * "/home"
 * 
 * Approach:
 * 1. Split the input path by '/' to get individual components.
 * 2. Use a stack to process the components:
 * - If the component is "..", pop from the stack (if the stack is not empty).
 * - If the component is ".", ignore it.
 * - Otherwise, push the component onto the stack.
 * 3. Join the components in the stack with '/' to form the simplified path.
 * 
 * Complexity:
 * - Time Complexity: O(N), where N is the length of the input path.
 * - Space Complexity: O(N), for the stack and split components.
 * 
 * Author: GitHub Copilot
 * Date: May 5, 2025
 */
public class SimplifyPath {

    public static String simplifyPath(String path) {
        // Split the path into components by "/"
        String[] components = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String component : components) {
            if (component.equals("..")) {
                // Go up one directory (pop from stack if not empty)
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!component.equals(".") && !component.isEmpty()) {
                // Push valid directory names onto the stack
                stack.push(component);
            }
        }

        // Build the simplified path from the stack
        StringBuilder simplifiedPath = new StringBuilder();
        for (String dir : stack) {
            simplifiedPath.append("/").append(dir);
        }

        // Return "/" if the stack is empty, otherwise return the simplified path
        return simplifiedPath.length() > 0 ? simplifiedPath.toString() : "/";
    }

    public static void main(String[] args) {
        // Example test cases
        System.out.println(simplifyPath("/home/")); // Output: "/home"
        System.out.println(simplifyPath("/../")); // Output: "/"
        System.out.println(simplifyPath("/home//foo/")); // Output: "/home/foo"
        System.out.println(simplifyPath("/a/./b/../../c/")); // Output: "/c"
    }
}