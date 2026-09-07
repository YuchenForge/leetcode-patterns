/*
 * @lc app=leetcode id=97 lang=java
 *
 * [97] Interleaving String
 */

// @lc code=start
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int l = s3.length();

        if (m + n != l) return false;

        // dp[j] = dp[i-1][j] before updating and dp[i][j] after updating
        boolean[] dp = new boolean[n + 1];

        dp[0] = true;

        // i = 0
        // Only using characters from s2
        for (int j = 1; j <= n; j++) {
            dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= m; i++) {
            // dp[0] = dp[i][0]
            dp[0] = dp[0] && s1.charAt(i - 1) == s3.charAt(i - 1);

            for (int j = 1; j <= n; j++) {
                dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                    || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[n];
    }
}
// @lc code=end

// 2d DP
class Solution2 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int l = s3.length();

        if (m + n != l) return false;

        // dp[i][j] = if s1[:i-1] and s2[:j-1] can form s3[:i+j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                // char come from s1
                if (i > 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    if (dp[i-1][j]) {
                        dp[i][j] = true;
                    }
                }

                // char come from s2
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    if (dp[i][j-1]) {
                        dp[i][j] = true;
                    }
                }
            }
        }

        return dp[m][n];
    }
}