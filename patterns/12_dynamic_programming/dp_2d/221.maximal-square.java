/*
 * @lc app=leetcode id=221 lang=java
 *
 * [221] Maximal Square
 */

// @lc code=start
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] dp = new int[n + 1];

        int maxSide = 0;

        for (int i = 1; i <= m; i++) {
            int prev = dp[0];

            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (matrix[i-1][j-1] == '1') {
                    dp[j] = 1 + 
                        Math.min(Math.min(
                            dp[j], 
                            dp[j-1]), 
                            prev);

                    maxSide = Math.max(maxSide, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }

        return maxSide * maxSide;
    }
}
// @lc code=end

class Solution2 {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // dp[i][j] = side length of the largest square 
        // whose bottom-right corner is (i, j)
        int[][] dp = new int[m + 1][n + 1];

        int maxSide = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = 1 + 
                        Math.min(Math.min(
                            dp[i-1][j], 
                            dp[i][j-1]), 
                            dp[i-1][j-1]);

                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }
}