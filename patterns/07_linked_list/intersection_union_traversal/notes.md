# Pattern 3: Linked List Insertion & Union

## When to Use
- **Insert at position** — insert node at specific index.
- **Insert in sorted order** — maintain sorted list while inserting.
- **Insert sorted list into another** — merge operation variant.
- **Union two lists** — combine two lists and remove duplicates.
- **Intersection** — find common nodes between two lists.
- **Partition** — reorder nodes into two groups (Dutch flag).

## Template: Insert at Position

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode curr = dummy;

// Move to position before insertion point
for (int i = 0; i < position; i++) {
    curr = curr.next;
}

// Insert new node
ListNode newNode = new ListNode(val);
newNode.next = curr.next;
curr.next = newNode;

return dummy.next;
```

## Template: Partition (Two Groups)

```java
ListNode dummy1 = new ListNode(0);  // For elements < x
ListNode dummy2 = new ListNode(0);  // For elements >= x

ListNode p1 = dummy1, p2 = dummy2;
ListNode curr = head;

while (curr != null) {
    if (curr.val < x) {
        p1.next = curr;
        p1 = p1.next;
    } else {
        p2.next = curr;
        p2 = p2.next;
    }
    curr = curr.next;
}

// IMPORTANT: Cut off tail of second list
p2.next = null;

// Connect groups
p1.next = dummy2.next;
return dummy1.next;
```

## Template: Union (Combine Two Lists + Remove Duplicates)

```java
ListNode dummy = new ListNode(0);
ListNode curr = dummy;
ListNode p1 = list1, p2 = list2;
Set<Integer> seen = new HashSet<>();

// Merge both lists, skip duplicates
while (p1 != null || p2 != null) {
    ListNode node = null;
    
    if (p1 != null && (p2 == null || p1.val <= p2.val)) {
        node = p1;
        p1 = p1.next;
    } else {
        node = p2;
        p2 = p2.next;
    }
    
    // Add if not seen before
    if (!seen.contains(node.val)) {
        seen.add(node.val);
        curr.next = node;
        curr = curr.next;
    }
}

curr.next = null;  // Cut off tail
return dummy.next;
```

## Template: Intersection (Common Nodes)

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) return null;
    
    ListNode pA = headA, pB = headB;
    
    // Two pointers traverse both lists
    // If they intersect, they'll meet at intersection point
    // If not, both will reach null together
    while (pA != pB) {
        pA = (pA == null) ? headB : pA.next;
        pB = (pB == null) ? headA : pB.next;
    }
    
    return pA;  // Either intersection node or null
}
```

## Complexity

| Operation | Time | Space | Notes |
|-----------|------|-------|-------|
| Insert at position | O(n) | O(1) | Find position then insert |
| Partition | O(n) | O(1) | Two dummies |
| Union | O(n + m) | O(n + m) | Hash set for dedup |
| Intersection | O(n + m) | O(1) | Two pointers, no extra space |

## Related Patterns
- two pointers
- fast slow pointers