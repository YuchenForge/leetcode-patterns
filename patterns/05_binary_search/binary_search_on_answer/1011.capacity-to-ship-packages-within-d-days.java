/*
 * @lc app=leetcode id=1011 lang=java
 *
 * [1011] Capacity To Ship Packages Within D Days
 */

// @lc code=start
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int lo = 0;
        int hi = 0;

        for (int w: weights) {
            lo = Math.max(lo, w);
            hi += w;
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isFeasible(weights, days, mid)) {
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private boolean isFeasible(int[] weights, int days, int capacity) {
        int daysNeeded = 1, total = 0;

        for (int weight : weights) {
            if (total + weight > capacity) {
                daysNeeded++;
                total = 0;
            }
            total += weight;
        }

        return daysNeeded <= days;
    }
}

// Time: O(n log S)
// Space: O(1)
// @lc code=end

