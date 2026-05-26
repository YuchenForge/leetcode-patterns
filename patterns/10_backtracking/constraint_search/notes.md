# Pattern: constraint search

## When to Use
- **Sudoku**, **N-Queens**, word search with pruning.
- Place choices on board; **prune** early when invalid.
- Generate parentheses, IP addresses — validity check before recurse.

## Core Idea
- Define `isValid(choice)` before recursing deeper.
- Undo choice on backtrack (clear cell, remove queen).
- Row-by-row or cell-by-cell reduces branching.

## Template
```java
void solve(int row, char[][] board, ...) {
    if (row == n) { save(); return; }
    for (int col = 0; col < n; col++) {
        if (!canPlace(row, col, board)) continue;
        place(row, col, board);
        solve(row + 1, board, ...);
        remove(row, col, board);
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Backtrack with prune | O(branch^depth) | O(depth) |

## Related Patterns
- Subsets — combination without board
- DFS grid — same undo pattern
