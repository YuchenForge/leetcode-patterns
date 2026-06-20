# Pattern: dijkstra graph

## When to Use
- Shortest / cheapest path in a weighted graph.
- Minimum effort / cost to reach a destination.
- Network delay, path with maximum probability, swim in rising water.
- Any problem framed as "process the globally cheapest node next."

## Core Idea
- Min-heap acts as a priority queue. Always expand the cheapest unvisited node, 
relax its neighbors, and push updated costs. Stale entries (old higher-cost paths) 
are skipped with if cost > dist[node], this is lazy deletion built into Dijkstra. 
No need to remove old entries.

Dijkstra requires non-negative edge weights. For negative weights use Bellman-Ford.

## Dijkstra Steps

1. Initialize
    - set distance to start node as 0
    - set other distances to inf
    - mark all nodes as unvisited
2. Select current node
    - pick unvisited node with smallest distance
    - mark it as visited
3. Update neighbors
    - for each neighbor:
        - calculate distance and update it
4. Repeat 
    - repeat 2-3 until all nodes are visited (or reached target)
5. Result
    - produces shortest distance from start to every other node
    - O((V + E) log V) with a binary heap where V is vertices and E is edges

## Template
```java
public int dijkstra(int n, int[][] edges, int src, int dest) {
    // Build adjacency list
    List<List<int[]>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        graph.add(new ArrayList<>());
    }
    
    // edges[i] = [u, v, weight]
    for (int[] edge : edges) {
        graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        graph.get(edge[1]).add(new int[]{edge[0], edge[2]}); // for undirected
    }
    
    // Distance array to track shortest distance to each node
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;
    
    // Priority queue: {distance, node}
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    pq.offer(new int[]{0, src});
    
    while (!pq.isEmpty()) {
        int[] curr = pq.poll();
        int d = curr[0];
        int u = curr[1];
        
        // Skip 
        if (d > dist[u]) continue;
        
        // Explore neighbors
        for (int[] neighbor : graph.get(u)) {
            int v = neighbor[0];
            int weight = neighbor[1];
            
            // Relaxation: update if we found a shorter path
            if (dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;
                pq.offer(new int[]{dist[v], v});
            }
        }
    }
    
    return dist[dest] == Integer.MAX_VALUE ? -1 : dist[dest];
}
```

## Variations
- Standard shortest path: Classic template above
- Minimum effort (matrix): Edge weight = max height diff along path
- Path with max probability: Max-heap, multiply probabilities
- Cheapest flights within K stops: Track (cost, node, stops_remaining)


## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Dijkstra (binary heap) | O((V + E) log V) | O(V + E) |
| Dense graph (adj matrix) | O(V²) with array, O((V+E) log V) with heap | O(V) |

## Related Patterns
- kmerge_two_heaps — same "always pick global min" mechanic, different domain
- top_k_and_ordering — heap mechanics identical, problem framing different

