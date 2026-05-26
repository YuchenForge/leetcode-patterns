# Pattern: frequency counting

## When to Use
- Top K Elements: Finding the most, least, or K most frequent elements 
- Permutation/Anagram Checks: Verifying if two strings can be rearranged into each other.
- Character/Element Budgets: "Can we form string A using characters from string B?"
- Subarray/Substring Constraints: Tracking character counts inside a changing window

## Core Idea
- Store frequency for future use

## Template
```java
HashMap<Integer, Integer> freq = new HashMap<>();

for (int num : nums) {
    freq.put(num, freq.getOrDefault(num, 0) + 1);
}

List<Integer>[] bucket = new ArrayList[nums.length + 1];

for (int key : freq.keySet()) {
    int count = freq.get(key);

    if (bucket[count] == null) {
        bucket[count] = new ArrayList<>();
    }

    bucket[count].add(key);
}
```

## Common Variations
- The Decrement Check: Instead of building two maps to compare them, 
build one from dataset A, then iterate through dataset B, decrementing 
counts and checking if any drop below zero (e.g., Valid Anagram).
- Frequency Map + Max Variable: Tracking the absolute highest frequency 
on the fly during a single pass to avoid iterating over the map at the end.
- Frequency of Frequencies: Mapping Count -> List of Elements or using a 
Bucket Sort array where the index represents the frequency (e.g., Top K 
Frequent Elements).

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Build freq map | O(n) | O(k) |
| Bucket by freq | O(n) | O(n) |
| Window freq match | O(n) | O(1) alphabet |

## Related Patterns
- Hashmap basics — storage
- Sliding window — dynamic window counts
- Top K heap — `06_heap/top_k` for heap version of 347
