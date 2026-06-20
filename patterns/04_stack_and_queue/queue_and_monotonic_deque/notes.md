# Pattern: queue and deque

## When to Use
- **BFS** — level-order tree traversal, shortest path in unweighted graph/grid.
- **FIFO simulation** — task scheduling, hit counter, moving average.
- **Sliding window max/min** — monotonic deque, O(n).
- **Palindrome / stream** — compare both ends with a deque.
- **Design problems** — queue from two stacks, stack from two queues.

Key question: do you need FIFO order (queue) or access to both ends (deque)?
If you need "nearest useful value in a window" → monotonic deque.

## Core Idea
- Queue = **first in, first out**. `offer` to tail, `poll` from head.
- Use `ArrayDeque` for both queues and deques — never `LinkedList` (worse cache performance).
- BFS: enqueue start, **mark visited on enqueue** (not dequeue), loop until empty.
- Monotonic deque: store indices in decreasing (or increasing) value order.
  - Pop from **back** when a new element dominates (useless old values).
  - Pop from **front** when an index falls outside the window (expired).
  - Front of deque = answer for the current window.

## Template

```java
// Basic Operations
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(5);       // add to back
queue.offer(10);
queue.offer(3);

queue.peek();         // look at front → 5  (no remove)
queue.poll();         // remove from front → 5

queue.isEmpty();      // true / false
queue.size();         // number of elements


// BFS — graph or grid
Queue<int[]> q = new ArrayDeque<>();
q.offer(new int[]{sr, sc});
boolean[][] seen = new boolean[m][n];
seen[sr][sc] = true;                          // mark on enqueue, not dequeue

while (!q.isEmpty()) {
    int[] cell = q.poll();
    for (int[] d : dirs) {
        int nr = cell[0] + d[0], nc = cell[1] + d[1];
        if (inBounds(nr, nc) && !seen[nr][nc]) {
            seen[nr][nc] = true;
            q.offer(new int[]{nr, nc});
        }
    }
}

// BFS — level-by-level (when depth/distance matters)
int level = 0;
while (!q.isEmpty()) {
    int size = q.size();                      // snapshot before inner loop
    for (int i = 0; i < size; i++) {
        int[] cell = q.poll();
        // all nodes here are at `level`
        // enqueue neighbors as above
    }
    level++;
}

// Sliding window maximum — monotonic deque (decreasing)
Deque<Integer> dq = new ArrayDeque<>();       // stores indices
int[] res = new int[nums.length - k + 1];
for (int i = 0; i < nums.length; i++) {
    while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i])
        dq.pollLast();                        // dominated — useless
    dq.offerLast(i);
    if (dq.peekFirst() <= i - k)
        dq.pollFirst();                       // expired — outside window
    if (i >= k - 1)
        res[i - k + 1] = nums[dq.peekFirst()];
}
```

## Common Variations
- **BFS shortest path**: poll, expand neighbors, count levels with size snapshot.
- **Multi-source BFS**: enqueue all sources at once before the loop (e.g., rotting oranges, walls and gates).
- **Sliding window max**: monotonic deque, decreasing order — pop back on domination, front on expiry.
- **Sliding window min**: same, increasing order.
- **Moving average**: deque as a bounded FIFO — poll front when full, keep a running sum.
- **Queue from two stacks**: inbox stack for push, outbox stack for pop; lazy transfer when outbox is empty. O(1) amortized.
- **Palindrome / two-ended stream**: `offerLast` to add, compare `peekFirst` and `peekLast`.

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| BFS | O(V+E) | O(V) |
| Sliding window max/min | O(n) | O(k) |
| Moving average | O(1) per call | O(k) |
| Two-stack queue | O(1) amortized | O(n) |


## Related Patterns
- Stack / DFS — LIFO; backtracking, postfix evaluation
- Monotonic stack — single-ended; no window expiry
- Monotonic deque — sliding window extension of monotonic stack
- Two pointers — alternative for some window problems when max/min not needed