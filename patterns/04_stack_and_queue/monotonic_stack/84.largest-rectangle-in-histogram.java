/*
 * @lc app=leetcode id=84 lang=java
 *
 * [84] Largest Rectangle in Histogram
 */

import java.util.*;

// @lc code=start
class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = heights.length;

        int maxArea = 0;

        for (int i = 0; i <= n; i++) {

            int h = (i == n)? 0: heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty()? i: i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }
}

// Time: O(n)
// Space: O(n)
// @lc code=end

// for each bar, find how far left and right 
// it can extend while remaining the shortest bar
