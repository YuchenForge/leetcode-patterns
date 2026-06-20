# Pattern 2: Linked List Reverse & Merge Sort

## When to Use
- **Reverse entire list** — iterative or recursive reversal.
- **Reverse between indices** — reverse sublist within range.
- **Reverse in k-groups** — reverse every k nodes.
- **Merge sort on list** — O(n log n) time, O(log n) space (recursion).
- **Reorder list** — find mid, reverse second half, merge.

## Template: Iterative Reversal (Entire List)

```java
ListNode prev = null;
ListNode curr = head;

while (curr != null) {
    ListNode next = curr.next;  // Save next
    curr.next = prev;           // Reverse pointer
    prev = curr;                // Move prev forward
    curr = next;                // Move curr forward
}

return prev;  // New head
```

## Template: Reorder List (find mid → reverse → merge)

```java
public void reorderList(ListNode head) {
    if (head == null || head.next == null) return;
    
    // Find middle
    ListNode slow = head, fast = head;
    ListNode prev = null;
    while (fast != null && fast.next != null) {
        prev = slow;
        slow = slow.next;
        fast = fast.next.next;
    }
    
    // Split into two lists
    if (prev != null) {
        prev.next = null;
    }
    
    // Reverse second half
    ListNode l2 = reverse(slow);
    
    // Merge
    merge(head, l2);
}

private ListNode reverse(ListNode head) {
    ListNode prev = null, curr = head;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}

private void merge(ListNode l1, ListNode l2) {
    while (l1 != null && l2 != null) {
        ListNode next1 = l1.next;
        ListNode next2 = l2.next;
        
        l1.next = l2;
        l2.next = next1;
        
        l1 = next1;
        l2 = next2;
    }
}
```

## Complexity

| Operation | Time | Space | Notes |
|-----------|------|-------|-------|
| Reverse entire | O(n) | O(1) | Iterative |
| Merge sort | O(n log n) | O(log n) | Recursion stack |
| Reorder list | O(n) | O(1) | Find mid + reverse + merge |

## Related Patterns
- two pointers
- fast slow pointers

