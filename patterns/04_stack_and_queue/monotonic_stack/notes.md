# Pattern: monotonic stack

## When to Use
- **Next greater / smaller element** for each index in O(n).
- **Largest rectangle in histogram** — width bounded by first smaller bars left/right.
- **Trapping rain water** (variant) — bounded by taller bars.
- **Stock span**, **daily temperatures** — days until warmer price.
- Maintain **increasing or decreasing** sequence of candidates; pop when current breaks order.

## Core Idea
- Instead of checking every pair in O(n^2), the stack keeps only useful elements
- Stack stores **indices** (or values) in monotonic order (usually increasing for “next greater”).
- When `nums[i]` violates order, **pop** — each popped index gets answer from current `i`.
- Each element pushed/popped once → **O(n)**.

## Template
```java
// Monotonic Stack — Find Next Greater Element (to the right)
// Decreasing stack: pop when nums[i] > top
int[] res = new int[n];
Arrays.fill(res, -1);

Deque<Integer> stack = new ArrayDeque<>();

for (int i = 0; i < n; i++) {

    while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {

        int idx = stack.pop();
        res[idx] = nums[i];
    }

    stack.push(i);
}

// Monotonic Stack — Next Smaller Element (to the right)
// Increasing stack: pop when nums[i] < top
while (!st.isEmpty() && nums[st.peek()] > nums[i]) {
    int idx = stack.pop();
    res[idx] = nums[i];
}

// Monotonic Stack — Previous Smaller (left boundary)
// Iterate left to right, increasing stack
// After popping, stack.peek() is the nearest smaller to the left
Deque<Integer> stack = new ArrayDeque<>();

for (int i = 0; i < n; i++) {
    while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
        stack.pop();
    }

    left[i] = stack.isEmpty()? -1: stack.peek();
    stack.push(i);
}
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
Use Monotonic Stack:
- nearest greater
- nearest smaller
- first larger
- first smaller
- span
- days until
- rectangle boundary

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Next greater per index | O(n) | O(n) |
| Histogram area | O(n) | O(n) |

## Related Patterns
- Stack basics — LIFO
- Two pointers — rain water alternate
