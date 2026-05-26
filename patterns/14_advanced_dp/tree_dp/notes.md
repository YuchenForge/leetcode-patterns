# Pattern: tree dp

## When to Use
- Optimization on trees — **house robber III**, max path, camera coverage.
- Return tuple from subtree `(withRoot, withoutRoot)`.
- DAG DP on trees (no cycles).

## Core Idea
- Postorder: compute answer for children before parent.
- Often two states: include node vs exclude node.
- Global variable for best path through node.

## Template
```java
int[] rob(TreeNode node) {
    if (node == null) return new int[]{0, 0};
    int[] L = rob(node.left), R = rob(node.right);
    int take = node.val + L[1] + R[1];
    int skip = Math.max(L[0], L[1]) + Math.max(R[0], R[1]);
    return new int[]{take, skip};
}
// answer = max(rob(root)[0], rob(root)[1])
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Tree DP postorder | O(n) | O(h) |

## Related Patterns
- Path problems — 124 max path
- Tree recursion
