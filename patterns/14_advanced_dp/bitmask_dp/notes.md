# Pattern: bitmask dp

## When to Use
- Small n (n ≤ 20) — assign/order subsets of items.
- **TSP**, visiting all cities, Hamiltonian path count.
- State: `(mask, index)` where mask = set of visited/used bits.

## Core Idea
- `dp[mask][i]` = min cost to reach state with last at i.
- Transition: add unvisited `j` → `mask | (1<<j)`.
- Iterate masks increasing; O(2^n * n²).

## Template
```java
int N = 1 << n;
int[][] dp = new int[N][n];
// dp[1<<start][start] = 0; fill inf
for (int mask = 0; mask < N; mask++) {
    for (int u = 0; u < n; u++) {
        if ((mask & (1<<u)) == 0) continue;
        for (int v = 0; v < n; v++) {
            if ((mask & (1<<v)) != 0) continue;
            int nmask = mask | (1<<v);
            dp[nmask][v] = Math.min(dp[nmask][v], dp[mask][u] + dist[u][v]);
        }
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Bitmask DP | O(2^n * n) | O(2^n) |

## Related Patterns
- Subsets enumeration
- TSP style
