# Pattern: two pointers

## When to Use
- Sorted Data：find elements meeting a specific constraint (e.g., Target Sum)
- Pairs/Triplets: find two or more elements that satisfy a condition
- In-place Manipulation: modify an array without using extra space (e.g., removing duplicates).
- Linked Lists: Finding cycles, the middle element, or the k-th node from the end.

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
- Slow and Fast: One pointer moves faster than the other 
    (e.g., Linked List cycle detection, finding the middle of a list)
- Fast and Catch-up: Both start at the beginning; one pointer moves based 
    on a condition while the other stays back to "mark" a spot 
    (e.g., Remove Duplicates, Move Zeroes)

## Common Pitfalls
- Off-by-one errors 
- Unsorted Data
- Infinite Loops: Forgetting to increment or decrement the pointers inside the loop
- Integer Overflow: When calculating (left + right) / 2 in variations 
    like Binary Search (use left + (right - left) / 2 instead).

## Related Patterns
- Sliding Window
- Binary Search
- Greedy
