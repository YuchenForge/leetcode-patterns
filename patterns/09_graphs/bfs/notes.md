# Pattern: BFS

## When to Use
- **Shortest path** in unweighted graph/grid (fewest steps/edges).
- **Level-by-level** expansion (word ladder, rotten oranges).
- When state space is small and you need minimum moves.

## Core Idea
- Queue + visited set; distance = number of dequeue layers (or store in queue).
- Grid: 4/8 directions; mark visited on **enqueue**.
- Multi-source: push all sources first with distance 0.

## Template
```java
int bfs(int start, Map<Integer, List<Integer>> graph) {
    Queue<Integer> q = new ArrayDeque<>();
    Set<Integer> seen = new HashSet<>();
    q.offer(start);
    seen.add(start);
    int dist = 0;
    while (!q.isEmpty()) {
        int sz = q.size();
        for (int i = 0; i < sz; i++) {
            int u = q.poll();
            if (u == target) return dist;
            for (int v : graph.getOrDefault(u, List.of())) {
                if (!seen.contains(v)) {
                    seen.add(v);
                    q.offer(v);
                }
            }
        }
        dist++;
    }
    return -1;
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| BFS unweighted | O(V+E) | O(V) |
| Grid BFS | O(mn) | O(mn) |

## Related Patterns
- DFS — components/explore
- Topological sort — DAG order
