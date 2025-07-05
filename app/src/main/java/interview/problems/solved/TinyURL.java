package interview.problems.solved;

import java.util.HashMap;
import java.util.Random;

public class TinyURL {

    // Base URL for the short URL
    private static final String BASE_URL = "http://tinyurl.com/";
    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    // Maps to store the long URL and short URL mappings
    private HashMap<String, String> shortToLongMap = new HashMap<>();
    private HashMap<String, String> longToShortMap = new HashMap<>();
    private Random random = new Random();

    /**
     * Encodes a long URL to a shortened URL.
     *
     * @param longUrl The original long URL.
     * @return The shortened URL.
     */
    public String encode(String longUrl) {
        // If the long URL is already encoded, return the existing short URL
        if (longToShortMap.containsKey(longUrl)) {
            return BASE_URL + longToShortMap.get(longUrl);
        }

        // Generate a unique short URL
        String shortUrl;
        do {
            shortUrl = generateShortUrl();
        } while (shortToLongMap.containsKey(shortUrl)); // Ensure uniqueness

        // Store the mappings
        shortToLongMap.put(shortUrl, longUrl);
        longToShortMap.put(longUrl, shortUrl);

        return BASE_URL + shortUrl;
    }

    /**
     * Decodes a shortened URL to its original long URL.
     *
     * @param shortUrl The shortened URL.
     * @return The original long URL.
     */
    public String decode(String shortUrl) {
        // Extract the short key from the full short URL
        String shortKey = shortUrl.replace(BASE_URL, "");
        return shortToLongMap.getOrDefault(shortKey, null);
    }

    /**
     * Generates a random short URL key of fixed length.
     *
     * @return A random short URL key.
     */
    private String generateShortUrl() {
        StringBuilder sb = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            sb.append(CHAR_SET.charAt(random.nextInt(CHAR_SET.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TinyURL tinyURL = new TinyURL();

        // Example usage
        String longUrl = "https://www.example.com/some/very/long/url";
        String shortUrl = tinyURL.encode(longUrl);
        System.out.println("Short URL: " + shortUrl);

        String decodedUrl = tinyURL.decode(shortUrl);
        System.out.println("Decoded URL: " + decodedUrl);
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **Data Structures**:
 *    - `shortToLongMap`: Maps the short URL key to the original long URL.
 *    - `longToShortMap`: Maps the long URL to the short URL key (to avoid duplicate encodings).
 *
 * 2. **Encoding**:
 *    - If the long URL is already encoded, return the existing short URL.
 *    - Otherwise, generate a unique short URL key using the `generateShortUrl()` method.
 *    - Store the mappings in both `shortToLongMap` and `longToShortMap`.
 *
 * 3. **Decoding**:
 *    - Extract the short key from the full short URL by removing the base URL.
 *    - Look up the original long URL in `shortToLongMap`.
 *
 * 4. **Generating a Short URL Key**:
 *    - Use a random generator to create a 6-character string from the character set (`a-z`, `A-Z`, `0-9`).
 *    - Ensure the generated key is unique by checking `shortToLongMap`.
 *
 * 5. **Base URL**:
 *    - The base URL (`http://tinyurl.com/`) is prepended to the short key to form the full short URL.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * Long URL: https://www.example.com/some/very/long/url
 *
 * Encoding:
 * 1. Generate a random short key, e.g., `abc123`.
 * 2. Store the mappings:
 *    - `shortToLongMap.put("abc123", "https://www.example.com/some/very/long/url")`
 *    - `longToShortMap.put("https://www.example.com/some/very/long/url", "abc123")`
 * 3. Return the short URL: `http://tinyurl.com/abc123`.
 *
 * Decoding:
 * 1. Extract the short key: `abc123`.
 * 2. Look up the original URL in `shortToLongMap`: `https://www.example.com/some/very/long/url`.
 *
 * Output:
 * Short URL: http://tinyurl.com/abc123
 * Decoded URL: https://www.example.com/some/very/long/url
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - Encoding: `O(1)` (constant time for hash map operations).
 *    - Decoding: `O(1)` (constant time for hash map lookup).
 *
 * 2. **Space Complexity**:
 *    - The space required is proportional to the number of URLs stored, i.e., `O(n)`.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: Long URL: https://www.google.com
 * Output: Short URL: http://tinyurl.com/<random_key>
 *         Decoded URL: https://www.google.com
 *
 * Test Case 2:
 * Input: Long URL: https://www.facebook.com
 * Output: Short URL: http://tinyurl.com/<random_key>
 *         Decoded URL: https://www.facebook.com
 *
 * Test Case 3:
 * Input: Short URL: http://tinyurl.com/xyz789 (non-existent key)
 * Output: Decoded URL: null
 */