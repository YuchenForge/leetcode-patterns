# Pattern: reversal patterns

## When to Use
- Reverse **entire list**, **sublist**, or **k-group**.
- Palindrome linked list — reverse second half.
- In-place reversal required (O(1) extra space).

## Core Idea
- Iterative: three pointers `prev`, `curr`, `next`; `curr.next = prev`.
- Recursive: reverse rest, then `head.next.next = head`.
- Sublist: find `before`, reverse segment, reconnect tails.

## Template
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

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Reverse list | O(n) | O(1) |
| Reverse k-group | O(n) | O(1) |

## Related Patterns
- Pointer manipulation
- fast_slow — find middle
