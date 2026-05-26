# Pattern: two pointers

## When to Use
- Sorted Data：find elements meeting a specific constraint (e.g., Target Sum)
- Pairs/Triplets: find two or more elements that satisfy a condition
- In-place Manipulation: modify an array without using extra space (e.g., removing duplicates).
- Linked list middle / nth-from-end → see `fast_slow_pointers` (not opposite-end two pointers).

## Core Idea
- Initializing two pointers that move through the data structure in a coordinated way
- Never re-scans the same sub-problem unnecessarily.

## Template
```java
public void twoPointerTemplate(int[] arr) {
    int left = 0;
    int right = arr.length - 1;

    while (left < right) {
        // Calculate current state
        int currentSum = arr[left] + arr[right];

        if (currentSum == target) {
            // Found the logic
            return; 
        } else if (currentSum < target) {
            left++; // Need a larger value
        } else {
            right--; // Need a smaller value
        }
    }
}
```

## Common Variations
- Opposite Ends: Pointers start at 0 and n-1 and move toward each other 
    (e.g., Binary Search, Reverse Array, Two Sum in sorted array)
- Fast and Catch-up: Both start at the beginning; one pointer moves based 
    on a condition while the other stays back to "mark" a spot 
    (e.g., Remove Duplicates, Move Zeroes)


## Related Patterns
- Sliding Window — contiguous substring/subarray
- Binary Search — sorted search
- Fast/slow — linked list (not opposite ends)
