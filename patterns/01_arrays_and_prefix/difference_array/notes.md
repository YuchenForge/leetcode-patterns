# Pattern: difference array

## When to Use
- Many **range updates** on array, few point queries (or final array only).
- Flight bookings, car pooling, range add constant.
- Complement to prefix sum: diff → prefix reconstructs array.

## Core Idea
- `diff[i] += val` at start, `diff[end+1] -= val` for range [start, end].
- After all updates: prefix sum `diff` → actual array.
- O(1) per range update, O(n) finalize.

## Template
```java
int[] diff = new int[n + 1];

// build diff array
void rangeAdd(int l, int r, int val) {
    diff[l] += val;
    if (r + 1 < diff.length) diff[r + 1] -= val;
}

// build prefix sum of diff array
int[] build() {
    int[] arr = new int[n];
    int cur = 0;
    for (int i = 0; i < n; i++) {
        cur += diff[i];
        arr[i] = cur;
    }
    return arr;
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Range add [l,r] | O(1) | O(n) |
| Finalize to array | O(n) | O(n) |

## Related Patterns
- Prefix sum — inverse of difference array
- Interval merge / sweep
