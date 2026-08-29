# Pattern: interval scheduling

## When to Use
- Interval problems about **overlap**, **scheduling**, and **grouping**.
- Maximize number of **non-overlapping** intervals (activity selection).
    - Greedy + sort
- Minimize resources (rooms, platforms) — sweep line or heap on end times.
    - Min-heap of end times, or Sweep line/events
- Merge overlapping intervals
    - sort by start time -> scan and merge

## Core Idea
- Greedy: pick interval ending earliest that doesn't conflict with last pick.
    - Then repeatedly choose the earliest-finishing interval that doesn't conflict with the previous choice.

    Sort by end time
            ↓
    Pick earliest finishing compatible interval
            ↓
    Update lastEnd
            ↓
    Repeat

- Meeting rooms II: min-heap of end times = active meetings count.
- Sweep line: events (+1 start, -1 end) on timeline.

## Exchange Argument
Show that an optimal solution can replace its choice with the greedy choice without becoming worse.

- Choosing the interval that finishes earliest leaves at least as much room for every future interval.

Therefore, replacing another first interval with the greedy interval cannot reduce the number of intervals we can schedule.

    Pattern:
    Optimal solution
        ↓
    Replace its choice with greedy choice
        ↓
    Solution is still optimal
        ↓
    Greedy choice is safe


## Template
```java
// Max non-overlapping
Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1])); 
int count = 0; 
int lastEnd = Integer.MIN_VALUE; 
for (int[] interval : intervals) { 
    if (interval[0] >= lastEnd) { 
        count++; 
        lastEnd = interval[1]; 
    }
} 
return count;
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Activity selection | O(n log n) | O(1) |
| Meeting rooms heap | O(n log n) | O(n) |

## Related Patterns
- Merge intervals
- Difference array timeline
