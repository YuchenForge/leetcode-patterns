# Pattern: greedy patterns

## When to Use
- Local optimal → global optimal (must prove or recognize classic).
- **Jump game**, **gas station**, **assign cookies**, **partition labels**.
- Exchange argument: swapping never hurts.
- "Maximum/Minimum" questions (DP also ask for max and min tho)
- You can identify a "best" choice at each step:
    - farthest reach
    - earliest finish time
    - smallest sufficient resource
    - largest available value


## Core Idea
- At each step:
    - Make the best local choice.
    - Maintain an invariant describing the best state so far.
    - Prove the greedy choice is safe.
    - Continue without reconsidering previous choices.
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

Examples:
Problem	            Greedy Choice	                       Invariant
|------------------|--------------------------------------|----------|
Jump Game	        Farthest reachable position	           reach
Gas Station	        Eliminate failed starting points       tank, start
Assign Cookies	    Smallest sufficient cookie	           two pointers
Partition Labels	Extend to furthest required boundary   end
Interval Scheduling	Earliest finishing interval	           lastEnd

## Prove Greedy
1. Exchange Argument
    - Show that an optimal solution can replace its choice with the greedy choice without becoming worse.
2. Staying Ahead
    - Show that after every step, greedy is at least as good as any alternative.
3. Elimination
    - Show that when a candidate fails, an entire group of candidates can be discarded.

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Greedy scan | O(n) | O(1) |

## Related Patterns
- Interval scheduling
- DP when greedy fails
