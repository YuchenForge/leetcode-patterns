/*
 * @lc app=leetcode id=72 lang=java
 *
 * [72] Edit Distance
 */

// @lc code=start
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // dp[j] holds dp[i-1][j] before i is processed
        // dp[j] holds dp[i][j] after j is processed
        int[] dp = new int[n + 1];

        // base cases
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }

        for (int i = 1; i <= m; i++) {
            // hold dp[i-1][j-1]
            int prev = dp[0];
            dp[0] = i;

            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = prev;
                } else {
                    // swap characters
                    int swap = prev + 1;
                    // delete character
                    int delete = dp[j] + 1;
                    // insert character
                    int insert = dp[j-1] + 1;

                    dp[j] = Math.min(Math.min(delete, insert), swap);
                }

                prev = temp;
            }
        }

        return dp[n];
    }
}
// @lc code=end

// 2D approach
class Solution2 {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // dp[i][j] = operation to convert word1[0...i-1] to word2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Base cases: takes i/j operations to convert to empty string
        for (int i = 1; i <= m; ++i) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; ++j) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // same char, no operations needed
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // swap characters
                    int swap = dp[i-1][j-1] + 1;
                    // delete character
                    int delete = dp[i-1][j] + 1;
                    // insert character
                    int insert = dp[i][j-1] + 1;

                    dp[i][j] = Math.min(Math.min(delete, insert), swap);
                }
            }
        }

        return dp[m][n];
    }
}