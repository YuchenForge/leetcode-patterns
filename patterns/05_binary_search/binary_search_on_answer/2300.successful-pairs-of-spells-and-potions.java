/*
 * @lc app=leetcode id=2300 lang=java
 *
 * [2300] Successful Pairs of Spells and Potions
 */

import java.util.*;

// @lc code=start
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);

        int n = spells.length;
        int m = potions.length;

        int[] pairs = new int[n];

        for (int i = 0; i < n; i++) {
            // binary search for leftmost index where
            // spells[i] * potions[mid] >= success

            int lo = 0;
            int hi = m;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if ((long) spells[i] * potions[mid] >= success) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            pairs[i] = m - lo;

        }

        return pairs;
    }
}

// Time: O(m log m + n log m)
// Space: O(1)
// @lc code=end

// hi = m instead of m - 1
// If no potion works, lo land at m (past the end), m - m = 0 pairs