

/*
 * @lc app=leetcode id=25 lang=java
 *
 * [25] Reverse Nodes in k-Group
 */

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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        while (hasKNodes(head, k)) {
            ListNode oldTail = head;
            ListNode newHead = reverseK(head, k);
            prev.next = newHead;
            prev = oldTail;
            head = oldTail.next;
        }

        return dummy.next;
    }

    private ListNode reverseK(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;
        
        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head.next = curr;
        return prev;
    }

    private boolean hasKNodes(ListNode head, int k) {
        ListNode curr = head;
        for (int i = 0; i < k; i++) {
            if (curr == null) {
                return false;
            }
            curr = curr.next;
        }
        return true;
    }
}
// @lc code=end

// Time: O(n)
// Space: O(1)