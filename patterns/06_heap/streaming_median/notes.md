# Pattern: streaming median

## When to Use
- **Dynamic median** as numbers arrive (data stream).
- Balance two halves: lower half max ≤ upper half min.

## Core Idea
- **Two heaps**: max-heap for lower half, min-heap for upper half.
- Keep sizes balanced: `lower.size() >= upper.size()` and `lower.size() <= upper.size() + 1`.
- Median: if odd, top of larger half; if even, average of both tops.

## Template
```java
class MedianFinder {
    PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> hi = new PriorityQueue<>();

    public void addNum(int num) {
        lo.offer(num);
        hi.offer(lo.poll());
        if (lo.size() < hi.size()) lo.offer(hi.poll());
    }

    public double findMedian() {
        if (lo.size() > hi.size()) return lo.peek();
        return (lo.peek() + hi.peek()) / 2.0;
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| addNum | O(log n) | O(n) |
| findMedian | O(1) | O(n) |

## Related Patterns
- top_k — one-sided heap
