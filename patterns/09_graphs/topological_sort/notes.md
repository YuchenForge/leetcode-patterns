# Pattern: topological sort

## When to Use
- **Directed acyclic graph** ordering — prerequisites, build order.
- Detect **cycle** in directed graph (no valid topo).
- Dependency resolution, course schedule, task schedling with dependencies

## Core Idea
- Topological sort orders vertices in a directed acyclic graph (DAG) (have directions, no cycle) so that for every edge:
u -> v, u comes before v
- **Kahn (BFS)**: in-degree 0 queue, reduce neighbors, if not all processed → cycle.
- **DFS postorder**: push node after visiting deps; reverse for order.
- Only defined for DAG.

## Detect Cycle
- If Kahn's returns empty list when result.size() < n, there's a cycle
- For DFS: use 3 colors (white/gray/black) like cycle detection

## Template
```java
// Kahn's Algorithm - BFS approach
List<Integer> topologicalSort(int n, List<List<Integer>> graph) {
    int[] inDegree = new int[n];
    List<Integer> result = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    
    // Calculate in-degrees (number of incoming edges)
    for (int i = 0; i < n; i++) {
        for (int neighbor : graph[i]) {
            inDegree[neighbor]++;
        }
    }
    
    // Add all nodes with in-degree 0 to queue
    for (int i = 0; i < n; i++) {
        if (inDegree[i] == 0) {
            queue.add(i);
        }
    }
    
    // Process nodes
    while (!queue.isEmpty()) {
        int node = queue.poll();
        result.add(node);
        
        for (int neighbor : graph[node]) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] == 0) {
                queue.add(neighbor);
            }
        }
    }
    
    // Empty if cycle exists
    return result.size() == n ? result : new ArrayList<>();  
}
```

```java
// DFS approach - Add nodes AFTER exploring children
List<Integer> topologicalSort(int n, List<List<Integer>> graph) {
    boolean[] visited = new boolean[n];
    Stack<Integer> stack = new Stack<>();
    
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            dfs(i, graph, visited, stack);
        }
    }
    
    List<Integer> result = new ArrayList<>();
    while (!stack.isEmpty()) {
        result.add(stack.pop());
    }
    return result;
}

void dfs(int node, List<List<Integer>> graph, boolean[] visited, Stack<Integer> stack) {
    visited[node] = true;
    
    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            dfs(neighbor, graph, visited, stack);
        }
    }
    
    stack.push(node);  // Push AFTER exploring all neighbors
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Kahn BFS | O(V+E) | O(V) |
| DFS postorder | O(V+E) | O(V) |

## Related Patterns
- DFS cycle detect
- Course schedule classic
