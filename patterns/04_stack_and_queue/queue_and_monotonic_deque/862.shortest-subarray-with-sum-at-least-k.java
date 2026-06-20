/*
 * @lc app=leetcode id=862 lang=java
 *
 * [862] Shortest Subarray with Sum at Least K
 */

import java.util.*;

// @lc code=start
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        // use long to prevent int overflow
        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i+1] = prefix[i] + nums[i];
        }

        Deque<Integer> dq = new ArrayDeque<>();
        int ans = Integer.MAX_VALUE;

        for (int j = 0; j <= nums.length; j++) {
            while (!dq.isEmpty() && prefix[j] - prefix[dq.peek()] >= k) {
                ans = Math.min(ans, j - dq.pollFirst());
            }

            while (!dq.isEmpty() && prefix[j] <= prefix[dq.peekLast()]) {
                dq.pollLast();
            }

            dq.offerLast(j);

        }

        return ans == Integer.MAX_VALUE? -1: ans;
    }
}

// Time: O(n)
// Space: O(n)
// @lc code=end

/**
 * sum(i..j) = prefix[j+1] - prefix[i]
    Find smallest (j - i) where prefix[j] - prefix[i] >= k
                    ↑ index gap         ↑ value gap
 */