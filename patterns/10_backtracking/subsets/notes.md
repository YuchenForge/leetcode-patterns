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
void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> res) {
    res.add(new ArrayList<>(path));
    for (int i = start; i < nums.length; i++) {
        if (i > start && nums[i] == nums[i-1]) continue; // duplicates
        path.add(nums[i]);
        backtrack(nums, i + 1, path, res);
        path.remove(path.size() - 1);
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
