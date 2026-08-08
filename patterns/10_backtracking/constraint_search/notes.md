# Pattern: constraint search

## When to Use
- **Sudoku**, **N-Queens**, word search with pruning.
- Place choices on board/grid; **prune** early when invalid.
- Generate parentheses, IP addresses, valid expressions — validity check before recurse.
- Anything with hard constraints checked against the whole state so far (not just the last choice)

## Core Idea
- Define `isValid(candidate, state)` before recursing deeper.
- Undo choice on backtrack (clear cell, remove queen).
- Row-by-row or cell-by-cell reduces branching.
- The state is usually mutated in place, not copied

## Template
```java
void backtrack(State state, int slot, int totalSlots, List<Result> results) {
    if (slot == totalSlots) {          // all slots filled = complete, valid assignment
        results.add(recordSolution(state));
        return;
    }
    for (Object candidate : candidatesFor(slot, state)) {
        if (isValid(candidate, slot, state)) {
            place(state, slot, candidate);        // choose
            backtrack(state, slot + 1, totalSlots, results); // explore
            unplace(state, slot);                 // un-choose
        }
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Backtrack with prune | O(branch^depth) worst case | O(depth) |

## Related Patterns
- Subsets — combination without board
- DFS grid — same undo pattern
