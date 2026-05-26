# Pattern: minimum spanning tree

## When to Use
- Connect all nodes with **minimum total edge weight** (undirected).
- Clustering, network design, offline connectivity cost.

## Core Idea
- **Kruskal**: sort edges, union-find add if connects different sets.
- **Prim**: grow tree from start, min-heap of frontier edges.
- MST exists for connected undirected graphs.

## Template
```java
// Kruskal
Arrays.sort(edges, (a,b) -> a[2] - b[2]);
UF uf = new UF(n);
int cost = 0, used = 0;
for (int[] e : edges) {
    if (uf.union(e[0], e[1])) {
        cost += e[2];
        if (++used == n - 1) break;
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Kruskal | O(E log E) | O(n) |
| Prim | O(E log V) | O(V) |

## Related Patterns
- Union find — Kruskal core
- Shortest path — different objective
