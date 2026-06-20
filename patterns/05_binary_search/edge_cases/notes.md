# Pattern: binary search edge cases

## When to Use
- Same as binary search, but problems with **duplicates**, **empty**, **single element**, **overflow**, or **ambiguous bounds**.
- Interview focus: prove loop terminates and returns correct boundary.

## Core Idea
- Pick **one invariant** and stick to it:
  - Closed `[lo, hi]` with `lo <= hi` → `hi = mid - 1`, `lo = mid + 1`
  - Half-open `[lo, hi)` with `lo < hi` → `hi = mid` or `lo = mid + 1`
- For duplicates: find **leftmost** or **rightmost** equal element, not any equal.

## Template
```java
int lo = minAnswer, hi = maxAnswer;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (feasible(mid)) hi = mid;
    else lo = mid + 1;
}
return lo;
```

```java
// Rightmost index with nums[i] == target (upper bound - 1)
int lo = 0, hi = n;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] <= target) lo = mid + 1;
    else hi = mid;
}
return lo - 1; // last position <= target, check nums[lo-1]==target
```

```java
// BS on doubles (precision)
double lo = 0, hi = 1e9;
for (int i = 0; i < 100; i++) { // fixed iterations
    double mid = (lo + hi) / 2;
    if (ok(mid)) hi = mid;
    else lo = mid;
}
```

## Common Variations
| Case | Fix |
|------|-----|
| Empty array | Return -1 before loop |
| Single element | Loop still works; test manually |
| All equal elements | Lower/upper bound |
| `int` overflow | `mid = lo + (hi - lo) / 2` |
| Minimize max / maximize min | Separate templates (see binary_search_on_answer) |

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Lower/upper bound | O(log n) | O(1) |
| BS on doubles | O(iterations) | O(1) |

## Related Patterns
- Classic binary search
- BS on answer
