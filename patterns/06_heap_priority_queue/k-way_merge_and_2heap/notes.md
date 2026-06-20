# Pattern: k way merge and two heaps

## When to Use
- **Merge K sorted** lists/arrays — always pick the global minimum next.
- **Running / sliding window median** — balance lower and upper halves.
- Smallest range covering K lists.

## Core Idea
- **K-way merge**: seed heap with the head of each list as (value, list_index, 
element_index). Pop the global min, then push the next element from that 
same list. Heap always holds exactly one candidate per stream.
- **Two heaps (median)**: max-heap for lower half, min-heap for upper half. 
Invariant: low.size() >= high.size() and low.size() <= high.size() + 1. 
Median is top of low (odd total) or average of both tops (even total). 
This is K-way merge with K=2 and a balance constraint.

## Template
```java
List<List<Integer>> result = new ArrayList<>();

// [sum, i, j] and order by sum
PriorityQueue<int[]> minHeap = 
    new PriorityQueue<>((a, b) -> a[0] - b[0]);

// pair each nums1[i] with nums2[0]
for (int i = 0; i < Math.min(k, nums1.length); i++) {
    minHeap.offer(new int[] {nums1[i] + nums2[0], i, 0});
}

while (!minHeap.isEmpty() && result.size() < k) {
    // grab smallest sum
    int[] curr = minHeap.poll();
    int i = curr[1];
    int j = curr[2];
    
    result.add(Arrays.asList(nums1[i], nums2[j]));
    
    if (j + 1 < nums2.length) {
        // add next element on the right of num2
        minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
    }
}

return result;
```

```java
// Running median — two heaps
class MedianFinder {
    PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder()); // max-heap
    PriorityQueue<Integer> hi = new PriorityQueue<>();                            // min-heap

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

## Common Variations
| Variation | Heap |
|-----------|------|
| Merge K sorted lists | Min-heap of list heads |
| Merge K sorted arrays | Min-heap of (value, arr_i, idx) |
| Running median | Two heaps (max + min) |
| Sliding window median | Two heaps + lazy deletion |
| Smallest range covering K lists | Min-heap of heads + track global max |

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| K-way merge| O(n log k) | O(k) |
| addNum (two heaps)| O(log n) | O(n) |
| findMedian | O(1) | O(n) |

## Related Patterns
- top_k and order - K refers to element rank, not number of lists
- dijkstra_graph — also merges paths greedily; same heap mechanic
