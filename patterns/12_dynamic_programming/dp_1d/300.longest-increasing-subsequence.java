/*
 * @lc app=leetcode id=300 lang=java
 *
 * [300] Longest Increasing Subsequence
 */

import java.util.*;

// @lc code=start
// Binary Search Approach: O(n log n)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0; 

        for (int x: nums) {
            int lo = 0;
            int hi = size;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tails[mid] < x) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            tails[lo] = x;
            if (lo == size) size++;
        }

        return size;
    }
}
// @lc code=end

// DP Approach: O(n^2)
class Solutio2 {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); 
        int maxLen = 1;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }
}