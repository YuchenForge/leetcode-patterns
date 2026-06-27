# Pattern: tree recursion

## When to Use
- Problem defined on **left/right subtrees** (height, mirror, invert, diameter).
- Return value to parent summarizes subtree state.
- Build/modify tree recursively.

## Core Idea
- Depth: Distance from root
- Height: Distance to deepest leaf
    - Height determines complexity
    - Balanced Tree: O(log n)
    - Skewed Tree: O(n)
- Number of nodes per level: Level k <= 2^k
- Total nodes in perfect binary tree: 2^(h+1) - 1
- Leaves in perfect tree: 2^h
- Full VS Complete VS Perfect
    - Full Binary Tree: Every node has 0-2 children, levels do not have to be full
    - Complete Binary Tree: lowest level can have 1 children, all levels filled from left to right
    - Perfect Binary Tree: All internal nodes have 2 children, leaves at same level, all level filled
- Diameter through node = leftHeight + rightHeight
- Tree Construction:
    - Preorder + Inorder: Preorder first element = root
    - Postorder + Inorder: Postorder last element = root
    - Inorder Alone: Cannot uniquely construct tree
- LCA: if p,q on different sides of root, root is LCA; else recurse one side.

## Template
```java
// Basic Recursion
int dfs(TreeNode root) {
    if (root == null)
        return baseCase;

    int left = dfs(root.left);
    int right = dfs(root.right);

    return combine(left, right);
}
```


## Complexity
| Variant | Time | Space |
|---------|------|-------|
| DFS tree | O(n) | O(h) |

## Related Patterns
- Tree traversal — order of visit
- Path problems
