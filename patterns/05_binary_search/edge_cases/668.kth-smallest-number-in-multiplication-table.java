/*
 * @lc app=leetcode id=668 lang=java
 *
 * [668] Kth Smallest Number in Multiplication Table
 */

// @lc code=start
class Solution {
    public int findKthNumber(int m, int n, int k) {
        int lo = 1;
        int hi = m * n;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isFeasible(mid, m, n, k)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private boolean isFeasible(int mid, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            /*
            for (int j = 1; j <= n; j++) {
                if (j <= mid / i) {
                    count++;
                }
            }
            */
           count += Math.min(n, mid / i);
        }
        return count >= k;
    }
}

// Time: O(m log(mn))
// Space: O(1)
// @lc code=end

