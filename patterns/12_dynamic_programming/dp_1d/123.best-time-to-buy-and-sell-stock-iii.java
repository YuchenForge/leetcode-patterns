/*
 * @lc app=leetcode id=123 lang=java
 *
 * [123] Best Time to Buy and Sell Stock III
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;

        for (int price: prices) {
            buy1 = Math.max(buy1, -price);          // best cost basis for 1st buy
            sell1 = Math.max(sell1, buy1 + price);   // best profit after 1st sell
            buy2 = Math.max(buy2, sell1 - price);    // best net position after 2nd buy
            sell2 = Math.max(sell2, buy2 + price);   // best profit after 2nd sell
        }

        return sell2;
    }
}
// @lc code=end

