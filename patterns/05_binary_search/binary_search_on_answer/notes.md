# Pattern: binary search on answer

## When to Use
- Minimize the **maximum** (or maximize the **minimum**) over feasible choices.
- Phrases: “smallest X such that…”, “minimum capacity/speed/time”, “can we finish in D days?”
- If you can write `feasible(mid)` monotonic (false…false, true…true), binary search the answer space.

## Core Idea
- Answer lives in range `[lo, hi]` (capacity, speed, time, distance).
- For candidate `mid`, check if feasible in **polynomial** time (often greedy).
- If feasible: try smaller (minimize) → `hi = mid`; else `lo = mid + 1`.

## Template
```java
// Minimize maximum such that feasible(x)
int lo = minAnswer, hi = maxAnswer;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (feasible(mid)) hi = mid;
    else lo = mid + 1;
}
return lo;
```

```java
// Maximize minimum such that feasible(x)
int lo = minAnswer, hi = maxAnswer;
while (lo < hi) {
    int mid = lo + (hi - lo + 1) / 2; // bias up for max-min
    if (feasible(mid)) lo = mid;
    else hi = mid - 1;
}
return lo;
```

## Common Variations
| Problem type | Feasibility check | Example |
|--------------|-------------------|---------|
| Min days / splits | Greedy pack with limit | LC 410, 1011 |
| Min max distance | Place K items with gap | LC 875 |
| Ship capacity | Sum weights in ≤ D days | LC 1011 |
| Split array largest sum | Greedy partition | LC 410 |
| Koko eating bananas | Hours ≤ h with speed | LC 875 |

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| BS on answer | O(log R * check) | O(1) |
| Feasibility check | depends | depends |

## Related Patterns
- Classic BS — sorted index
- Greedy — inside feasible()
