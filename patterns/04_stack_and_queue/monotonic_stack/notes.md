# Pattern: monotonic stack

## When to Use
- **Next greater / smaller element** for each index in O(n).
- **Largest rectangle in histogram** — width bounded by first smaller bars left/right.
- **Trapping rain water** (variant) — bounded by taller bars.
- **Stock span**, **daily temperatures** — days until warmer price.
- Maintain **increasing or decreasing** sequence of candidates; pop when current breaks order.

## Core Idea
- Stack stores **indices** (or values) in monotonic order (usually increasing for “next greater”).
- When `nums[i]` violates order, **pop** — each popped index gets answer from current `i`.
- Each element pushed/popped once → **O(n)**.

## Template
```java
// Monotonic Stack — Next Greater Element (to the right)
// Decreasing stack: pop when nums[i] > top
int[] res = new int[n];
Arrays.fill(res, -1);
Deque<Integer> st = new ArrayDeque<>();     // stores indices
for (int i = 0; i < n; i++) {
    while (!st.isEmpty() && nums[st.peek()] < nums[i]) {
        res[st.pop()] = nums[i];            // i is the next greater for popped index
    }
    st.push(i);
}
// anything left in stack has no greater element → stays -1

// Monotonic Stack — Next Smaller Element (to the right)
// Increasing stack: pop when nums[i] < top
while (!st.isEmpty() && nums[st.peek()] > nums[i]) {
    res[st.pop()] = nums[i];
}

// Monotonic Stack — Previous Smaller (left boundary)
// Iterate left to right, increasing stack
// After popping, stack.peek() is the nearest smaller to the left
while (!st.isEmpty() && nums[st.peek()] >= nums[i]) st.pop();
left[i] = st.isEmpty() ? -1 : st.peek();
st.push(i);
```

```java
// Histogram max rectangle — pop when height < current
int max = 0;
Deque<Integer> st = new ArrayDeque<>();
for (int i = 0; i <= heights.length; i++) {
    int h = (i == heights.length) ? 0 : heights[i];
    while (!st.isEmpty() && heights[st.peek()] > h) {
        int height = heights[st.pop()];
        int width = st.isEmpty() ? i : i - st.peek() - 1;
        max = Math.max(max, height * width);
    }
    st.push(i);
}
```

## Common Variations
| Variation | Monotonic order | Answer |
|-----------|-----------------|--------|
| Next greater to right | Decreasing stack | First warmer on right |
| Next smaller to left | Increasing stack | Bound for rectangle width |
| Circular array | Run 2n or duplicate logic | LC 503 |
| Contribution counting | Pop and multiply counts | LC 907 |

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Next greater per index | O(n) | O(n) |
| Histogram area | O(n) | O(n) |

## Related Patterns
- Stack basics — LIFO
- Two pointers — rain water alternate
