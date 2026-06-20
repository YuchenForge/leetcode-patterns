/*
 * @lc app=leetcode id=2141 lang=java
 *
 * [2141] Maximum Running Time of N Computers
 */

// @lc code=start
class Solution {
    public long maxRunTime(int n, int[] batteries) {
        // ans between 1 and split even
        long lo = 1;
        long hi = 0;

        for (int b: batteries) {
            hi += b;
        }

        hi = hi / n;

        while (lo < hi) {
            long mid = lo + (hi - lo + 1) / 2;

            if (isFeasible(batteries, n, mid)) {
                lo = mid;
            }
            else {
                hi = mid - 1;
            }
        }

        return lo;
    
    }

    // Given a time mid
    // Feasible if:
        // sum of min(batteries[i], T) >= n * T
    // Return if possible
    private boolean isFeasible(int[] batteries, int n, long T) {
        long sum = 0;

        for (int b: batteries) {
            sum += Math.min(b, T);
        }

        return sum >= n * T;

    }
    
}

// Time: O(n log T)
// Space: O(1)
// @lc code=end

