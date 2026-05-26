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
int[] dp = new int[n + 1];
dp[0] = base;
for (int i = 1; i <= n; i++) {
    dp[i] = Math.max(dp[i-1], dp[i-2] + gain(i));
}
return dp[n];
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
