# Pattern: fast slow pointers

## When to Use
- Cycle Detection: Determining if a Linked List or a cyclic array has a loop
- Middle of the List: Finding the middle element of a Linked List in one pass
- K-th Element: Finding the k-th element from the end or specific "split" points
- Cycle Length/Start: Finding the exact node where a cycle begins or the length of that cycle
- Number Theory Problems 

## Core Idea
- You initialize two pointers (often called slow and fast) at the head of the data structure. 
- In each iteration, the slow pointer moves one step while the fast pointer moves two steps. 
- If there is a cycle, the fast pointer will eventually "lap" the slow pointer and they will meet. 
- If there is no cycle, the fast pointer will reach the end of the structure.

## Template
```java
public boolean hasCycle(ListNode head) {
    if (head == null) return false;

    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;          // Move 1 step
        fast = fast.next.next;     // Move 2 steps

        if (slow == fast) {
            return true; // Cycle detected
        }
    }

    return false; // Fast reached the end, no cycle
}
```

## Common Variations
- Finding the Middle: 
    When fast reaches the end (fast == null or fast.next == null), 
    slow will be exactly at the middle node.
- Finding the Cycle Start: 
    Once a cycle is detected (pointers meet), reset slow to the head. 
    Move both pointers one step at a time; the point where they meet 
    again is the start of the cycle.
- Happy Number: 
    Treating the squares of digits as a sequence; if the sequence 
    enters a loop that doesn't include 1, it’s not a happy number.

## Common Pitfalls
- Null Pointer Exceptions: Forgetting to check while (fast != null && fast.next != null)
- Infinite Loops: Not moving the pointers inside the loop, or incorrect logic 
- Start Position: Ensure both pointers start at the same location (usually head) 

## Related Patterns
- Linkeded List Reversal
- Cycle Sort
