package interview.problems.solved;

public class SolveEquationLeetCode {

    /**
     * Function to solve a linear equation in the form of a string.
     *
     * @param equation The input equation as a string.
     * @return The solution for x as a string.
     */
    public String solveEquation(String equation) {
        // Split the equation into left-hand side (LHS) and right-hand side (RHS)
        String[] sides = equation.split("=");
        int[] left = parseSide(sides[0]);
        int[] right = parseSide(sides[1]);

        // Combine coefficients and constants from both sides
        int coefficient = left[0] - right[0]; // Coefficient of x
        int constant = right[1] - left[1];   // Constant term

        // Solve for x
        if (coefficient == 0) {
            if (constant == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        }

        // Calculate the value of x
        return "x=" + (constant / coefficient);
    }

    /**
     * Helper function to parse a side of the equation.
     *
     * @param side The side of the equation as a string.
     * @return An array where the first element is the coefficient of x and the second element is the constant term.
     */
    private int[] parseSide(String side) {
        int coefficient = 0; // Coefficient of x
        int constant = 0;    // Constant term
        int sign = 1;        // Current sign (+1 or -1)
        int i = 0;

        while (i < side.length()) {
            char c = side.charAt(i);

            if (c == '+') {
                sign = 1;
                i++;
            } else if (c == '-') {
                sign = -1;
                i++;
            } else {
                int num = 0;
                boolean isNumber = false;

                // Parse the number (if present)
                while (i < side.length() && Character.isDigit(side.charAt(i))) {
                    num = num * 10 + (side.charAt(i) - '0');
                    i++;
                    isNumber = true;
                }

                // If the term contains 'x'
                if (i < side.length() && side.charAt(i) == 'x') {
                    coefficient += sign * (isNumber ? num : 1); // Default coefficient is 1 if no number is present
                    i++;
                } else {
                    constant += sign * num; // Add the constant term
                }
            }
        }

        return new int[]{coefficient, constant};
    }

    public static void main(String[] args) {
        SolveEquationLeetCode solution = new SolveEquationLeetCode();

        // Example 1
        String equation1 = "x+5-3+x=6+x-2";
        System.out.println("Solution (Example 1): " + solution.solveEquation(equation1));
        // Output: "x=2"

        // Example 2
        String equation2 = "x=x";
        System.out.println("Solution (Example 2): " + solution.solveEquation(equation2));
        // Output: "Infinite solutions"

        // Example 3
        String equation3 = "2x=x";
        System.out.println("Solution (Example 3): " + solution.solveEquation(equation3));
        // Output: "x=0"

        // Example 4
        String equation4 = "2x+3x-6x=x+2";
        System.out.println("Solution (Example 4): " + solution.solveEquation(equation4));
        // Output: "x=-1"

        // Example 5
        String equation5 = "x=x+2";
        System.out.println("Solution (Example 5): " + solution.solveEquation(equation5));
        // Output: "No solution"
    }
}