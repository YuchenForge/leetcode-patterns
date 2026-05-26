# Pattern: interval dp

## When to Use
- Optimal on **subarray/substring range** [i, j] — burst balloons, stone merge, cut rod.
- Fill by **increasing length** of interval.
- Merge stones, palindrome partitioning cost.

## Core Idea
- `dp[i][j]` = answer for subproblem on i..j.
- Try split point k: combine `dp[i][k]` and `dp[k+1][j]`.
- Base: length 1 intervals.

## Template
```java
for (int len = 1; len <= n; len++) {
    for (int i = 0; i + len - 1 < n; i++) {
        int j = i + len - 1;
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + cost(i,j,k));
        }
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Interval DP len | O(n^3) | O(n^2) |

## Related Patterns
- DP 2D on strings
- Tree DP
