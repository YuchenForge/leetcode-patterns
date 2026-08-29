/*
 * @lc app=leetcode id=321 lang=java
 *
 * [321] Create Maximum Number
 */

// @lc code=start
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] best = new int[k];

        for (int i = Math.max(0, k - n); i <= Math.min(k, m); i++) {
            int[] max1 = maxSubsequence(nums1, i);
            int[] max2 = maxSubsequence(nums2, k - i);
            int[] candidate = merge(max1, max2);

            if (greater(candidate, 0, best, 0)) {
                best = candidate;
            }
        }

        return best;
    }

    // Pick largest subsequence from a given array of length
    private int[] maxSubsequence(int[] nums, int length) {
        int[] stack = new int[length];
        int top = -1;
        int toDrop = nums.length - length;

        for (int num: nums) {
            while (top >= 0 && stack[top] < num && toDrop > 0) {
                top--;
                toDrop--;
            }

            if (top < length - 1) {
                top++;
                stack[top] = num;
            } else {
                toDrop--;
            }
        }

        return stack;
    }

    // Given two array, merge in descending order while keep each one's internal order
    private int[] merge(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        int i = 0, j = 0;
        int index = 0;

        while (i < a.length || j < b.length) {
            if (greater(a, i, b, j)) {
                merged[index++] = a[i++];
            } else {
                merged[index++] = b[j++];
            }
        }

        return merged;
    }

    // Helper function for merge
    // Compares if a[i:] is lexicographically greater than b[j:]
    private boolean greater(int[] a, int i, int[] b, int j) {
        while (i < a.length && j < b.length && a[i] == b[j]) {
            i++;
            j++;
        }

        // b ran out or both didn't ran out and a > b
        return j == b.length || (i < a.length && a[i] > b[j]);
    }

}
// @lc code=end

// maxSubsequence: O(m) or O(n)
// greater: O(k) worst case
// merge: O(k^2) worst case

// Time: O(k · (m + n)²))