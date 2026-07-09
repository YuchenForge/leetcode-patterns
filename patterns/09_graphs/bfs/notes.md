# Pattern: BFS

## When to Use
- Shortest path — Finding shortest distance in unweighted graphs (mazes, social networks)
- Level-order traversal — Processing tree/graph by levels
- Connected components — Same as DFS but with queue instead
- 0/1 Matrix — Finding distance to nearest 0 in binary matrix
- Word ladder — Fewest steps to transform one word to another

## Core Idea
- Queue + visited set; distance = number of dequeue layers (or store in queue).
- Grid: 4/8 directions; mark visited on **enqueue**.
- Multi-source: push all sources first with distance 0.
- Time: O(V + E), Space: O(V) for queue

## Steps
1. Start at a node, add it to queue
2. Dequeue the front node, mark as visited
3. Enqueue all unvisited neighbors
4. Repeat until queue is empty

## Template
```java
// Same as dfs but use queue
void bfs(int startVertex) {
    boolean[] visited = new boolean[vertices];
    Queue<Integer> queue = new LinkedList<>(); 
    
    queue.add(startVertex);         
    visited[startVertex] = true;
    
    while (!queue.isEmpty()) {
        int vertex = queue.poll();          
        System.out.print(vertex + " ");
        
        for (int adjacent : adjacencyList[vertex]) {
            if (!visited[adjacent]) {
                queue.add(adjacent); 
                visited[adjacent] = true;
            }
        }
    }
}
```

```java
int shortestPath(int start, int end, boolean[] visited) {
    Queue<int[]> queue = new LinkedList<>();  // [node, distance]
    queue.add(new int[]{start, 0});
    visited[start] = true;
    
    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int node = current[0];
        int dist = current[1];
        
        if (node == end) {
            return dist;
        }
        
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.add(new int[]{neighbor, dist + 1});
            }
        }
    }
    return -1;  // No path found
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
