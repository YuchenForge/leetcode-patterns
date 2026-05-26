# Pattern: path problems

## When to Use
- **Root-to-leaf** path sum, count paths with sum K.
- **Any-to-any** path (may not pass root) — diameter, max path sum.
- **Lowest common ancestor** — path or parent pointers.

## Core Idea
- DFS with **running sum** + hashmap of prefix counts (like array prefix + map).
- Max path sum: at each node, best path through node = leftGain + val + rightGain; global max.
- LCA: if p,q on different sides of root, root is LCA; else recurse one side.

## Template
```java
int maxGain(TreeNode node) {
    if (node == null) return 0;
    int left = Math.max(0, maxGain(node.left));
    int right = Math.max(0, maxGain(node.right));
    globalMax = Math.max(globalMax, left + node.val + right);
    return node.val + Math.max(left, right);
}
```

```java
// Path sum III — prefix count on tree
void dfs(TreeNode node, long sum, Map<Long, Integer> cnt) {
    if (node == null) return;
    sum += node.val;
    ans += cnt.getOrDefault(sum - target, 0);
    cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
    dfs(node.left, sum, cnt);
    dfs(node.right, sum, cnt);
    cnt.put(sum, cnt.get(sum) - 1); // backtrack
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Root-to-leaf DFS | O(n) | O(h) |
| Path sum III prefix | O(n) | O(n) |

## Related Patterns
- Prefix sum — same idea on tree
- Tree DP — max path `14_advanced_dp/tree_dp`
