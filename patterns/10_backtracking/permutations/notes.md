# Pattern: permutations

## When to Use
- **All orderings** of a set matter — [1,2] and [2,1] are different answers
- Word/letter arrangements, task scheduling orders, "next permutation"-style problems
- Any problem where you're told "arrange" or "order" rather than "choose" or "pick"

## Core Idea
- At every level of the tree, any unused element is a valid next choice — the 
candidate pool is the same size logic at each level (n, then n-1 available, etc.), 
not shrinking by index
- Track what's already in the current path with a used[] array (or Set), not a 
start index — because order matters, you must be able to revisit earlier indices 
in later positions
- Base case is a fixed depth: path.size() == nums.length
- Always copy the path when recording — it's the same mutable list being reused 
across all branches

## Template
```java
void backtrack(int[] nums, List<Integer> path, boolean[] used, List<List<Integer>> results) {
    if (path.size() == nums.length) {              // fixed depth reached = full permutation
        results.add(new ArrayList<>(path));         // copy! path keeps mutating after this
        return;
    }
    for (int i = 0; i < nums.length; i++) {          // loop ALWAYS starts at 0
        if (used[i]) continue;                       // constraint: no reuse within this path

        used[i] = true;
        path.add(nums[i]);                           // choose

        backtrack(nums, path, used, results);        // explore

        path.remove(path.size() - 1);                // un-choose
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
