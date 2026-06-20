/*
 * @lc app=leetcode id=162 lang=java
 *
 * [162] Find Peak Element
 */

// @lc code=start
class Solution {
    public int findPeakElement(int[] nums) {
        int lo = 0, hi = nums.length - 1;

        while (lo < hi) {        
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] < nums[mid + 1]) {
                // going uphill → peak is to the right
                lo = mid + 1;
            } else {
                // going downhill → peak is to the left (mid could be peak)
                hi = mid;
            }
        }

        // lo == hi, converged on a peak
        return lo;  
    }
}

// Time: O(log n)
// Space: O(1)
// @lc code=end

