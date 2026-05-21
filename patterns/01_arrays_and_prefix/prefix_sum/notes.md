# Pattern: prefix sum

## When to Use
- subarray sum equals k
- range sum queries
- count subarrays with property
- continuous segment

## Core Idea
- Build an array where each position store the sum up to that point
- Instead of computing sum of num[l..r] every time -> O(n)
- You preprocess once -> O(n), query any range -> O(1)

## Formula
- sum(l,r) = prefix[r] - prefix[l-1]
- sum(l,r) = prefix[r] if l = 0

## Template
```java
class PrefixSum {
    int[] prefix;

    public PrefixSum(int[] nums) {
        int n = nums.length;
        prefix = new int[n];

        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
    }

    public int rangeSum(int l, int r) {
        if (l == 0) return prefix[r];
        return prefix[r] - prefix[l - 1];
    }
}
```

## Common Variations
1. Subarray sum = K
    Use prefix sum + hashmap
2. Subarray sum divisible by K
    Use (prefix % k) instead of raw prefix
3. Count subarrays with condition
    Replace sum with:
    XOR
    difference
    transformed value
4. Range sum queries
    Static array → prefix array
    Dynamic updates → Fenwick Tree / Segment Tree

## Common Pitfalls
- Forgetting base case: map.put(0, 1);
- Off-by-one errors in l - 1
- Using prefix array when sliding window is better (important distinction)
- Integer overflow (use long if needed)
- Confusing “count subarrays” vs “return subarray”

## Related Patterns
- Sliding Window (when array is non-negative / shrinking window works)
- HashMap frequency counting
- Difference Array (range updates)
- Kadane’s Algorithm (max subarray sum)
- Binary prefix tricks (bitwise XOR problems)
