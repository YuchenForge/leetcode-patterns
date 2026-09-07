# Pattern: dp 1d

## When to Use
- Optimal substructure on **linear sequence** (stairs, rob houses, decode ways).
- Count ways / max/min with constraints on previous states.
- State = index `i` or few previous values.

## Core Idea
- `dp[i]` = answer for prefix ending at i.
- Transition from `dp[i-1]`, `dp[i-2]`, or scan j < i.
- Space optimize to O(1) or O(k) if only last k states matter.

## Template
```java
// Naive recursion: O(2^n) - recomputes the same values over and over
static int fibNaive(int n) {
    if (n <= 1) return n;
    return fibNaive(n - 1) + fibNaive(n - 2);
}

// Memoization: O(n) - cache results
static int fibMemo(int n, int[] memo) {
    if (n <= 1) return n;
    if (memo[n] != -1) return memo[n];
    memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
    return memo[n];
}

// Tabulation: O(n) time, O(n) space
static int fibTab(int n) {
    if (n <= 1) return n;
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
}

// Optimized: O(n) time, O(1) space — only need the last 2 values
static int fibOptimal(int n) {
    if (n <= 1) return n;
    int prev2 = 0, prev1 = 1;
    for (int i = 2; i <= n; i++) {
        int curr = prev1 + prev2;
        prev2 = prev1;
        prev1 = curr;
    }
    return prev1;
}
```

## Common Variations
| Problem | State | Transition |
|---------|-------|------------|
| Climbing stairs | ways to i | dp[i]=dp[i-1]+dp[i-2] |
| House robber | max to i | max(dp[i-1], dp[i-2]+a[i]) |
| LIS | len ending i | j<i if a[j]<a[i] |
| Coin change | min coins | dp[x]=min(dp[x-c]+1) |

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| 1D DP scan | O(n) | O(n) or O(1) |
| LIS O(n^2) | O(n^2) | O(n) |

## Related Patterns
- Knapsack — subset capacity
- DP 2D — two sequences
