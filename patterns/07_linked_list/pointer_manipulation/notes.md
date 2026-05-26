# Pattern: pointer manipulation

## When to Use
- **Dummy head** — simplify insert/delete at head (remove nth, merge lists).
- **Merge two sorted lists** — compare and stitch `next`.
- **Partition** — reorder nodes without new nodes (Dutch flag on list).
- **Reorder list** — find mid, reverse second half, merge.

## Core Idea
- Always draw pointers: `prev`, `curr`, `next` before rewiring.
- Dummy: `ListNode dummy = new ListNode(0); dummy.next = head; return dummy.next;`
- Save `next` before changing `curr.next` to avoid losing rest of list.

## Template
```java
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode prev = dummy, curr = head;

while (curr != null) {
    ListNode next = curr.next;
    // modify links
    curr.next = ...;
    prev = curr;
    curr = next;
}
return dummy.next;
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Single pass | O(n) | O(1) |
| Merge lists | O(n+m) | O(1) |

## Related Patterns
- Reversal patterns
- fast_slow_pointers — gap n, middle
