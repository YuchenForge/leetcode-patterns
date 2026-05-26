# Pattern: classic binary search

## When to Use
- **Sorted array** — find target, first/last occurrence, insert position.
- **Search space is monotonic** — predicate `ok(mid)` flips from false to true (or vice versa).
- **Minimize/maximize** with discrete choices — often becomes binary search on answer (separate topic).
- **Matrix** — treat row/col as 1D if sorted (LC 74).

## Core Idea
- Maintain `[lo, hi]` invariant; shrink until found or `lo == hi`.
- Use `mid = lo + (hi - lo) / 2` to avoid overflow.
- **Lower bound**: first index `>= target`. **Upper bound**: first index `> target`.

## Template
```java
// Exact target in sorted array
int lo = 0, hi = n - 1;
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] == target) return mid;
    else if (nums[mid] < target) lo = mid + 1;
    else hi = mid - 1;
}
return -1;
```

```java
// Lower bound: first index with nums[i] >= target
int lo = 0, hi = n; // [lo, hi) half-open
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] < target) lo = mid + 1;
    else hi = mid;
}
return lo; // insert position if not found
```

## Common Variations
| Variation | Loop style | Use |
|-----------|------------|-----|
| Standard | `lo <= hi` | Exact match |
| Lower/upper bound | `lo < hi`, half-open | First/last equal |
| Rotated sorted | Compare which half sorted | LC 33 |
| 2D matrix | Flatten index or row/col BS | LC 74 |
| Peak element | Compare mid with mid+1 | LC 162 |

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Binary search | O(log n) | O(1) |
| Lower bound | O(log n) | O(1) |

## Related Patterns
- BS on answer — optimize value not index
- Two pointers — sorted pairs
