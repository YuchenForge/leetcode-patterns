/*
 * @lc app=leetcode id=1438 lang=java
 *
 * [1438] Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 */

import java.util.*;

// @lc code=start
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        // decreasing, stores indices, front holds max
        Deque<Integer> maxD = new ArrayDeque<>(); 
        // increasing, stores indices, front holds min
        Deque<Integer> minD = new ArrayDeque<>();  
        int left = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {

            while (!maxD.isEmpty() && nums[maxD.peekLast()] <= nums[right]) {
                maxD.pollLast();
            }
            maxD.add(right);

            while (!minD.isEmpty() && nums[minD.peekLast()] >= nums[right]) {
                minD.pollLast();
            }
            minD.add(right);

            while (nums[maxD.peekFirst()] - nums[minD.peekFirst()] > limit) {
                if (maxD.peekFirst() == left) {
                    maxD.pollFirst();
                }
                if (minD.peekFirst() == left) {
                    minD.pollFirst();
                }
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }
        
        return ans;
    }
}

// Time: O(n)
// Space: O(n)
// @lc code=end

// 1. Maintain maxD: pop back while nums[back] <= nums[right]
// 2. Maintain minD: pop back while nums[back] >= nums[right]
// 3. Add right to both deques
// 4. Shrink window if max - min > limit
// 5. Update ans = max(ans, right - left + 1)