/*
 * @lc app=leetcode id=643 lang=java
 *
 * [643] Maximum Average Subarray I
 */

// @lc code=start
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int max = sum;

        for (int j = k; j < nums.length; j++) {
            sum += nums[j];
            sum -= nums[j - k];

            max = Math.max(max, sum);
        }

        return (double) max / k;
    }
}

// Time: O(n)
// Space: O(1)
// @lc code=end

