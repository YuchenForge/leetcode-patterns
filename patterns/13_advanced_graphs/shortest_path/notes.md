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
public int bellmanFord(int n, int[][] edges, int src, int dst) {
    // Initialize distances
    int[] distance = new int[n];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[src] = 0;
    
    // Relax edges V-1 times
    for (int i = 0; i < n - 1; i++) {
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            
            // Skip if source is unreachable
            if (distance[u] == Integer.MAX_VALUE) {
                continue;
            }
            
            // Relax edge
            if (distance[u] + weight < distance[v]) {
                distance[v] = distance[u] + weight;
            }
        }
    }
    
    // Return result
    return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
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
