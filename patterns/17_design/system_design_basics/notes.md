# Pattern: system design basics

## When to Use
- Interview **OOD + scalability** discussion alongside LC-tagged design problems.
- File system, rate limiter, distributed-style framing (not full system design interview).

## Core Idea (interview framing)
- Clarify: QPS, memory, consistency, eviction policy.
- Start single-machine `HashMap` + structure; then shard by key hash.
- Trade-offs: write-through vs write-back cache, TTL vs LRU.

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Per design | varies | varies |

## Related Patterns
- key_design for cache eviction implementations
