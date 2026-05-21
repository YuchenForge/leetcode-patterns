# Pattern: sliding window

## When to Use
- Problems involving subarrays or substrings (contiguous sequences)
- Optimize brute force O(n²) or O(n³) solutions to O(n)
- Asked to find “longest”, 
                “shortest”, 
                “maximum/minimum sum”, or 
                “at most K distinct/valid condition”

## Core Idea
- Maintain a “window” (two pointers: left and right)
- Expand right pointer to include new elements
- Shrink left pointer when condition is violated
- Keep track of result while maintaining a valid window

- Two types:
- Fixed window size (size = k)
- Variable window size (condition-based)

## Template
```java
// Variable sized
public int solve(int[] nums) {
    int left = 0;
    int result = 0;

    // state (examples)
    int sum = 0;
    Map<Integer, Integer> freq = new HashMap<>();

    for (int right = 0; right < nums.length; right++) {
        int val = nums[right];

        // expand window
        sum += val;
        freq.put(val, freq.getOrDefault(val, 0) + 1);

        // shrink window until valid
        while (/* invalid condition */) {
            int leftVal = nums[left];
            sum -= leftVal;

            freq.put(leftVal, freq.get(leftVal) - 1);
            if (freq.get(leftVal) == 0) freq.remove(leftVal);

            left++;
        }

        // update answer (valid window)
        result = Math.max(result, right - left + 1);
    }

    return result;
}
```

```java
// fixed size
public int fixedWindow(int[] nums, int k) {
    int windowSum = 0;

    // build first window
    for (int i = 0; i < k; i++) {
        windowSum += nums[i];
    }

    int result = windowSum;

    for (int right = k; right < nums.length; right++) {
        windowSum += nums[right];
        windowSum -= nums[right - k];

        result = Math.max(result, windowSum);
    }

    return result;
}
```

## Common Variations
- Fixed-size window (k elements)
- Variable-size window (expand + shrink logic)
    - Longest Valid Window
        Expand aggressively. Shrink ONLY when invalid.
    -  Shortest Valid Window
        Once valid: shrink immediately, try minimizing
- At most / at least K constraints
    Track: distinctCount <= k
    If distinct exceeds k: shrink until restored
- “Exactly K” trick: 
    exactly K = atMost(K) − atMost(K−1)
- Frequency-based windows (HashMap / int array)
- Character problems (strings, anagrams, permutations)
- Binary/boolean constraints (0/1 strings, validity checks)

## Common Pitfalls
- Using if instead of while when shrinking window
- Forgetting to fully restore validity when shrinking
- Not updating/removing state when moving left
- Off-by-one errors in window size (right - left + 1)
- Assuming sliding window works with NEGATIVE numbers in sum problems (often it doesn’t)
- Mixing up “valid window” vs “best answer condition”
- Forgetting to initialize state for first window (fixed-size case)

## Related Patterns
- Two Pointers
- Prefix Sum
- Hash Map Counting
- Monotonic Queue (optimized sliding window)
