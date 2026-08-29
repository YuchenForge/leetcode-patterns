/*
 * @lc app=leetcode id=45 lang=java
 *
 * [45] Jump Game II
 */

// @lc code=start
class Solution {
    public int jump(int[] nums) {
        int jumps = 0;
        int start = 0;
        int end = 0;

        while (end < nums.length - 1) {
            int farthest = 0;
            for (int i = start; i <= end; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }
            start = end + 1;
            end = farthest;
            jumps++;
        }

        return jumps;
    }
}
// @lc code=end

// At each jump, choose the jump boundary that 
// gives us the largest future reach.