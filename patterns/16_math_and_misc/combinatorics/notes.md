# Pattern: combinatorics

## When to Use
- Count permutations/combinations **mathematically** (nCr, nPr).
- Pascal triangle, unique paths count.
- Modular arithmetic for large counts.

## Core Idea
- `C(n,k) = C(n-1,k-1) + C(n-1,k)` — Pascal / DP.
- Factorials precompute with mod inverse for `nCk mod p`.
- Stars and bars for distributing items (advanced).

## Template
```java
long nCk(int n, int k, long mod) {
    if (k > n) return 0;
    long[] fact = new long[n+1];
    fact[0] = 1;
    for (int i = 1; i <= n; i++) fact[i] = fact[i-1] * i % mod;
    // multiply by modular inverse of fact[k]*fact[n-k]
    return ...;
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Pascal row | O(n) | O(n) |
| nCk DP | O(n*k) | O(k) |

## Related Patterns
- Backtracking permutations
- DP paths
