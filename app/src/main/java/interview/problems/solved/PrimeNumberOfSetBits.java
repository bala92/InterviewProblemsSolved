package interview.problems.solved;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem Description:
 * ---------------------
 * Count how many numbers in the range [L, R] have a binary representation
 * with a prime number of set bits.
 *
 * Approach:
 * ---------
 * 1. Use a helper function to count the number of set bits in a number.
 * 2. Precompute all prime numbers up to 32 (since the maximum number of set bits is 32).
 * 3. Iterate through the range [L, R] and count numbers with a prime number of set bits.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O((R - L + 1) * log(max(L, R))), where log(max(L, R)) is for counting set bits.
 * - Space Complexity: O(1), for the prime set.
 */
public class PrimeNumberOfSetBits {

    /**
     * Function to count numbers with a prime number of set bits in the range [L, R].
     *
     * @param L The start of the range.
     * @param R The end of the range.
     * @return The count of numbers with a prime number of set bits.
     */
    public int countPrimeSetBits(int L, int R) {
        // Precompute prime numbers up to 32
        Set<Integer> primes = new HashSet<>();
        primes.add(2);
        primes.add(3);
        primes.add(5);
        primes.add(7);
        primes.add(11);
        primes.add(13);
        primes.add(17);
        primes.add(19);
        primes.add(23);
        primes.add(29);
        primes.add(31);

        int count = 0;

        // Iterate through the range [L, R]
        for (int i = L; i <= R; i++) {
            int setBits = Integer.bitCount(i); // Count the number of set bits
            if (primes.contains(setBits)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        PrimeNumberOfSetBits solution = new PrimeNumberOfSetBits();

        // Example 1
        int L1 = 6, R1 = 10;
        System.out.println("Count (Example 1): " + solution.countPrimeSetBits(L1, R1));
        // Output: 4 (6 -> 2 set bits, 7 -> 3 set bits, 9 -> 2 set bits, 10 -> 2 set bits)

        // Example 2
        int L2 = 10, R2 = 15;
        System.out.println("Count (Example 2): " + solution.countPrimeSetBits(L2, R2));
        // Output: 5 (10 -> 2 set bits, 11 -> 3 set bits, 12 -> 2 set bits, 13 -> 3 set bits, 14 -> 3 set bits)
    }
}