# Pattern: hashmap basics

## When to Use
- Fast Lookup Needed: Need O(1) average-time search, insert, or delete.
- Counting Frequency: Count occurrences of elements (e.g., characters, numbers, words).
- Tracking Seen Values: Detect duplicates or previously visited elements.
- Key → Value Relationship: Store mappings (e.g., number → index).
- Complement Problems: Need to quickly find a matching value (e.g., Two Sum).
- Grouping Data: Group items by a property (e.g., anagrams).

## Core Idea
- Use a key-value store to avoid repeated scanning.
- Trade extra memory for faster access time.
- Store useful information while iterating so future lookups become constant time.

## Template
```java
HashMap<Integer, Integer> map = new HashMap<>();
HashSet<Integer> set = new HashSet<>();

map.put(key, value);             // insert/update
map.get(key);                    // retrieve
map.containsKey(key);            // key exists
map.getOrDefault(key, default);  // safe retrieval
map.putIfAbsent(key, value);     // insert if absent
map.remove(key);                 // delete

set.add(x);
set.contains(x);
set.remove(x);

// Time Complexity (Average):
/*
    Insert: O(1)
    Search: O(1)
    Delete: O(1)
*/
```

1. Frequency Count Pattern

```java
public void frequencyTemplate(int[] nums) {
    HashMap<Integer, Integer> map = new HashMap<>();

    // Count frequency
    for (int num : nums) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    // Use frequency
    for (int key : map.keySet()) {
        int count = map.get(key);

        if (count > 1) {
            // Logic here
        }
    }
}
```

2. Lookup / Complement Pattern
```java
public int[] hashMapTemplate(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];

        // Check if needed value already exists
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }

        // Store current number and index
        map.put(nums[i], i);
    }

    return new int[]{};
}
```

3. Grouping Pattern
```java
public List<List<String>> groupTemplate(String[] strs) {
    HashMap<String, List<String>> map = new HashMap<>();

    for (String str : strs) {
        // Example: sorted string as key
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String key = new String(chars);

        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(str);
    }

    return new ArrayList<>(map.values());
}
```

## Common Variations
- Frequency Counter: Count occurrences
(e.g., Two Sum, Top K Frequent Elements, Valid Anagram)
- Index Mapping: Store value → index
(e.g., Two Sum, Contains Duplicate)
- Prefix Sum + HashMap: Store prefix sums for range problems
(e.g., Subarray Sum Equals K)
- HashSet Variant: Only care about existence, not values
(e.g., Contains Duplicate, Longest Consecutive Sequence)
- Group by Key: Many items share a transformed key
(e.g., Group Anagrams)

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| get/put/contains | O(1)* | O(n) |
| One pass complement | O(n) | O(n) |

## Related Patterns
- Set usage — membership only
- Frequency counting — counts not just presence
- Prefix sum + map
