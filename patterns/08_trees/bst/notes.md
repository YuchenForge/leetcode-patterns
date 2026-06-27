# Pattern: BST

## When to Use
- Sorted order, **search/insert/delete** in O(h).
- Validate BST, kth smallest, LCA in BST.
- Range queries, floor/ceiling in tree.

## Core Idea
- Invariant: left < node < right (strict or non-strict per problem).
- Search: go left if target < node, else right.
- In-order traversal: Returns values in sorted order

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

## Time Complexity
- Search O(log n)
- Insert O(log n)
- Delete O(log n)
- Worst Case: All operations O(n) if tree becomes skewed (like a linked list)

## Related Patterns
- Tree traversal — inorder
- Tree recursion
