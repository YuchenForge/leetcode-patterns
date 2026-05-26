# Pattern: tree recursion

## When to Use
- Problem defined on **left/right subtrees** (height, mirror, invert, diameter).
- Return value to parent summarizes subtree state.
- Build/modify tree recursively.

## Core Idea
- Base: `if (node == null) return baseCase;`
- Recurse: `left = dfs(node.left)`, `right = dfs(node.right);`
- Combine at current node; sometimes pass parent info down.

## Template
```java
int height(TreeNode node) {
    if (node == null) return 0;
    return 1 + Math.max(height(node.left), height(node.right));
}

TreeNode invert(TreeNode node) {
    if (node == null) return null;
    TreeNode L = invert(node.left);
    TreeNode R = invert(node.right);
    node.left = R;
    node.right = L;
    return node;
}
```

## Common Variations
| Return type | Example |
|-------------|---------|
| int (metric) | Depth, count, max |
| boolean | Balanced, symmetric |
| TreeNode | Construct, merge |
| void + global | Collect all paths |

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| DFS tree | O(n) | O(h) |

## Related Patterns
- Tree traversal — order of visit
- Path problems
