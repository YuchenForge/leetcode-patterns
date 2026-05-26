/*
 * @lc app=leetcode id=995 lang=java
 *
 * [995] Minimum Number of K Consecutive Bit Flips
 */

// @lc code=start
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int[] flipDiff = new int[n + 1];
        int result = 0;
        int flipCount = 0;

        for (int i = 0; i < n; i++) {
            flipCount += flipDiff[i];
            int cur = nums[i] ^ (flipCount % 2);

            if (cur == 0) {
                if (i + k > n) {
                    return -1;
                }

                flipCount++;         
                flipDiff[i + k]--;

                result++;
            }
        }

        return result;
    }
}
// @lc code=end

