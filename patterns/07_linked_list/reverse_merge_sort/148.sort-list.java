/*
 * @lc app=leetcode id=148 lang=java
 *
 * [148] Sort List
 */

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        int len = getLength(head);

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        for (int size = 1; size < len; size *= 2) {
            ListNode curr = dummy.next;
            ListNode tail = dummy; // reset tail each loop

            while (curr != null) {
                ListNode left = curr;
                ListNode right = split(left, size);
                curr = split(right, size);

                tail = merge(dummy, tail, left, right);
            }
        }

        return dummy.next;
    }

    /**
     * Splits a list after `n` nodes and returns the head of remaining list
     * If list has <= n nodes, returns null
     */
    private ListNode split(ListNode head, int n) {
        for (int i = 1; i < n && head != null; i++) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        ListNode next = head.next;
        head.next = null;  // Cut the connection
        return next;

    }

    /**
     * Merges two sorted lists and attaches to tail
     * Returns the new tail after merge
     */
    private ListNode merge(ListNode dummy, ListNode tail, 
        ListNode l1, ListNode l2) {

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = (l1 != null) ? l1 : l2;

        while (tail.next != null) {
            tail = tail.next;
        }
        
        return tail;
    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
// @lc code=end

// Time: O(N log N)
// Space: O(1)