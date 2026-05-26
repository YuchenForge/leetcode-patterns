# Pattern: union find

## When to Use
- Dynamic **connectivity** — add edges, query if connected.
- Count **connected components**, detect cycle in undirected graph.
- Kruskal MST, redundant connection, accounts merge.

## Core Idea
- Parent array + **path compression** + **union by rank/size**.
- `find(x)` returns root; `union(a,b)` merges sets.

## Template
```java
class UF {
    int[] parent, size;
    UF(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) { parent[i] = i; size[i] = 1; }
    }
    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;
        if (size[ra] < size[rb]) { int t = ra; ra = rb; rb = t; }
        parent[rb] = ra;
        size[ra] += size[rb];
        return true;
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| find/union α(n) | O(α(n)) | O(n) |
| Kruskal | O(E log E) | O(n) |

## Related Patterns
- DFS components
- MST
