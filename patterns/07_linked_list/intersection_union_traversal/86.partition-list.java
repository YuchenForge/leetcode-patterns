/*
 * @lc app=leetcode id=86 lang=java
 *
 * [86] Partition List
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
    public ListNode partition(ListNode head, int x) {
        ListNode dummy_less = new ListNode(0);     
        ListNode dummy_greater = new ListNode(0);   

        ListNode less = dummy_less;
        ListNode more = dummy_greater;
        ListNode curr = head;

        while (curr != null) {
            if (curr.val < x) {
                less.next = curr;
                less = less.next;
            } else {
                more.next = curr;
                more = more.next;
            }

            curr = curr.next;
        }

        more.next = null;
        less.next = dummy_greater.next;

        return dummy_less.next;
    }
}
// @lc code=end

// Time: O(n)
// Space O(1)
