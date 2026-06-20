/*
 * @lc app=leetcode id=33 lang=java
 *
 * [33] Search in Rotated Sorted Array
 */

// @lc code=start
class Solution {
    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == target) return mid;

            // left half is sorted
            if (nums[lo] <= nums[mid]) {
                // if target is in [nums[lo]..nums[mid]] → go left
                if (nums[lo] <= target && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    // else → go right
                    lo = mid + 1;
                }

            // right half is sorted
            } else {
                // if target is in [nums[mid]..nums[hi]] → go right
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    // else → go left
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }
}

// Time: O(log n)
// Space: O(1)
// @lc code=end

