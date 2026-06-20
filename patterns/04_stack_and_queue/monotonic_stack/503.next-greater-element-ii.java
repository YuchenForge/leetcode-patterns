/*
 * @lc app=leetcode id=503 lang=java
 *
 * [503] Next Greater Element II
 */

import java.util.*;

// @lc code=start
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < 2*n; i++) {
            int j = i % n;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[j]) {

                int idx = stack.pop();
                res[idx] = nums[j];
            }

            stack.push(j);
        }

        return res;
    }
}

// Time: O(n)
// Space: O(n)
// @lc code=end

