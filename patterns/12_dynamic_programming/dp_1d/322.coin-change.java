/*
 * @lc app=leetcode id=322 lang=java
 *
 * [322] Coin Change
 */

import java.util.Arrays;

// @lc code=start
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // sentinel value for "impossible"
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin: coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount? -1: dp[amount];
    }
}
// @lc code=end

// dp[amount] = 1 + min(dp[amount - coin]) 
// for every coin where coin <= amount