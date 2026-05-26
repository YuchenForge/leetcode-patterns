# Pattern: data structure design

> Hash + list O(1) designs (LRU, LFU, RandomizedSet) → `03_hashing/key_design`.

## When to Use
- Implement API with stated time bounds — hit counter, snapshot map, time-keyed store.
- Combine primitives: map + heap + list + deque (without duplicating stack/queue/cache notes above).

## Core Idea
- Read problem ops carefully: amortized vs worst-case.
- Use existing Java structures when allowed (`PriorityQueue`, `TreeMap`, `LinkedHashMap`).
- For O(1) delete from middle: **HashMap + doubly linked list** or swap-with-last in `ArrayList`.

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Design ops | per problem | per problem |

## Related Patterns
- key_design — LRU/LFU in `03_hashing/key_design`
- stack/queue — `04_stack_and_queue`
