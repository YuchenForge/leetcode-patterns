/*
 * @lc app=leetcode id=11 lang=java
 *
 * [11] Container With Most Water
 */

// @lc code=start
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;

        int maxArea = 0;

        while (left < right) {
            // Area depends on shorter line
            int area = Math.min(height[left], height[right]) * (right-left);
            if (area > maxArea) {
                maxArea = area;
            }

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}

// Time: O(n)
// Space: O(1)
// @lc code=end

