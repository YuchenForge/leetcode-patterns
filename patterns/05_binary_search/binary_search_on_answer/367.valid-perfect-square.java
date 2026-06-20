/*
 * @lc app=leetcode id=367 lang=java
 *
 * [367] Valid Perfect Square
 */

// @lc code=start
class Solution {
    public boolean isPerfectSquare(int num) {
        int lo = 1;
        int hi = num;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if ((long) mid * mid >= num) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return (long) lo * lo == num;
    }
}

// Time: O(log n)
// Space: O(1)
// @lc code=end

