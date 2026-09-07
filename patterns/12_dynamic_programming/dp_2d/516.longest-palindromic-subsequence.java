/*
 * @lc app=leetcode id=516 lang=java
 *
 * [516] Longest Palindromic Subsequence
 */

// @lc code=start
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[] dp = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            // single character
            dp[i] = 1;
            int prev = 0;

            for (int j = i + 1; j < n; j++) {
                int temp = dp[j];

                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = 2 + prev;
                } else {
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }

                prev = temp;
            }
        }

        return dp[n-1];
    }
}
// @lc code=end

class Solution2 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // len of longest palindromic subsequence inside s[i...j]
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            // single character
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][n-1];
    }
}