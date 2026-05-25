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

## Common Pitfalls
- Autoboxing Overhead: In Java, mapping Character to Integer creates 
thousands of wrapper objects in tight loops. Use primitive arrays (int[]) 
whenever constraints allow.
- Modifying Map While Iterating: Trying to remove elements from a HashMap 
while iterating over its keySet() throws a ConcurrentModificationException. 
Use an iterator or collect keys to remove first.

## Related Patterns
- Sliding Window: For managing a dynamic frequency counter of the current window.
- Heap / Priority Queue: For extracting the top K frequent elements efficiently 
in O(NlogK) time.
- Bucket Sort: For extracting top K frequent elements in O(N) time when frequency 
limits are known.
