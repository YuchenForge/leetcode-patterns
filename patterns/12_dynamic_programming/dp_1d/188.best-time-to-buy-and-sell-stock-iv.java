/*
 * @lc app=leetcode id=188 lang=java
 *
 * [188] Best Time to Buy and Sell Stock IV
 */

import java.util.*;

// @lc code=start
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (k == 0) return 0;

        int n = prices.length;
        int profit = 0;
        if (k >= n / 2) {
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i-1]) {
                    profit += prices[i] - prices[i-1];
                }
            }
            
            return profit;
        }
        
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);

        for (int price: prices) {
            for (int t = 1; t <= k; t++) {
                // hold prev or sell t-1 and buy curr price
                buy[t] = Math.max(buy[t], sell[t-1] - price);
                // keep profit from t or sell today 
                sell[t] = Math.max(sell[t], buy[t] + price);
            }
        }
        
        return sell[k];
    }
}
// @lc code=end

// Time: O(n · k)
// Space: O(k)