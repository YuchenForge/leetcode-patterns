/*
 * @lc app=leetcode id=1283 lang=java
 *
 * [1283] Find the Smallest Divisor Given a Threshold
 */
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int lo = 1;
        int hi = 0;

        for (int n: nums) {
            hi = Math.max(hi, n);
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isFeasible(nums, threshold, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;

    }

    private boolean isFeasible(int[] nums, int threshold, int divisor) {
        int sum = 0;
        for (int num: nums) {
            sum += (num + divisor - 1) / divisor;
            if (sum > threshold) {
                return false;
            }
        }

        return true;
    }
}

// Time: O(n log M)
// Space: O(1)
// @lc code=end

