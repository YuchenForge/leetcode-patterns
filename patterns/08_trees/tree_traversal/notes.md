# Pattern: tree traversal

## When to Use
- Visit **every node** in preorder/inorder/postorder/level order.
- Serialize tree, build from traversal, print structure.
- Foundation for DFS on trees before graph DFS.

## Core Idea
- DFS Traversal
    - **Preorder Traversal**: (Root → Left → Right)
    - **Inorder Traversal**: (Left → Root → Right)
    - **Postorder Traversal**: (Left → Right → Root)
    - Time complexity: O(m) each node visited exactly once
    - Space complexity: O(h) where h is height of tree
- BFS Traversal
    - **Level Order Traversal**: Process level by level
    - Time complexity: O(n)
    - Space complexity: O(w) where w is maximum width

## Template
```java
// Tree Basics
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}
```

```java
// Preorder Recursive: Root → Left → Right
    public void preorder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        preorder(node.left);
        preorder(node.right);
    }
    
    // Inorder Recursive: Left → Root → Right
    public void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }
    
    // Postorder Recursive: Left → Right → Root
    public void postorder(TreeNode node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.val + " ");
    }
```

```java
// Preorder iterative
public void preorderIterative(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    if (root == null) return;
    
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        System.out.print(node.val + " ");
        if (node.right != null) stack.push(node.right);
        if (node.left != null) stack.push(node.left);
    }
}

// Inorder iterative
public void inorderIterative(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;
    
    while (current != null || !stack.isEmpty()) {
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        current = stack.pop();
        System.out.print(current.val + " ");
        current = current.right;
    }
}

// Postorder iterative 
public void postorderIterative(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;
    TreeNode lastVisited = null;
    
    while (current != null || !stack.isEmpty()) {
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        TreeNode peek = stack.peek();
        if (peek.right != null && lastVisited != peek.right) {
            current = peek.right;
        } else {
            System.out.print(peek.val + " ");
            lastVisited = stack.pop();
        }
    }
}
```

```java
public void levelOrder(TreeNode root) {
    if (root == null) return;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    
    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        System.out.print(node.val + " ");
        
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
    }
}
```

## Common Variations
| Order | When process node | Use |
|-------|-------------------|-----|
| Preorder | Before children | Copy tree, prefix |
| Inorder | Between L and R | BST sorted |
| Postorder | After children | Delete tree, postfix eval on tree |
| Level (BFS) | By depth | Zigzag, right-side view |

## Complexity
| Traversal | Approach | Time | Space | Notes |
|-----------|----------|------|-------|-------|
| **Preorder** | Recursive | O(n) | O(h) | Visit → Left → Right |
| **Preorder** | Iterative | O(n) | O(h) | Single stack |
| **Inorder** | Recursive | O(n) | O(h) | Left → Visit → Right |
| **Inorder** | Iterative | O(n) | O(h) | Stack + flag |
| **Postorder** | Recursive | O(n) | O(h) | Left → Right → Visit |
| **Postorder** | Iterative | O(n) | O(h) | Dual stack / one stack + set |

## Related Patterns
- Tree recursion — DFS returns
- BST — inorder sorted
