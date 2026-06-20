# Pattern: basic array tricks

## When to Use
- In-place rearrangement (rotate, move zeroes, dutch flag).
- Index as hash key (first missing positive).
- Insert interval into sorted list of intervals.

## Core Idea
- Prefer O(1) extra space: swap, read/write pointers, cyclic replacement.
- Know Java array ops cold (see snippets below).

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| In-place swap / write | O(n) | O(1) |
| Rotate by reverse | O(n) | O(1) |
| Cycle sort placement | O(n) | O(1) |

# Array Initialization
```java
int[] arr = new int[n];
int[] arr = {1, 2, 3, 4};
int[] arr = new int[]{1, 2, 3};

int[][] grid = new int[m][n];
int[][] grid = {{1,2},{3,4}};
```

# Length
```java
arr.length
grid.length        // rows
grid[0].length     // cols
```

# Traversal
```java
for (int i = 0; i < arr.length; i++) {
    int val = arr[i];
}

for (int num : arr) {
}

for (int i = 0; i < grid.length; i++) {
    for (int j = 0; j < grid[0].length; j++) {
        int val = grid[i][j];
    }
}
```

# In-place Modification
```java
arr[i] = arr[i] + 1;

int tmp = arr[i];
arr[i] = arr[j];
arr[j] = tmp;
```

# Hashmap with Array
```java
Map<Integer, Integer> map = new HashMap<>();

for (int num : arr) {
    map.put(num, map.getOrDefault(num, 0) + 1);
}
```

# Sorting
```java
Arrays.sort(arr);

Arrays.sort(arr, (a, b) -> a - b); // only for Integer[]
```

# Copy Array
```java
int[] copy = Arrays.copyOf(arr, arr.length);
int[] copy = arr.clone();
```

# Filling
```java
Arrays.fill(arr, 0);
Arrays.fill(arr, 1, 5, -1); // [1,5)
```
