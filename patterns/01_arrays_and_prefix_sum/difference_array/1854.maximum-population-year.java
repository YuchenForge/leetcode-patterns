/*
 * @lc app=leetcode id=1854 lang=java
 *
 * [1854] Maximum Population Year
 */

// @lc code=start
class Solution {
    public int maximumPopulation(int[][] logs) {
        int[] year = new int[2051];

        for (int[] log: logs) {
            year[log[0]] += 1;
            year[log[1]] -= 1;

        }

        int maxNum = year[1950];
        int maxYear = 1950;

        for (int i = 1951; i < year.length; i++) {
            // prefix sum
            year[i] += year[i - 1];

            if (year[i] > maxNum) {
                maxNum = year[i];
                maxYear = i;
            }
        }

        return maxYear;
    }
}

// Time: O(n)
// Space: O(1)
// @lc code=end


