# Pattern: tree traversal

## When to Use
- Visit **every node** in preorder/inorder/postorder/level order.
- Serialize tree, build from traversal, print structure.
- Foundation for DFS on trees before graph DFS.

## Core Idea
- **DFS**: recursion or explicit stack; order depends when you process node.
- **BFS**: queue by level; track `size` per level for level-order lists.
- Inorder on BST → sorted order.

## Template
```java
void preorder(TreeNode node, List<Integer> out) {
    if (node == null) return;
    out.add(node.val);
    preorder(node.left, out);
    preorder(node.right, out);
}

List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    Queue<TreeNode> q = new ArrayDeque<>();
    q.offer(root);
    while (!q.isEmpty()) {
        int sz = q.size();
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < sz; i++) {
            TreeNode n = q.poll();
            level.add(n.val);
            if (n.left != null) q.offer(n.left);
            if (n.right != null) q.offer(n.right);
        }
        res.add(level);
    }
    return res;
}
```

## Common Variations
| Order | When process node | Use |
|-------|-------------------|-----|
| Preorder | Before children | Copy tree, prefix |
| Inorder | Between L and R | BST sorted |
| Postorder | After children | Delete tree, postfix eval on tree |
| Level | By depth | Zigzag, right-side view |

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| DFS visit all | O(n) | O(h) stack |
| BFS levels | O(n) | O(n) |

## Related Patterns
- Tree recursion — DFS returns
- BST — inorder sorted
