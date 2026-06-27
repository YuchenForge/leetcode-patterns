/*
 * @lc app=leetcode id=1019 lang=java
 *
 * [1019] Next Greater Node In Linked List
 */

import java.util.*;

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
    public int[] nextLargerNodes(ListNode head) {
        int len = 0;
        ListNode current = head;
        while (current != null) {
            len++;
            current = current.next; 
        }

        int[] values = new int[len];
        current = head;  // Reset to head
        for (int i = 0; i < len; i++) {
            values[i] = current.val;
            current = current.next;
        }

        int[] res = new int[len];

        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = values.length - 1; i >= 0; i--) { 
            int currentValue = values[i];
            while (!stack.isEmpty() && stack.peek() <= currentValue) {
                stack.pop();
            }

            if (!stack.isEmpty() && stack.peek() > currentValue) {
                res[i] = stack.peek(); 
            } else {
                res[i] = 0;
            }

            stack.push(currentValue);
        }
        
        return res;
    }
}
// @lc code=end

// Time: O(n)
// Space: O(n)