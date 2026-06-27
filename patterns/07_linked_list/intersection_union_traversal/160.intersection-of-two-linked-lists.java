/*
 * @lc app=leetcode id=160 lang=java
 *
 * [160] Intersection of Two Linked Lists
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            if (pA == null) {
                pA = headB;
            } else {
                pA = pA.next;
            }
            
            if (pB == null) {
                pB = headA;
            } else {
                pB = pB.next;
            }
        }

        return pA;
    }
}
// @lc code=end

// Time: O(M + N)
// Space: O(1)