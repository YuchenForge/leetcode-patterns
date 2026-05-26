# Pattern: kth element

## When to Use
- Find **Kth largest / smallest** in unsorted array (one-shot or stream).
- **Quickselect** when O(n) average preferred over O(n log k) heap.
- **Partition** like quicksort — pivot splits rank.

## Core Idea
- **Quickselect**: partition around pivot; if pivot index == `n-k`, done; else recurse one side.
- **Heap**: O(n log k) time, O(k) space — simpler, good for streams.
- Kth largest = partition index `n - k` in ascending order.

## Template
```java
public int findKthLargest(int[] nums, int k) {
    int target = nums.length - k;
    int lo = 0, hi = nums.length - 1;
    while (lo <= hi) {
        int p = partition(nums, lo, hi);
        if (p == target) return nums[p];
        else if (p < target) lo = p + 1;
        else hi = p - 1;
    }
    return -1;
}

int partition(int[] a, int lo, int hi) {
    int pivot = a[hi], i = lo;
    for (int j = lo; j < hi; j++) {
        if (a[j] <= pivot) {
            int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
            i++;
        }
    }
    int tmp = a[i]; a[i] = a[hi]; a[hi] = tmp;
    return i;
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Quickselect avg | O(n) | O(1) |
| Heap size k | O(n log k) | O(k) |

## Related Patterns
- top_k — heap patterns
- streaming_median — two heaps
