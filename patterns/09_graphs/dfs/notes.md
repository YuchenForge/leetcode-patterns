# Pattern: DFS

## When to Use
- **Explore all nodes** in component; count islands, connected components.
- **Backtracking** on graphs (paths, cycles) with visited state.
- **Topological sort** via postorder / recursion stack.
- Clone graph, detect cycle in directed graph.

## Core Idea
- Recursive DFS or stack; `visited` prevents cycles.
- For directed cycle: use **recursion stack** (gray/black) or in-degree Kahn.
- Postorder: process children before node (topo, delete tree).

## Template
```java
void dfs(int u, List<List<Integer>> adj, boolean[] vis) {
    vis[u] = true;
    for (int v : adj.get(u)) {
        if (!vis[v]) dfs(v, adj, vis);
    }
}
```

```java
// Grid DFS
void dfs(int r, int c, char[][] grid) {
    if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == '0') return;
    grid[r][c] = '0';
    dfs(r+1,c,grid); dfs(r-1,c,grid); dfs(r,c+1,grid); dfs(r,c-1,grid);
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| DFS all nodes | O(V+E) | O(V) |
| DFS+memo path | O(V+E) | O(V) |

## Related Patterns
- BFS — shortest unweighted
- Union find — connectivity offline
