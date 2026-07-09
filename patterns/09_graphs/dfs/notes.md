# Pattern: DFS

## When to Use
- **Explore all nodes** in component; count islands, connected components.
- **Backtracking** on graphs (paths, cycles) with visited state.
- **Topological sort** via postorder / recursion stack.
- Clone graph, detect cycle in directed graph.

## Core Idea
- Depth-first: Prioritizes going deeper before going wider
- Uses a stack (explicitly or through recursion)
- Time complexity: O(V + E) where V = vertices, E = edges
- Space complexity: O(V) for the recursion stack or explicit stack
- Naturally recursive (though can be iterative)

## Steps
1. Start at a node (mark it as visited)
2. Recursively visit each unvisited neighbor
3. When a neighbor is fully explored (its subtree is done), backtrack to the previous node
4. Continue with the next unvisited neighbor

## Important Edge Cases
- Disconnected graphs — DFS only visits nodes reachable from the starting node. 
For disconnected graphs, run DFS from each - unvisited node to cover the whole graph.
- Cycles — DFS naturally handles cycles since you mark nodes as visited. Without the 
visited check, you'd loop infinitely.
- Self-loops — A node pointing to itself. The visited check prevents infinite recursion.

## Template
```java
void dfsRecursive(int vertex, boolean[] visited) {
    visited[vertex] = true;
    System.out.print(vertex + " ");
    
    for (int adjacent : adjacencyList[vertex]) {
        if (!visited[adjacent]) {
            dfsRecursive(adjacent, visited);  
        }
    }
}

void dfsStart(int start) {
    boolean[] visited = new boolean[vertices];
    dfsRecursive(start, visited);
}
```

```java
void dfsIterative(int startVertex) {
    boolean[] visited = new boolean[vertices];
    Stack<Integer> stack = new Stack<>();  
    
    stack.push(startVertex); 
    visited[startVertex] = true;
    
    while (!stack.isEmpty()) {
        int vertex = stack.pop();  
        System.out.print(vertex + " ");
        
        for (int adjacent : adjacencyList[vertex]) {
            if (!visited[adjacent]) {
                stack.push(adjacent);     
                visited[adjacent] = true;
            }
        }
    }
}
```

## Recursive vs Iterative
The order differs because recursive processes neighbors in order, 
while iterative pushes them in reverse order (LIFO stack behavior).

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| DFS all nodes | O(V+E) | O(V) |
| DFS+memo path | O(V+E) | O(V) |

## Related Patterns
- BFS — shortest unweighted
- Union find — connectivity offline
