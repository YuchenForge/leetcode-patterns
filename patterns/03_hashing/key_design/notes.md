# Pattern: Hash Design

## When to Use
- Fast insert / delete / lookup needed: Need average O(1) operations.
- LRU Cache, Randomized Set, TimeMap, HashSet/HashMap implementation.
- Key → Value relationships: Store mappings efficiently.
- Need constant-time access to state: Caching, counting, indexing, tracking positions.
- Avoid repeated scanning: Replace O(n) search with O(1) lookup.

## Core Idea
- Use a hash function to map a key to an index (bucket), allowing fast access.

- Instead of scanning the whole array/list:
- Search in array → O(n)
- Search in hash table → O(1) average

- Typical structure:
- key --hash--> bucket/index --> value

- To handle collisions:
- Chaining → linked list / list in each bucket.
- Open Addressing → probe for next empty spot.

```java
// For LeetCode/interviews, Java’s built-in
HashMap<K, V>
HashSet<T>
```

## Template
```java
class MyHashMap {

    static class Pair {
        int key, value;

        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    List<Pair>[] buckets;

    public MyHashMap() {
        buckets = new LinkedList[1000];
        for (int i = 0; i < 1000; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return key % 1000;
    }

    public void put(int key, int value) {
        int idx = hash(key);

        for (Pair pair : buckets[idx]) {
            if (pair.key == key) {
                pair.value = value;
                return;
            }
        }

        buckets[idx].add(new Pair(key, value));
    }

    public int get(int key) {
        int idx = hash(key);

        for (Pair pair : buckets[idx]) {
            if (pair.key == key) {
                return pair.value;
            }
        }

        return -1;
    }

    public void remove(int key) {
        int idx = hash(key);

        buckets[idx].removeIf(pair -> pair.key == key);
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| HashMap ops | O(1)* | O(n) |
| LRU get/put | O(1) | O(capacity) |
| LFU | O(1) | O(n) |

## Related Patterns
- Hashmap basics — simple map problems
- Linked list — DLL pointers for LRU
