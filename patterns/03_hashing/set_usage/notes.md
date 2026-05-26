# Pattern: set usage

## When to Use
- **Existence only** — no value needed, use `HashSet`.
- Track seen elements while scanning (duplicate, cycle in sequence).
- Union of components — `Set` per group or single visited set in DFS/BFS.

## Core Idea
- `add` / `contains` / `remove` average O(1).
- When you need counts → `HashMap`; when only membership → `Set`.
- `TreeSet` for sorted order O(log n).

## Template
```java
Set<Integer> seen = new HashSet<>();
for (int x : nums) {
    if (!seen.add(x)) return true; // duplicate
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| add/contains | O(1)* | O(n) |
| TreeSet | O(log n) | O(n) |

## Related Patterns
- Hashmap basics — 217/128 canonical in that folder
