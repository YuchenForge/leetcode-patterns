# Pattern: merge intervals

## When to Use
- Overlapping intervals — **merge**, insert, cover.
- Sort by start (or end) then sweep.
- Meeting rooms, non-overlapping intervals.

## Core Idea
- Sort `intervals[i][0]`; merge if current start ≤ last end.
- Insert: add interval, merge in one pass after sort.
- Minimum arrows/points: sort by end, greedy keep one point per group.

## Template
```java
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
List<int[]> merged = new ArrayList<>();
for (int[] iv : intervals) {
    if (merged.isEmpty() || iv[0] > merged.get(merged.size()-1)[1]) {
        merged.add(iv.clone());
    } else {
        merged.get(merged.size()-1)[1] = Math.max(merged.get(merged.size()-1)[1], iv[1]);
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Sort + merge | O(n log n) | O(n) |

## Related Patterns
- Interval scheduling — max non-overlap
- basic_array_tricks — LC 57 insert
