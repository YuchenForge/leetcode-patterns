# Pattern: basic array tricks

# Array Initialization
```java
import java.util.*;

List<Integer> list = new ArrayList<>();
List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
List<List<Integer>> grid = new ArrayList<>();

// initialize 2D ArrayList with m rows
for (int i = 0; i < m; i++) {
grid.add(new ArrayList<>());
}
```

#  Size
```java
list.size()
grid.size()        // rows
grid.get(0).size() // cols
```

# Access Elements
```java
int val = list.get(i);
int val = grid.get(i).get(j);
```

#  Modify Elements
```java
list.set(i, 10);
grid.get(i).set(j, 10);
```

# Add Elements
```java
list.add(10);        // append
list.add(i, 10);     // insert at index
```

# Remove Elements
```java
list.remove(i);                    // remove by index
list.remove(Integer.valueOf(10));  // remove by value
```

# Traversal

### Index-based
```java
for (int i = 0; i < list.size(); i++) {
    int val = list.get(i);
}
```
### Enhanced for-loop
```java
for (int num : list) {
}
```
### 2D traversal
```java
for (int i = 0; i < grid.size(); i++) {
    for (int j = 0; j < grid.get(i).size(); j++) {
        int val = grid.get(i).get(j);
    }
}
```

# Swap Elements
```java
int tmp = list.get(i);
list.set(i, list.get(j));
list.set(j, tmp);
```

# Sorting

```java
Collections.sort(list);
```
### Descending order:
```java
Collections.sort(list, (a, b) -> b - a);
```

- Works for `List<Integer>`, not primitive arrays.

# Copy ArrayList
```java
List<Integer> copy = new ArrayList<>(list);
```

# Fill / Initialize Values

### Fill existing list
```java
Collections.fill(list, 0);
```
### Create filled list (better)
```java
List<Integer> list = new ArrayList<>(Collections.nCopies(n, 0));
```

# Convert Between Array and ArrayList

### Array → List
```java
List<Integer> list = Arrays.asList(1, 2, 3);
```
### Array → mutable List
```java
List<Integer> list = new ArrayList<>();
for (int x : arr) list.add(x);
```
### List → Array
```java
int[] arr = new int[list.size()];
for (int i = 0; i < list.size(); i++) {
    arr[i] = list.get(i);
}
```

# HashMap with ArrayList
```java
Map<Integer, Integer> map = new HashMap<>();

for (int num : list) {
    map.put(num, map.getOrDefault(num, 0) + 1);
}
```

# Key Differences vs Array

| Feature | Array      | ArrayList       |
| ------- | ---------- | --------------- |
| Size    | Fixed      | Dynamic         |
| Access  | arr[i]     | get(i)          |
| Modify  | arr[i] = x | set(i, x)       |
| Add     | ❌          | add(x)          |
| Remove  | ❌          | remove(i/x)     |
| Speed   | Faster     | Slight overhead |

# When to Use ArrayList

* size changes dynamically
* frequent insert/remove needed
* BFS / graph adjacency list
* building result lists (backtracking)

# When NOT to Use ArrayList

* heavy numeric computation
* prefix sum / DP / sliding window
* performance-critical indexing problems
  -> Use arrays instead

