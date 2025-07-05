package interview.problems.solved;

/**
 * Problem Description:
 * ---------------------
 * Given two strings representing two complex numbers, return a string representing their product.
 * The input strings are in the form "a+bi", where a and b are integers, and i is the imaginary unit.
 *
 * Approach:
 * ---------
 * 1. Parse the real and imaginary parts of both complex numbers.
 * 2. Use the formula for complex number multiplication:
 *    (a + bi) * (c + di) = (ac - bd) + (ad + bc)i
 * 3. Construct the result string in the form "x+yi".
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(1), since parsing and arithmetic operations are constant time.
 * - Space Complexity: O(1), as no additional data structures are used.
 */
public class ComplexNumberMultiplication {

    /**
     * Function to multiply two complex numbers.
     *
     * @param num1 The first complex number as a string.
     * @param num2 The second complex number as a string.
     * @return The product of the two complex numbers as a string.
     */
    public String complexNumberMultiply(String num1, String num2) {
        // Parse the real and imaginary parts of the first complex number
        int[] complex1 = parseComplexNumber(num1);
        int a = complex1[0]; // Real part of num1
        int b = complex1[1]; // Imaginary part of num1

        // Parse the real and imaginary parts of the second complex number
        int[] complex2 = parseComplexNumber(num2);
        int c = complex2[0]; // Real part of num2
        int d = complex2[1]; // Imaginary part of num2

        // Calculate the real and imaginary parts of the product
        int realPart = a * c - b * d;
        int imaginaryPart = a * d + b * c;

        // Construct the result string
        return realPart + " + (" + imaginaryPart + "i)";
    }

    /**
     * Helper function to parse a complex number string into its real and imaginary parts.
     *
     * @param num The complex number as a string in the form "a+bi".
     * @return An array where the first element is the real part and the second element is the imaginary part.
     */
    private int[] parseComplexNumber(String num) {
        // Split the string into the real and imaginary parts
        String[] parts = num.split("\\+");
        int realPart = Integer.parseInt(parts[0]); // Parse the real part
        int imaginaryPart = Integer.parseInt(parts[1].replace("i", "")); // Parse the imaginary part
        return new int[]{realPart, imaginaryPart};
    }

    public static void main(String[] args) {
        ComplexNumberMultiplication solution = new ComplexNumberMultiplication();

        // Example 1
        String num1 = "1+1i";
        String num2 = "1+1i";
        System.out.println("Product (Example 1): " + solution.complexNumberMultiply(num1, num2));
        // Output: "0+2i"

        // Example 2
        String num1_2 = "1+-1i";
        String num2_2 = "1+-1i";
        System.out.println("Product (Example 2): " + solution.complexNumberMultiply(num1_2, num2_2));
        // Output: "0+-2i"
    }
}