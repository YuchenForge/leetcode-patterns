# Pattern: permutations

## When to Use
- **All orderings** of elements (permutations).
- Arrange with **constraints** (used array, duplicates).
- Next permutation / palindrome permutation.

## Core Idea
- Swap or use `used[]`; depth = n, leaf = full permutation.
- Duplicates: sort, skip same value at same depth if previous unused skipped.

## Template
```java
void permute(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
    if (path.size() == nums.length) {
        res.add(new ArrayList<>(path));
        return;
    }
    for (int i = 0; i < nums.length; i++) {
        if (used[i]) continue;
        if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
        used[i] = true;
        path.add(nums[i]);
        permute(nums, used, path, res);
        path.remove(path.size() - 1);
        used[i] = false;
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| All permutations | O(n!) | O(n) |

## Related Patterns
- Subsets — combinations not order
- Constraint search
