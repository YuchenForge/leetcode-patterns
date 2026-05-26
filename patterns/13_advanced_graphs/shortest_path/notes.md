# Pattern: shortest path

## When to Use
- **Weighted** edges — Dijkstra (non-negative), Bellman-Ford (negative OK, small n).
- **Unweighted** — BFS (see graphs/bfs).
- Grid with obstacles — 0-1 BFS or Dijkstra.

## Core Idea
- Dijkstra: min-heap of `(dist, node)`; relax neighbors if shorter.
- Bellman-Ford: relax all edges V-1 times.
- Floyd-Warshall: all pairs O(n³) — rare in interviews.

## Template
```java
int[] dist = new int[n];
Arrays.fill(dist, Integer.MAX_VALUE);
dist[src] = 0;
PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
pq.offer(new int[]{src, 0});
while (!pq.isEmpty()) {
    int[] cur = pq.poll();
    int u = cur[0], d = cur[1];
    if (d > dist[u]) continue;
    for (int[] e : adj.get(u)) {
        int v = e[0], w = e[1];
        if (dist[u] + w < dist[v]) {
            dist[v] = dist[u] + w;
            pq.offer(new int[]{v, dist[v]});
        }
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Dijkstra | O((V+E) log V) | O(V) |
| Bellman-Ford | O(VE) | O(V) |

## Related Patterns
- BFS — weight 1
- 0-1 BFS deque
