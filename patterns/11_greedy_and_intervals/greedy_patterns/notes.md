# Pattern: greedy patterns

## When to Use
- Local optimal → global optimal (must prove or recognize classic).
- **Jump game**, **gas station**, **assign cookies**, **partition labels**.
- Exchange argument: swapping never hurts.

## Core Idea
- Sort input by key (end time, profit, ratio).
- Maintain invariant (reachable index, current fuel, active set).
- If greedy fails, try DP or BS on answer.

## Template
```java
// Jump game — farthest reachable
int reach = 0;
for (int i = 0; i < nums.length; i++) {
    if (i > reach) return false;
    reach = Math.max(reach, i + nums[i]);
}
return true;
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Greedy scan | O(n) | O(1) |

## Related Patterns
- Interval scheduling
- DP when greedy fails
