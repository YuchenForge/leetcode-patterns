/*
 * @lc app=leetcode id=1482 lang=java
 *
 * [1482] Minimum Number of Days to Make m Bouquets
 */

// @lc code=start
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;

        if ((long) m * k > n) {
            return -1;
        }

        int lo = Integer.MAX_VALUE;
        int hi = Integer.MIN_VALUE;

        for (int d: bloomDay) {
            lo = Math.min(lo, d);
            hi = Math.max(hi, d);
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isFeasible(bloomDay, m, k, mid)) {
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private boolean isFeasible(int[] bloomDay, int m, int k, int days) {
        int sum = 0;
        int streak = 0;
        
        for (int b: bloomDay) {
            if (b <= days) {
                streak++;
                if (streak == k) {
                    sum++;
                    streak = 0;
                }
            } else {
                streak = 0;
            }
        }

        return sum >= m;
    }
}

// Time: O(n log D)
// Space: O(1)
// @lc code=end

