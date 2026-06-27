/*
 * @lc app=leetcode id=92 lang=java
 *
 * [92] Reverse Linked List II
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Find the node 
        ListNode leftPrev = dummy;
        for (int i = 0; i < left - 1; i++) {
            leftPrev = leftPrev.next;
        }
        
        // Reverse nodes between left and right
        ListNode prev = leftPrev;
        ListNode curr = leftPrev.next;
        
        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next; 
            curr.next = next.next; 
            next.next = prev.next;  
            prev.next = next;  
        }
        
        return dummy.next;
    }
}
// @lc code=end

// Time: O(N)
// Space: O(1)
