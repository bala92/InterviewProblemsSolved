# Interview Problems Solved

Welcome to the **Interview Problems Solved** repository! This project is a comprehensive collection of Java solutions to a wide variety of coding interview problems, including LeetCode, system design, and algorithmic challenges. Each solution is self-contained, well-documented, and often includes multiple approaches, complexity analysis, and example usage. The goal is to provide a one-stop resource for interview preparation, algorithm mastery, and reference for both beginners and experienced engineers.

---

## 📁 Project Structure

```
/Interview Problems Solved
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── interview/problems/solved/   # All Java source files, one per problem
│   │   │   └── resources/
│   │   │       └── SystemDesign/                # System design notes and diagrams
│   │   └── test/
│   │       └── java/interview/problems/solved/  # (If present) Unit tests for the solutions
│   └── build.gradle.kts, ...                    # Gradle build files
├── Meta_Facebook_Coding_Problems.md             # Reference and notes for common interview questions
├── README.md                                    # This file
└── ...
```

---

## 🧠 Topics Covered
- **Arrays & Strings**: Sliding window, two pointers, prefix/suffix, subarrays, string manipulation, etc.
- **Binary Trees & Graphs**: Traversals (BFS, DFS, iterative/recursive), LCA, serialization, graph cycles, topological sort, etc.
- **Dynamic Programming**: 1D/2D DP, memoization, tabulation, knapsack, LIS, DP on trees, etc.
- **Backtracking & Recursion**: Permutations, combinations, subsets, N-Queens, Sudoku, expression add operators, etc.
- **Greedy Algorithms**: Interval scheduling, coin change, activity selection, etc.
- **Stack, Queue, and Heap Problems**: Monotonic stack, min/max heap, queue simulation, etc.
- **System Design**: High-level design, scalability, distributed systems (see `resources/SystemDesign`)
- **Bit Manipulation**: Gray code, single number, bitwise tricks
- **Math & Number Theory**: Prime numbers, combinatorics, GCD/LCM, etc.
- **Others**: Union-Find, Trie, Sliding Window Maximum, etc.

---

## 📝 Example Problems & Approaches

| Problem File                        | Description / Approach                                                                 |
|-------------------------------------|---------------------------------------------------------------------------------------|
| `AlienDictionary.java`              | Topological sort (Kahn's algorithm) to determine character order in an alien language. |
| `PathSum3.java`                     | Prefix sum + DFS to count all paths in a binary tree that sum to a target value.       |
| `GrayCode.java`                     | Recursive reflection to generate n-bit Gray code sequence.                             |
| `TrappingRainWater.java`            | Two-pointer technique to calculate trapped water.                                      |
| `PermutationsWithDuplicates.java`   | Backtracking with duplicate skipping for unique permutations.                          |
| `ExpressionAddOperators.java`       | Backtracking to add operators to a string of digits to reach a target value.           |
| `ShortestPathInBinaryMatrix.java`   | BFS to find the shortest path in a 2D grid (0 = open, 1 = blocked).                   |
| `ValidateBinarySearchTree.java`     | Recursion with min/max bounds to validate BST property.                                |
| `BinaryTreeToString.java`           | Preorder traversal to serialize a binary tree as a string.                             |
| `WordLadder.java`                   | BFS for shortest transformation sequence in word ladder problem.                       |
| `RedundantConnection.java`          | Union-Find and DFS approaches to detect cycles in a graph.                             |
| `NestedListWeightSum.java`          | BFS to compute weighted sum of nested lists.                                           |
| ...                                 | Many more!                                                                            |

---

## 🚀 How to Use

1. **Clone the Repository**
   ```sh
   git clone <repo-url>
   cd "Interview Problems Solved"
   ```
2. **Open in Your Java IDE**
   - Recommended: IntelliJ IDEA, Eclipse, or Visual Studio Code with Java extensions.
3. **Explore the Solutions**
   - Each file in `app/src/main/java/interview/problems/solved/` is a standalone Java class for a specific problem.
   - Each file contains a `main` method for direct execution and testing.
   - Many files include multiple approaches (recursive, iterative, optimized, brute-force) and detailed comments.
4. **Run a Specific Problem**
   - Open the desired Java file and run its `main` method.
   - Example:
     ```sh
     cd app/src/main/java/interview/problems/solved/
     javac AlienDictionary.java
     java interview.problems.solved.AlienDictionary
     ```
5. **Unit Tests** (if present)
   - Run tests from your IDE or with Gradle: `./gradlew test`

---

## 🛠️ Requirements
- Java 8 or higher
- Gradle (for building/running all at once, optional)

---

## 📚 Additional Resources
- **Meta_Facebook_Coding_Problems.md**: Curated list of common interview questions and patterns.
- **SystemDesign/**: Notes, diagrams, and explanations for system design interviews.
- **In-file Explanations**: Each Java file contains problem statements, approach, complexity, and example usage.

---

## 🤝 Contributing
Contributions are welcome! If you have a new problem, alternative approach, or improvement:
- Fork the repository
- Add your solution with clear documentation and example usage
- Submit a pull request

---

## 📄 License
This project is for educational and interview preparation purposes. Use freely for learning and practice.

---

## 🙏 Acknowledgements
- LeetCode, GeeksforGeeks, and other open platforms for problem inspiration
- The open-source community for sharing knowledge and best practices

---

## 💡 Tips for Interview Prep
- Practice a variety of problems: arrays, trees, graphs, DP, and system design
- Write code by hand and test edge cases
- Focus on explaining your thought process and trade-offs
- Review time and space complexity for each solution
- Use this repository as a reference and template for your own practice
