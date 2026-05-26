# Pattern: interval scheduling

## When to Use
- Maximize number of **non-overlapping** intervals (activity selection).
- Minimize resources (rooms, platforms) — sweep line or heap on end times.
- “Schedule as many as possible” → sort by **finish time**.

## Core Idea
- Greedy: pick interval ending earliest that doesn't conflict with last pick.
- Meeting rooms II: min-heap of end times = active meetings count.
- Sweep line: events (+1 start, -1 end) on timeline.

## Template
```java
// Max non-overlapping
Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
int count = 0, end = Integer.MIN_VALUE;
for (int[] iv : intervals) {
    if (iv[0] >= end) {
        count++;
        end = iv[1];
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Activity selection | O(n log n) | O(1) |
| Meeting rooms heap | O(n log n) | O(n) |

## Related Patterns
- Merge intervals
- Difference array timeline
