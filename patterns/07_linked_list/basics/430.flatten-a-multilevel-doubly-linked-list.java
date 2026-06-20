/*
 * @lc app=leetcode id=430 lang=java
 *
 * [430] Flatten a Multilevel Doubly Linked List
 */

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        Node curr = head;

        while (curr != null) {
            if (curr.child != null) {
                Node tail = getTail(curr.child);

                Node next = curr.next;
                curr.next = curr.child;
                curr.child.prev = curr;

                if (next != null) {
                    tail.next = next;
                    next.prev = tail;
                }

                curr.child = null;
            }

            curr = curr.next;
        }

        return head;
    }

    private Node getTail(Node head) {
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }
}
// @lc code=end

// Time: O(N)
// Space: O(1)