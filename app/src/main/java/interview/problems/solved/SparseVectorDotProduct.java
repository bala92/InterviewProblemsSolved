package interview.problems.solved;

import java.util.HashMap;
import java.util.Map;

// Meta interview Question Jun 30th 2025
public class SparseVectorDotProduct {

    static class SparseVector {
        private final Map<Integer, Integer> indexValueMap;

        // Constructor to initialize the sparse vector
        public SparseVector(int[] nums) {
            indexValueMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    indexValueMap.put(i, nums[i]);
                }
            }
        }

        // Method to compute the dot product with another sparse vector
        public int dotProduct(SparseVector other) {
            int result = 0;

            // Iterate through the non-zero elements of this vector
            for (Map.Entry<Integer, Integer> entry : indexValueMap.entrySet()) {
                int index = entry.getKey();
                int value = entry.getValue();

                // If the other vector also has a non-zero value at this index, multiply and add
                if (other.indexValueMap.containsKey(index)) {
                    result += value * other.indexValueMap.get(index);
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        // Example usage
        int[] nums1 = {1, 0, 0, 2, 3};
        int[] nums2 = {0, 3, 0, 4, 3};

        SparseVector v1 = new SparseVector(nums1);
        SparseVector v2 = new SparseVector(nums2);

        // Compute the dot product
        int result = v1.dotProduct(v2);
        System.out.println("Dot Product: " + result); // Output: 8
    }
}