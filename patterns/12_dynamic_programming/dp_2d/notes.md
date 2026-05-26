# Pattern: dp 2d

## When to Use
- Two sequences/strings — **LCS**, edit distance, interleaving.
- Grid paths — unique paths, min path sum.
- `dp[i][j]` depends on neighbors and previous row/col.

## Core Idea
- Fill table by increasing i, j; boundary first row/col.
- Space: rolling array if only previous row needed.

## Template
```java
int m = s.length(), n = t.length();
int[][] dp = new int[m + 1][n + 1];
for (int i = 0; i <= m; i++) dp[i][0] = i;
for (int j = 0; j <= n; j++) dp[0][j] = j;
for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
        if (s.charAt(i-1) == t.charAt(j-1))
            dp[i][j] = dp[i-1][j-1];
        else
            dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Grid DP | O(mn) | O(mn) or O(n) |
| Two string DP | O(mn) | O(mn) |

## Related Patterns
- DP 1D
- Interval DP
