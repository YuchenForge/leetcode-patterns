# Pattern: subsets

## When to Use
- Generate **all subsets/combinations** (power set).
- Include/exclude each element; duplicates need sorting + skip.
- Combination sum — reuse allowed or not changes inner loop.

## Core Idea
- Pick index `start`; at each step choose include `nums[i]` or not (or loop i from start).
- **Backtrack**: add choice → recurse → remove choice.
- Duplicates: sort, skip `nums[i] == nums[i-1]` at same depth.

## Template
```java
void backtrack(int start, int[] nums, List<Integer> path, List<List<Integer>> results) {
    results.add(new ArrayList<>(path));            // record at EVERY node, not just leaves

    for (int i = start; i < nums.length; i++) {     // loop starts at `start`, not 0
        path.add(nums[i]);                          // choose
        backtrack(i + 1, nums, path, results);       // explore, only look forward
        path.remove(path.size() - 1);                // un-choose
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| All subsets | O(2^n) | O(n) path |
| Combination sum | O(2^n) | O(n) |

## Related Patterns
- Permutations — all orderings
- Constraint search — board pruning
