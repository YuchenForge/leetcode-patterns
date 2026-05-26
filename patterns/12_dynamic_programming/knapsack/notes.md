# Pattern: knapsack

## When to Use
- Choose items with **weight/capacity** limit — max value or count ways.
- **0/1** each item once; **unbounded** reuse allowed.
- Subset sum, partition equal subset, target sum.

## Core Idea
- `dp[w]` = best for capacity w; iterate items, update w **descending** (0/1).
- Unbounded: update w **ascending**.
- Count ways: `dp[w] += dp[w-weight]`.

## Template
```java
// 0/1 knapsack
int[] dp = new int[capacity + 1];
for (int i = 0; i < n; i++) {
    for (int w = capacity; w >= weights[i]; w--) {
        dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| 0/1 knapsack | O(n*W) | O(W) |
| Unbounded | O(n*W) | O(W) |

## Related Patterns
- DP 1D — coin change
- Subset sum — boolean dp
