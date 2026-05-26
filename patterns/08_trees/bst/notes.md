# Pattern: BST

## When to Use
- Sorted order, **search/insert/delete** in O(h).
- Validate BST, kth smallest, LCA in BST.
- Range queries, floor/ceiling in tree.

## Core Idea
- Invariant: left < node < right (strict or non-strict per problem).
- Search: go left if target < node, else right.
- Inorder traversal yields sorted sequence.

## Template
```java
TreeNode search(TreeNode root, int target) {
    while (root != null) {
        if (target < root.val) root = root.left;
        else if (target > root.val) root = root.right;
        else return root;
    }
    return null;
}

boolean isValidBST(TreeNode root, long min, long max) {
    if (root == null) return true;
    if (root.val <= min || root.val >= max) return false;
    return isValidBST(root.left, min, root.val)
        && isValidBST(root.right, root.val, max);
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| BST search | O(h) | O(1) |
| Validate | O(n) | O(h) |

## Related Patterns
- Tree traversal — inorder
- Tree recursion
