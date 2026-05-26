# Pattern: topological sort

## When to Use
- **Directed acyclic graph** ordering — prerequisites, build order.
- Detect **cycle** in directed graph (no valid topo).
- Dependency resolution, course schedule.

## Core Idea
- **Kahn (BFS)**: in-degree 0 queue, reduce neighbors, if not all processed → cycle.
- **DFS postorder**: push node after visiting deps; reverse for order.
- Only defined for DAG.

## Template
```java
// Kahn
int[] indeg = new int[n];
for (int[] e : edges) indeg[e[1]]++;
Queue<Integer> q = new ArrayDeque<>();
for (int i = 0; i < n; i++) if (indeg[i] == 0) q.offer(i);
List<Integer> order = new ArrayList<>();
while (!q.isEmpty()) {
    int u = q.poll();
    order.add(u);
    for (int v : adj.get(u)) {
        if (--indeg[v] == 0) q.offer(v);
    }
}
return order.size() == n ? order : empty; // cycle if size < n
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Kahn BFS | O(V+E) | O(V) |
| DFS postorder | O(V+E) | O(V) |

## Related Patterns
- DFS cycle detect
- Course schedule classic
