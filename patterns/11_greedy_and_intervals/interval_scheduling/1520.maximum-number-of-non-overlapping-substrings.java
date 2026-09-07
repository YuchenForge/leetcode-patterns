/*
 * @lc app=leetcode id=1520 lang=java
 *
 * [1520] Maximum Number of Non-Overlapping Substrings
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        List<String> result = new ArrayList<>();

        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        // Store valid intervals as [start, end]
        List<int[]> intervals = new ArrayList<>();

        // Compute all valid intervals
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            // Only consider first occurrence of a character
            if (i != first[c]) {
                continue;
            }

            int end = last[c];
            boolean valid = true;

            for (int j = i; j < end; j++) {
                int curr = s.charAt(j) - 'a';

                if (first[curr] < i) {
                    valid = false;
                    break;
                }

                end = Math.max(end, last[curr]);
            }

            if (valid) {
                intervals.add(new int[] {i, end});
            }
        }

        // Find max non-overlapping intervals
        // Greedily choose intervals with earliest ending position
        intervals.sort((a, b) -> a[1] - b[1]);

        int prevEnd = -1;

        for (int[] interval: intervals) {
            if (interval[0] >= prevEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                prevEnd = interval[1];
            } 
        }

        return result;
    }
}
// @lc code=end

// Time: O(n)
// Space: O(1)