/*
 * @lc app=leetcode id=143 lang=java
 *
 * [143] Reorder List
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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode mid = findMid(head);
        ListNode right = reverse(mid.next);
        mid.next = null;

        merge(head, right);
    }

    private ListNode findMid (ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

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
            ListNode temp1 = l1.next;
            ListNode temp2 = l2.next;
            
            l1.next = l2;
            l2.next = temp1;
            
            l1 = temp1;
            l2 = temp2; 
        }
    }
}
// @lc code=end

/**
Step 1: Find the middle
    [1,2,3,4,5] → middle is 3
        
Step 2: Reverse the second half
    [1,2,3] + [4,5]
    [1,2,3] + [5,4] (reversed)
        
Step 3: Merge alternately
    1 → 5 → 2 → 4 → 3
 */


// Time: O(N)
// Space: O(1)
