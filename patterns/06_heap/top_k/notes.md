# Pattern: top k

## When to Use
- **K largest / K smallest** elements in stream or array.
- **K most frequent (heap version)** — bucket approach lives in `03_hashing/frequency_counting`.
- **Merge K sorted** lists/arrays — min-heap of heads.
- **Scheduling** — always process smallest/largest available (task scheduler).

## Core Idea
- Min-heap size K keeps **K largest** (root = smallest among top K).
- Max-heap for K smallest, or negate values in min-heap.
- Frequency: map count → heap of `(count, key)` or bucket index = count.

## Template
```java
// K largest — min-heap of size k
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int x : nums) {
    minHeap.offer(x);
    if (minHeap.size() > k) minHeap.poll();
}
// answer: minHeap.peek() is k-th largest
```

```java
// K most frequent
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
for (var e : freq.entrySet()) {
    pq.offer(new int[]{e.getKey(), e.getValue()});
    if (pq.size() > k) pq.poll();
}
```

## Common Variations
| Variation | Heap | Alt |
|-----------|------|-----|
| K largest | Min-heap size K | Quickselect O(n) avg |
| K smallest | Max-heap size K | |
| K frequent | Heap on count | See `03_hashing/frequency_counting` (bucket) |
| Merge K lists | Min-heap of ListNode | Divide & conquer |

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Min-heap size k | O(n log k) | O(k) |
| Merge k lists | O(N log k) | O(k) |

## Related Patterns
- kth_element — quickselect
- frequency_counting — 347 bucket in `03`
