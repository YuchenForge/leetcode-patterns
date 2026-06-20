/*
 * @lc app=leetcode id=540 lang=java
 *
 * [540] Single Element in a Sorted Array
 */

// @lc code=start
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if ((mid % 2 == 0 && nums[mid] == nums[mid + 1]) 
            || (mid % 2 == 1 && nums[mid] == nums[mid - 1])) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return nums[lo];
    }
}

// Time: O(log n)
// Space: O(1)
// @lc code=end

