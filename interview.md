# Interview Cheat Sheet

OA prep reference. Each entry: **Key idea** + **Template** only.

---

## 01 Arrays & Prefix Sum

### Prefix Sum
**Key idea:** Precompute cumulative sums for O(1) range queries. For subarray-sum = k, track prefix counts in a HashMap and add `count[prefix - k]`. For divisibility by k, group by `prefix % k`.

```java
int[] prefix = new int[n];
prefix[0] = nums[0];
for (int i = 1; i < n; i++) prefix[i] = prefix[i - 1] + nums[i];
int rangeSum = (l == 0) ? prefix[r] : prefix[r] - prefix[l - 1];

// subarray sum = k
Map<Long, Integer> cnt = new HashMap<>();
cnt.put(0L, 1);
long sum = 0, ans = 0;
for (int x : nums) {
    sum += x;
    ans += cnt.getOrDefault(sum - k, 0);
    cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
}
```

### Difference Array
**Key idea:** O(1) range updates via `diff[l] += val`, `diff[r+1] -= val`. Prefix-sum `diff` once to get final array. Inverse of prefix sum.

```java
int[] diff = new int[n + 1];
void rangeAdd(int l, int r, int val) {
    diff[l] += val;
    if (r + 1 < diff.length) diff[r + 1] -= val;
}
int[] build() {
    int[] arr = new int[n];
    int cur = 0;
    for (int i = 0; i < n; i++) { cur += diff[i]; arr[i] = cur; }
    return arr;
}
```

### Basic Array Tricks
**Key idea:** In-place rearrange with swap, read/write pointers, or cyclic replacement — O(1) extra space. Rotate by three reverses; Dutch flag partitions by value.

```java
void rotate(int[] nums, int k) {
    k %= nums.length;
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
}
void reverse(int[] a, int l, int r) {
    while (l < r) { int t = a[l]; a[l++] = a[r]; a[r--] = t; }
}
```

### ArrayList
**Key idea:** Dynamic list for unknown-size collections (BFS adjacency, backtracking results). Use raw arrays for DP/prefix sums. Know `add`, `get`, `set`, `remove`, `Collections.sort`.

```java
List<Integer> list = new ArrayList<>();
List<List<Integer>> grid = new ArrayList<>();
for (int i = 0; i < m; i++) grid.add(new ArrayList<>());
list.add(x); list.get(i); list.set(i, val);
Collections.sort(list);
```

---

## 02 Two Pointers & Windows

### Two Pointers
**Key idea:** Two coordinated indices avoid redundant scans. Opposite ends on sorted arrays for pair-sum; same direction for in-place compaction (remove dupes, move zeroes).

```java
int left = 0, right = arr.length - 1;
while (left < right) {
    int sum = arr[left] + arr[right];
    if (sum == target) { /* found */ break; }
    else if (sum < target) left++;
    else right--;
}
```

### Sliding Window
**Key idea:** Expand `right`, shrink `left` when invalid. Fixed window: add right, drop `right - k`. Exactly K = `atMost(K) - atMost(K-1)`.

```java
// variable window
int left = 0, result = 0;
Map<Integer, Integer> freq = new HashMap<>();
for (int right = 0; right < nums.length; right++) {
    freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);
    while (/* invalid */) {
        int lv = nums[left++];
        if (freq.put(lv, freq.get(lv) - 1) == 1) freq.remove(lv);
    }
    result = Math.max(result, right - left + 1);
}

// fixed window size k
int windowSum = 0;
for (int i = 0; i < k; i++) windowSum += nums[i];
int result = windowSum;
for (int right = k; right < nums.length; right++) {
    windowSum += nums[right] - nums[right - k];
    result = Math.max(result, windowSum);
}
```

### Fast / Slow Pointers
**Key idea:** Slow +1, fast +2. Meet ⇒ cycle. Fast hits null ⇒ no cycle. Cycle start: reset slow to head, both move +1 after meeting.

```java
boolean hasCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next; fast = fast.next.next;
        if (slow == fast) return true;
    }
    return false;
}
// cycle start: after meeting, slow = head; while (slow != fast) { slow = slow.next; fast = fast.next; }
```

---

## 03 Hashing

### HashMap Basics
**Key idea:** O(1) lookup. Three patterns: frequency count, complement lookup (Two Sum), group-by transformed key (anagrams).

```java
Map<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    int need = target - nums[i];
    if (map.containsKey(need)) return new int[]{map.get(need), i};
    map.put(nums[i], i);
}
```

### Frequency Counting
**Key idea:** Build freq map; use for top-K (bucket sort by frequency), anagram checks, or sliding-window char budgets.

```java
Map<Integer, Integer> freq = new HashMap<>();
for (int x : nums) freq.put(x, freq.getOrDefault(x, 0) + 1);

// bucket sort top-K — index = frequency
List<Integer>[] bucket = new ArrayList[nums.length + 1];
for (var e : freq.entrySet()) {
    int c = e.getValue();
    if (bucket[c] == null) bucket[c] = new ArrayList<>();
    bucket[c].add(e.getKey());
}
```

### Key Design
**Key idea:** HashMap + doubly-linked list for LRU (O(1) get/put/evict). Chaining buckets for custom HashMap. ArrayList + HashMap for O(1) insert/delete/getRandom.

```java
// LRU Cache
class LRUCache {
    class Node { int k, v; Node prev, next; Node(int k, int v) { this.k = k; this.v = v; } }
    Map<Integer, Node> map = new HashMap<>();
    Node head = new Node(0, 0), tail = new Node(0, 0);
    int cap;
    LRUCache(int c) { cap = c; head.next = tail; tail.prev = head; }
    void add(Node n) { n.prev = head; n.next = head.next; head.next.prev = n; head.next = n; }
    void remove(Node n) { n.prev.next = n.next; n.next.prev = n.prev; }
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node n = map.get(key); remove(n); add(n); return n.v;
    }
    public void put(int key, int val) {
        if (map.containsKey(key)) { Node n = map.get(key); n.v = val; remove(n); add(n); return; }
        if (map.size() == cap) { map.remove(tail.prev.k); remove(tail.prev); }
        Node n = new Node(key, val); map.put(key, n); add(n);
    }
}
```

---

## 04 Stack & Queue

### Stack Basics
**Key idea:** LIFO for matching/nesting (parentheses), evaluation (RPN), path simplification. Check empty before pop. Use `ArrayDeque`, not `Stack`.

```java
Deque<Character> st = new ArrayDeque<>();
for (char c : s.toCharArray()) {
    if (isOpen(c)) st.push(c);
    else if (isClose(c)) {
        if (st.isEmpty() || !matches(st.pop(), c)) return false;
    }
}
return st.isEmpty();
```

### Monotonic Stack
**Key idea:** Stack of indices in monotonic order. Pop when current breaks order — each popped index gets its answer. O(n) for next greater/smaller, histogram rectangle.

```java
int[] res = new int[n];
Arrays.fill(res, -1);
Deque<Integer> st = new ArrayDeque<>();
for (int i = 0; i < n; i++) {
    while (!st.isEmpty() && nums[st.peek()] < nums[i])
        res[st.pop()] = nums[i];
    st.push(i);
}
```

### Queue & Monotonic Deque
**Key idea:** Queue = FIFO for BFS (mark visited on enqueue). Monotonic deque = sliding-window min/max in O(n): pop dominated back values, expire front indices outside window.

```java
// BFS grid
Queue<int[]> q = new ArrayDeque<>();
boolean[][] seen = new boolean[m][n];
q.offer(new int[]{sr, sc}); seen[sr][sc] = true;
while (!q.isEmpty()) {
    int[] cell = q.poll();
    for (int[] d : dirs) {
        int nr = cell[0] + d[0], nc = cell[1] + d[1];
        if (inBounds(nr, nc) && !seen[nr][nc]) { seen[nr][nc] = true; q.offer(new int[]{nr, nc}); }
    }
}

// sliding window max
Deque<Integer> dq = new ArrayDeque<>();
for (int i = 0; i < nums.length; i++) {
    while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) dq.pollLast();
    dq.offerLast(i);
    if (dq.peekFirst() <= i - k) dq.pollFirst();
    if (i >= k - 1) res[i - k + 1] = nums[dq.peekFirst()];
}
```

---

## 05 Binary Search

### Classic Binary Search
**Key idea:** Shrink `[lo, hi]` on sorted data. Lower bound = first index `>= target` (half-open `[lo, hi)`). Use `mid = lo + (hi - lo) / 2`.

```java
// lower bound: first index with nums[i] >= target
int lo = 0, hi = n;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] < target) lo = mid + 1;
    else hi = mid;
}
return lo;
```

### Binary Search on Answer
**Key idea:** Answer in `[lo, hi]`. Monotonic `feasible(mid)` — if feasible, shrink toward better answer. Minimize-max: `hi = mid`; maximize-min: bias mid up.

```java
int lo = minAns, hi = maxAns;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (feasible(mid)) hi = mid;
    else lo = mid + 1;
}
return lo;
```

### Edge Cases
**Key idea:** Pick one invariant and stick to it — closed `[lo,hi]` vs half-open `[lo,hi)`. Duplicates: leftmost/rightmost bound. Doubles: fixed iterations.

```java
// rightmost index with nums[i] <= target
int lo = 0, hi = n;
while (lo < hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] <= target) lo = mid + 1;
    else hi = mid;
}
return lo - 1;
```

---

## 06 Heap / Priority Queue

### Top K
**Key idea:** Min-heap size K keeps K largest (root = Kth largest). Max-heap for K smallest. Comparator: `(a,b) -> a-b` min-heap, `b-a` max-heap.

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int x : nums) {
    minHeap.offer(x);
    if (minHeap.size() > k) minHeap.poll();
}
return minHeap.peek();
```

### K-Way Merge & Two Heaps
**Key idea:** K-way merge: seed heap with one head per list, pop min, push next from same list. Median: max-heap lower half + min-heap upper half, rebalance sizes.

```java
PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder());
PriorityQueue<Integer> hi = new PriorityQueue<>();
void addNum(int num) {
    lo.offer(num); hi.offer(lo.poll());
    if (lo.size() < hi.size()) lo.offer(hi.poll());
}
double findMedian() {
    return lo.size() > hi.size() ? lo.peek() : (lo.peek() + hi.peek()) / 2.0;
}
```

### Dijkstra & Graph Traversal
**Key idea:** Always expand cheapest unvisited node via min-heap. Relax neighbors; skip stale with `if (d > dist[u]) continue`. Non-negative weights only.

```java
int[] dist = new int[n];
Arrays.fill(dist, Integer.MAX_VALUE);
dist[src] = 0;
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
pq.offer(new int[]{0, src});
while (!pq.isEmpty()) {
    int[] cur = pq.poll();
    if (cur[0] > dist[cur[1]]) continue;
    for (int[] nb : graph.get(cur[1])) {
        int nd = cur[0] + nb[1];
        if (nd < dist[nb[0]]) { dist[nb[0]] = nd; pq.offer(new int[]{nd, nb[0]}); }
    }
}
```

---

## 07 Linked List

### Basics
**Key idea:** Save `next` before rewiring. Dummy head simplifies head deletion. Draw `prev`, `curr`, `next` before any pointer change.

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode prev = dummy, curr = head;
while (curr != null) {
    ListNode next = curr.next;
    if (condition) prev.next = curr.next;
    else prev = curr;
    curr = next;
}
return dummy.next;
```

### Reverse / Merge / Sort
**Key idea:** Iterative reverse: save next, flip `curr.next`, advance. Reorder: find mid → reverse second half → interleave merge.

```java
ListNode reverse(ListNode head) {
    ListNode prev = null, curr = head;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}
```

### Intersection / Union Traversal
**Key idea:** Partition with two dummy lists for `< x` and `>= x`, connect tails. Intersection: two pointers switch lists at end — meet at intersection or both null.

```java
ListNode pA = headA, pB = headB;
while (pA != pB) {
    pA = (pA == null) ? headB : pA.next;
    pB = (pB == null) ? headA : pB.next;
}
return pA;
```

---

## 08 Trees

### Tree Traversal
**Key idea:** DFS: preorder (root first), inorder (BST sorted), postorder (children first). BFS = level order with queue.

```java
void inorder(TreeNode root) {
    if (root == null) return;
    inorder(root.left); visit(root); inorder(root.right);
}
void levelOrder(TreeNode root) {
    if (root == null) return;
    Queue<TreeNode> q = new ArrayDeque<>();
    q.offer(root);
    while (!q.isEmpty()) {
        TreeNode node = q.poll();
        visit(node);
        if (node.left != null) q.offer(node.left);
        if (node.right != null) q.offer(node.right);
    }
}
```

### BST
**Key idea:** Invariant: left < node < right. Search by comparing target. Validate BST with min/max bounds passed down.

```java
TreeNode search(TreeNode root, int target) {
    while (root != null) {
        if (target < root.val) root = root.left;
        else if (target > root.val) root = root.right;
        else return root;
    }
    return null;
}
boolean isValidBST(TreeNode root, long min, long max) {
    if (root == null) return true;
    if (root.val <= min || root.val >= max) return false;
    return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
}
```

### Tree Recursion
**Key idea:** Problem on left/right subtrees — recurse, combine at parent. Height, diameter, mirror, invert, construct from traversals.

```java
int dfs(TreeNode root) {
    if (root == null) return baseCase;
    int left = dfs(root.left), right = dfs(root.right);
    return combine(left, right);
}
```

### Path Problems
**Key idea:** Max path sum: at each node, best downward gain from children; global max = leftGain + val + rightGain. Path sum III: prefix-count HashMap with backtrack on unwind.

```java
int globalMax = Integer.MIN_VALUE;
int maxGain(TreeNode node) {
    if (node == null) return 0;
    int left = Math.max(0, maxGain(node.left));
    int right = Math.max(0, maxGain(node.right));
    globalMax = Math.max(globalMax, left + node.val + right);
    return node.val + Math.max(left, right);
}
```

---

## 09 Graphs

### DFS
**Key idea:** Go deep first. Mark visited on entry. For disconnected graphs, DFS from every unvisited node. O(V+E).

```java
void dfs(int v, boolean[] vis, List<List<Integer>> adj) {
    vis[v] = true;
    for (int nb : adj.get(v))
        if (!vis[nb]) dfs(nb, vis, adj);
}
```

### BFS
**Key idea:** Queue + visited (mark on enqueue). Shortest path in unweighted graphs. Multi-source: enqueue all sources first.

```java
Queue<int[]> q = new ArrayDeque<>();
boolean[] vis = new boolean[n];
q.offer(new int[]{start, 0}); vis[start] = true;
while (!q.isEmpty()) {
    int[] cur = q.poll();
    if (cur[0] == end) return cur[1];
    for (int nb : graph.get(cur[0]))
        if (!vis[nb]) { vis[nb] = true; q.offer(new int[]{nb, cur[1] + 1}); }
}
return -1;
```

### Topological Sort
**Key idea:** Order DAG so u before v for every edge u→v. Kahn's: in-degree-0 queue, decrement neighbors; `result.size() < n` ⇒ cycle.

```java
int[] indeg = new int[n];
for (int u = 0; u < n; u++) for (int v : graph.get(u)) indeg[v]++;
Queue<Integer> q = new ArrayDeque<>();
for (int i = 0; i < n; i++) if (indeg[i] == 0) q.offer(i);
List<Integer> res = new ArrayList<>();
while (!q.isEmpty()) {
    int u = q.poll(); res.add(u);
    for (int v : graph.get(u)) if (--indeg[v] == 0) q.offer(v);
}
return res.size() == n ? res : new ArrayList<>();
```

---

## 10 Backtracking

### Subsets
**Key idea:** At each index, include `nums[i]` or skip (loop from `start`). Record at every node. Skip dupes: sort + skip equal at same depth.

```java
void backtrack(int start, int[] nums, List<Integer> path, List<List<Integer>> res) {
    res.add(new ArrayList<>(path));
    for (int i = start; i < nums.length; i++) {
        path.add(nums[i]);
        backtrack(i + 1, nums, path, res);
        path.remove(path.size() - 1);
    }
}
```

### Permutations
**Key idea:** All orderings — loop from 0, track `used[]` to prevent reuse. Base: `path.size() == nums.length`. Always copy path when recording.

```java
void backtrack(int[] nums, List<Integer> path, boolean[] used, List<List<Integer>> res) {
    if (path.size() == nums.length) { res.add(new ArrayList<>(path)); return; }
    for (int i = 0; i < nums.length; i++) {
        if (used[i]) continue;
        used[i] = true; path.add(nums[i]);
        backtrack(nums, path, used, res);
        path.remove(path.size() - 1); used[i] = false;
    }
}
```

### Constraint Search
**Key idea:** Place candidates on board/grid with early `isValid()` pruning. Mutate in place, recurse, undo on backtrack. N-Queens, Sudoku, generate parentheses.

```java
void backtrack(State state, int slot, List<Result> res) {
    if (slot == totalSlots) { res.add(recordSolution(state)); return; }
    for (Object cand : candidatesFor(slot, state)) {
        if (isValid(cand, slot, state)) {
            place(state, slot, cand);
            backtrack(state, slot + 1, res);
            unplace(state, slot);
        }
    }
}
```

---

## 11 Greedy & Intervals

### Greedy Patterns
**Key idea:** Locally best choice maintains invariant (farthest reach, earliest finish, smallest sufficient resource). If greedy fails, try DP or BS on answer.

```java
// Jump Game — farthest reachable
int reach = 0;
for (int i = 0; i < nums.length; i++) {
    if (i > reach) return false;
    reach = Math.max(reach, i + nums[i]);
}
return true;
```

### Merge Intervals
**Key idea:** Sort by start, merge overlapping by extending end. Minimum arrows: sort by end, greedily place one point per group.

```java
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
List<int[]> merged = new ArrayList<>();
for (int[] iv : intervals) {
    if (merged.isEmpty() || iv[0] > merged.get(merged.size() - 1)[1])
        merged.add(iv.clone());
    else
        merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], iv[1]);
}
```

### Interval Scheduling
**Key idea:** Max non-overlapping: sort by end, greedily pick earliest-finishing compatible interval. Meeting Rooms II: min-heap of end times tracks concurrent meetings.

```java
// max non-overlapping
Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
int count = 0, lastEnd = Integer.MIN_VALUE;
for (int[] iv : intervals)
    if (iv[0] >= lastEnd) { count++; lastEnd = iv[1]; }

// meeting rooms II
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
PriorityQueue<Integer> pq = new PriorityQueue<>();
for (int[] iv : intervals) {
    if (!pq.isEmpty() && pq.peek() <= iv[0]) pq.poll();
    pq.offer(iv[1]);
}
return pq.size();
```

---

## 12 Dynamic Programming

### DP 1D
**Key idea:** `dp[i]` = optimal for prefix ending at i. Transition from previous 1–2 states or all `j < i`. Space-optimize to O(1) when only last k states matter.

```java
int[] dp = new int[n + 1];
dp[0] = base;
for (int i = 1; i <= n; i++)
    dp[i] = Math.max(dp[i - 1], dp[i - 2] + gain(i));
return dp[n];
```

### DP 2D
**Key idea:** `dp[i][j]` for two sequences or grids. Fill boundaries first, interior by neighbors. Rolling array when only previous row needed.

```java
// edit distance
int[][] dp = new int[m + 1][n + 1];
for (int i = 0; i <= m; i++) dp[i][0] = i;
for (int j = 0; j <= n; j++) dp[0][j] = j;
for (int i = 1; i <= m; i++)
    for (int j = 1; j <= n; j++)
        dp[i][j] = s.charAt(i-1) == t.charAt(j-1) ? dp[i-1][j-1]
            : 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
```

### Knapsack
**Key idea:** `dp[w]` = best for capacity w. 0/1: iterate w descending (each item once). Unbounded: w ascending (reuse). Count ways: `dp[w] += dp[w - weight]`.

```java
int[] dp = new int[capacity + 1];
for (int i = 0; i < n; i++)
    for (int w = capacity; w >= weights[i]; w--)
        dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
```

---

## 13 Advanced Graphs

### Shortest Path
**Key idea:** Dijkstra for non-negative weights (heap). Bellman-Ford relaxes all edges V−1 times for negative edges. BFS unweighted; 0-1 BFS with deque for {0,1} weights.

```java
// Bellman-Ford
int[] dist = new int[n];
Arrays.fill(dist, Integer.MAX_VALUE);
dist[src] = 0;
for (int i = 0; i < n - 1; i++)
    for (int[] e : edges)
        if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]])
            dist[e[1]] = dist[e[0]] + e[2];
```

### Union Find
**Key idea:** Dynamic connectivity with path compression + union by size. `find(x)` returns root; `union(a,b)` merges sets, returns false if already connected.

```java
class UF {
    int[] parent, size;
    UF(int n) {
        parent = new int[n]; size = new int[n];
        for (int i = 0; i < n; i++) { parent[i] = i; size[i] = 1; }
    }
    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;
        if (size[ra] < size[rb]) { int t = ra; ra = rb; rb = t; }
        parent[rb] = ra; size[ra] += size[rb];
        return true;
    }
}
```

### Minimum Spanning Tree
**Key idea:** Connect all nodes with minimum total edge weight. Kruskal: sort edges, union-find add if connects different components. Prim: grow tree via min-heap of frontier edges.

```java
Arrays.sort(edges, (a, b) -> a[2] - b[2]);
UF uf = new UF(n);
int cost = 0, used = 0;
for (int[] e : edges)
    if (uf.union(e[0], e[1])) { cost += e[2]; if (++used == n - 1) break; }
```

---

## 14 Advanced DP

### Bitmask DP
**Key idea:** State `(mask, index)` where mask = visited set. For n ≤ 20, iterate masks increasing, add unvisited bits. O(2^n · n²) for TSP-style.

```java
int N = 1 << n;
int[][] dp = new int[N][n];
for (int mask = 0; mask < N; mask++)
    for (int u = 0; u < n; u++) {
        if ((mask & (1 << u)) == 0) continue;
        for (int v = 0; v < n; v++) {
            if ((mask & (1 << v)) != 0) continue;
            int nmask = mask | (1 << v);
            dp[nmask][v] = Math.min(dp[nmask][v], dp[mask][u] + dist[u][v]);
        }
    }
```

### Interval DP
**Key idea:** `dp[i][j]` = optimal on subarray i..j. Fill by increasing length; try every split k to combine `dp[i][k]` + `dp[k+1][j]`.

```java
for (int len = 1; len <= n; len++)
    for (int i = 0; i + len - 1 < n; i++) {
        int j = i + len - 1;
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++)
            dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + cost(i, j, k));
    }
```

### Tree DP
**Key idea:** Postorder — child answers before parent. Often two states: include vs exclude node. Global variable for best path through a node.

```java
int[] rob(TreeNode node) {
    if (node == null) return new int[]{0, 0};
    int[] L = rob(node.left), R = rob(node.right);
    int take = node.val + L[1] + R[1];
    int skip = Math.max(L[0], L[1]) + Math.max(R[0], R[1]);
    return new int[]{take, skip};
}
// answer = max(rob(root)[0], rob(root)[1])
```

---

## 16 Math & Misc

### Bit Manipulation
**Key idea:** `x & (x-1)` clears lowest set bit; `x & -x` isolates it. XOR finds unique when others appear twice. Power of two: `n > 0 && (n & (n-1)) == 0`.

```java
int singleNumber(int[] nums) {
    int x = 0;
    for (int n : nums) x ^= n;
    return x;
}
```

### Combinatorics
**Key idea:** `C(n,k) = C(n-1,k-1) + C(n-1,k)` via Pascal's triangle. Precompute factorials + modular inverse for `nCk mod p`.

```java
long[][] C = new long[n + 1][k + 1];
for (int i = 0; i <= n; i++) {
    C[i][0] = 1;
    for (int j = 1; j <= Math.min(i, k); j++)
        C[i][j] = C[i-1][j-1] + C[i-1][j];
}
```

### Probability
**Key idea:** Prefix sum + binary search for weighted random pick. Reservoir sampling: keep element i with probability 1/i. RandomizedSet: ArrayList + HashMap for O(1) all ops.

```java
class RandomizedSet {
    List<Integer> arr = new ArrayList<>();
    Map<Integer, Integer> idx = new HashMap<>();
    Random rnd = new Random();
    boolean insert(int val) {
        if (idx.containsKey(val)) return false;
        idx.put(val, arr.size()); arr.add(val); return true;
    }
    boolean remove(int val) {
        if (!idx.containsKey(val)) return false;
        int i = idx.remove(val), last = arr.remove(arr.size() - 1);
        if (val != last) { arr.set(i, last); idx.put(last, i); }
        return true;
    }
    int getRandom() { return arr.get(rnd.nextInt(arr.size())); }
}
```

---

## 17 Design

### Data Structure Design
**Key idea:** Combine primitives to meet time bounds. O(1) delete from middle: HashMap + doubly-linked list (LRU) or swap-with-last in ArrayList.

```java
class HitCounter {
    Queue<Integer> q = new ArrayDeque<>();
    public void hit(int ts) { q.offer(ts); }
    public int getHits(int ts) {
        while (!q.isEmpty() && q.peek() <= ts - 300) q.poll();
        return q.size();
    }
}
```

### System Design Basics
**Key idea:** Clarify QPS, memory, consistency, eviction. Single-machine HashMap + structure; shard by key hash. Token bucket for rate limiting.

```java
class RateLimiter {
    double tokens, lastRefill, rate, capacity;
    synchronized boolean allow() {
        refill();
        if (tokens >= 1) { tokens--; return true; }
        return false;
    }
    void refill() {
        long now = System.nanoTime();
        tokens = Math.min(capacity, tokens + (now - lastRefill) * rate / 1e9);
        lastRefill = now;
    }
}
```

---

## 18 Strings

### String Basics
**Key idea:** Strings immutable — use `StringBuilder` for O(n) builds. `char[]` for in-place style. `int[26]` beats HashMap for lowercase ASCII. Two pointers for palindrome.

```java
boolean isPalindrome(String s) {
    int l = 0, r = s.length() - 1;
    while (l < r) {
        while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
        while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
        if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) return false;
        l++; r--;
    }
    return true;
}
```

### Pattern Matching
**Key idea:** KMP uses LPS array to avoid re-scanning. Wildcard/regex: 2D DP on string vs pattern. Rolling hash for O(1) avg substring equality.

```java
// wildcard matching (* and ?)
boolean[][] dp = new boolean[m + 1][n + 1];
dp[0][0] = true;
for (int j = 1; j <= n; j++)
    if (p.charAt(j-1) == '*') dp[0][j] = dp[0][j-1];
for (int i = 1; i <= m; i++)
    for (int j = 1; j <= n; j++) {
        char sc = s.charAt(i-1), pc = p.charAt(j-1);
        if (pc == '*') dp[i][j] = dp[i][j-1] || dp[i-1][j];
        else if (pc == '?' || sc == pc) dp[i][j] = dp[i-1][j-1];
    }
return dp[m][n];
```

### Trie
**Key idea:** Prefix tree with `children[26]` + `end` flag. Insert/search/prefix in O(L). Prunes word-search; bit-trie for max XOR.

```java
class TrieNode { TrieNode[] next = new TrieNode[26]; boolean end; }
class Trie {
    TrieNode root = new TrieNode();
    void insert(String w) {
        TrieNode cur = root;
        for (char c : w.toCharArray()) {
            int i = c - 'a';
            if (cur.next[i] == null) cur.next[i] = new TrieNode();
            cur = cur.next[i];
        }
        cur.end = true;
    }
    boolean searchPrefix(String p) {
        TrieNode cur = root;
        for (char c : p.toCharArray()) {
            if (cur.next[c - 'a'] == null) return false;
            cur = cur.next[c - 'a'];
        }
        return true;
    }
}
```
