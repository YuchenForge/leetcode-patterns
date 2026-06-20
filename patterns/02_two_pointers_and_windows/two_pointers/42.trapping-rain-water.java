/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 */

// @lc code=start
class Solution {
    public int trap(int[] height) {
        // For any index i, Water = min(max_left, max_right) - height[i]
        int left = 0;
        int right = height.length - 1;
        int max_left = 0;
        int max_right = 0;

        int sum = 0;
    
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= max_left) {
                    max_left = height[left];
                } else {
                    sum += max_left - height[left];
                }
                left++;
            } else {
                if (height[right] >= max_right) {
                    max_right = height[right];
                } else {
                    sum += max_right - height[right];
                }
                right--;
            }
        }

        return sum;
    
    }
}

// Time: O(n)
// Space: O(1)
// @lc code=end

