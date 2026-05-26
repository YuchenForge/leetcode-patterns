# Pattern: probability

## When to Use
- Random pick with weight, reservoir sampling.
- Expected value, probability DP on dice/cards.
- **Design** — RandomizedSet, RandPick.

## Core Idea
- **Prefix sum + binary search** for weighted random.
- **Reservoir sampling**: stream of unknown size, each element 1/i chance to be kept.
- Use `Random` in Java; `nextInt(bound)` for [0, bound).

## Template
```java
class RandomizedSet {
    List<Integer> arr = new ArrayList<>();
    Map<Integer, Integer> idx = new HashMap<>();
    Random rnd = new Random();
    boolean insert(int val) {
        if (idx.containsKey(val)) return false;
        idx.put(val, arr.size());
        arr.add(val);
        return true;
    }
    int getRandom() {
        return arr.get(rnd.nextInt(arr.size()));
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Reservoir | O(n) | O(1) |
| Weighted pick | O(log n) | O(n) |

## Related Patterns
- key_design randomized structures
