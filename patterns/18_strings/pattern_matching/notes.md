# Pattern: pattern matching

## When to Use
- Find pattern in text — KMP, Rabin-Karp (less common in LC).
- **Regex / wildcard** — DP on pattern vs string.
- Repeated substring, strStr.

## Core Idea
- **KMP**: `lps` array — longest proper prefix which is suffix; never re-scan text index.
- **Wildcard DP**: `dp[i][j]` = match first i chars of s with first j of p.
- Rolling hash for substring equality O(1) average.

## Template
```java
// Wildcard matching simplified (* only)
boolean match(String s, String p) {
    int m = s.length(), n = p.length();
    boolean[][] dp = new boolean[m+1][n+1];
    dp[0][0] = true;
    for (int j = 1; j <= n; j++)
        if (p.charAt(j-1) == '*') dp[0][j] = dp[0][j-1];
    for (int i = 1; i <= m; i++)
        for (int j = 1; j <= n; j++) {
            char sc = s.charAt(i-1), pc = p.charAt(j-1);
            if (pc == '*') dp[i][j] = dp[i][j-1] || dp[i-1][j];
            else if (pc == '?' || sc == pc) dp[i][j] = dp[i-1][j-1];
        }
    return dp[m][n];
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| KMP | O(n+m) | O(m) |
| Regex DP | O(nm) | O(nm) |

## Related Patterns
- String basics
- Trie
