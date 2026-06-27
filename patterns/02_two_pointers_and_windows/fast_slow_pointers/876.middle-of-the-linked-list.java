/*
 * @lc app=leetcode id=876 lang=java
 *
 * [876] Middle of the Linked List
 */

// @lc code=start
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}

// Time: O(n)
// Space: O(1)
// @lc code=end

