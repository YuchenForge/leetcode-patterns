# Pattern 1: LinkedList Basics

## When to Use
- **Dummy head** — simplify insert/delete at head (remove nth, merge lists).
- **Merge two sorted lists** — compare and stitch `next`.
- **Partition** — reorder nodes without new nodes (Dutch flag on list).
- **Reorder list** — find mid, reverse second half, merge.
- **Remove nth from end** — two-pass or dummy to handle head removal.
- **Detect/remove cycle** — Floyd's cycle detection (tortoise & hare).

## Core Idea
- Always draw pointers: `prev`, `curr`, `next` before rewiring.
- Dummy: `ListNode dummy = new ListNode(0); dummy.next = head; return dummy.next;`
- Save `next` before changing `curr.next` to avoid losing rest of list.
- **Never lose references** — `ListNode next = curr.next;` BEFORE modifying links.

## Template: Basic Traversal with Modification

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode prev = dummy, curr = head;

while (curr != null) {
    ListNode next = curr.next;  // Save BEFORE modifying
    
    // Modify current node or links
    if (condition) {
        prev.next = curr.next;  // Remove/skip curr
        // OR
        curr.next = ...;        // Rewire curr's next
    }
    
    prev = curr;
    curr = next;
}

return dummy.next;
```


## Template: Remove Nth Node from End

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode fast = dummy, slow = dummy;

// Move fast n+1 steps ahead
for (int i = 0; i <= n; i++) {
    fast = fast.next;
}

// Move both until fast reaches end
while (fast != null) {
    slow = slow.next;
    fast = fast.next;
}

slow.next = slow.next.next;  // Remove nth node
return dummy.next;
```

## Complexity

| Operation | Time | Space | Notes |
|-----------|------|-------|-------|
| Access element at index i | O(n) | O(1) | Must traverse from head |
| Search for value | O(n) | O(1) | Linear search, best O(1) if at head |

| Insert/Delete at head | O(1) | O(1) | Direct pointer update |
| Insert/Delete at tail | O(n) | O(1) | Need to reach end first |
| Insert/Delete at position i | O(n) | O(1) | Find position then insert |

## Related Patterns
- two pointers
- fast slow pointers