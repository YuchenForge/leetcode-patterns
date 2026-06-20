# Pattern: top k and ordering

## When to Use
- **K largest / K smallest** elements in stream or array.
- **Kth largest / smallest (one shot)** use quickselect for O(n) average, heap for O(n log k)
- **K most frequent (heap version)** — bucket approach lives in `03_hashing/frequency_counting`.
- Custon ordering: most/least frequent first, multi-key priority

## Core Idea
- Min-heap size K keeps **K largest** (root = smallest among top K).
- Max-heap for K smallest, or negate values in min-heap.
- O(1) access to the min/max, and O(log n) to insert or remove it 
- Frequency map: map count → heap of `(count, key)` 

## Comparison
- Array: find min = O(n) every time
- Sorted array: find min = O(1), but insert = O(n)
- Heap: find min = O(1), insert = O(log n), remove min = O(log n)

## Comparator Rules
 
```java
/*
compare(a, b) returns:
  negative → a should be on top
  zero     → equal, doesn't matter
  positive → b should be on top
 
  a - b  →  MIN heap (smaller on top)
  b - a  →  MAX heap (larger on top)
 
  For doubles, use Double.compare(b, a) instead of b - a
  to avoid precision loss from (int) cast.
*/
```
 
---
 
## Comparison: Data Structures
 
| Structure | Find Min | Insert | Remove Min |
|-----------|----------|--------|------------|
| Array | O(n) | O(1) | O(n) |
| Sorted array | O(1) | O(n) | O(1) |
| Heap | O(1) | O(log n) | O(log n) |
 
---
 
## Heap Internals
 
```
Min-heap:
        2
      /   \
     5     3
    / \   /
   8   9 7
 
Array form: [2, 5, 3, 8, 9, 7]
Index:       0  1  2  3  4  5
 
parent(i) = (i-1)/2
left(i)   = 2i+1
right(i)  = 2i+2
```

## Templates
 
### Basics
 
```java
// Min-heap (default)
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.offer(5);
pq.offer(1);
pq.offer(3);
pq.peek();   // 1 — smallest, O(1)
pq.poll();   // removes and returns 1, O(log n)
 
// Max-heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
// or:
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
 
// Heap of arrays — order by first element
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
pq.offer(new int[]{3, 100});
pq.offer(new int[]{1, 200});
pq.peek(); // {1, 200} — smallest first element on top
```
 
### K Largest — Min-Heap of Size K
 
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int x : nums) {
    minHeap.offer(x);
    if (minHeap.size() > k) minHeap.poll(); // evict smallest
}
// minHeap.peek() = Kth largest
```
 
### K Most Frequent
 
```java
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // min by freq
for (var e : freq.entrySet()) {
    pq.offer(new int[]{e.getKey(), e.getValue()});
    if (pq.size() > k) pq.poll(); // evict least frequent
}
```

## Common Variations
| Variation | Heap | Alternative Approach |
|-----------|------|-----|
| K largest | Min-heap size K | Quickselect O(n) avg |
| K smallest | Max-heap size K | Quickselect O(n) avg |
| K frequent | Min-heap on count | See `03_hashing/frequency_counting` (bucket) |
| Kth element (one shot) | Min/max heap O(n log k) | Quickselect O(n) avg, O(1) space |

## Related Patterns
- k-way_merge_and_two_heaps - when K is the # of sorted list, not element count
- frequency_counting — 347 bucket approach in `03`
