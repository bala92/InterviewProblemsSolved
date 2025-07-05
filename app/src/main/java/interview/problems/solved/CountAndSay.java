package interview.problems.solved;

public class CountAndSay {

    /**
     * Generates the nth term of the Count and Say sequence.
     *
     * @param n The term to generate.
     * @return The nth term of the Count and Say sequence.
     */
    public static String countAndSay(int n) {
        if (n <= 0) {
            return "";
        }

        // Start with the base case
        String result = "1";

        // Generate terms from 2 to n
        for (int i = 2; i <= n; i++) {
            result = generateNext(result);
        }

        return result;
    }

    /**
     * Generates the next term in the Count and Say sequence.
     *
     * @param current The current term.
     * @return The next term in the sequence.
     */
    private static String generateNext(String current) {
        StringBuilder next = new StringBuilder();
        int count = 1;

        // Iterate through the current term
        for (int i = 1; i < current.length(); i++) {
            if (current.charAt(i) == current.charAt(i - 1)) {
                count++; // Increment the count for consecutive digits
            } else {
                // Append the count and the digit
                next.append(count).append(current.charAt(i - 1));
                count = 1; // Reset the count
            }
        }

        // Append the last group
        next.append(count).append(current.charAt(current.length() - 1));

        return next.toString();
    }

    public static void main(String[] args) {
        // Example usage
        int n = 5;

        for (int i = 1; i <= n; i++) {
            System.out.println("Count and Say (" + i + "): " + countAndSay(i));
        }


        //System.out.println("Count and Say (" + n + "): " + countAndSay(n)); // Output: "111221"
    }
}