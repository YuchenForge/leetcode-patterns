# Pattern: bit manipulation

## When to Use
- Powers of two, single number, XOR tricks.
- Subsets via bitmask, count set bits.
- Divide/multiply using shifts (rare).

## Core Idea
- `x & (x-1)` clears lowest set bit.
- `x & -x` isolates lowest set bit.
- XOR: `a^a=0`, `a^0=a`; find unique when pairs duplicate.

## Template
```java
int singleNumber(int[] nums) {
    int x = 0;
    for (int n : nums) x ^= n;
    return x;
}
boolean isPowerOfTwo(int n) {
    return n > 0 && (n & (n - 1)) == 0;
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Single pass bits | O(n) | O(1) |

## Related Patterns
- Hashmap — alternate for frequency
