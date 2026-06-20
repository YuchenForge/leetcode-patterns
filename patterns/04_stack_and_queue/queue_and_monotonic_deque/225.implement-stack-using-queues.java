/*
 * @lc app=leetcode id=225 lang=java
 *
 * [225] Implement Stack using Queues
 */

import java.util.*;
// @lc code=start
class MyStack {

    private Queue<Integer> queue = new ArrayDeque<>();

    public MyStack() {

    }

    public void push(int x) {
        queue.offer(x);

        int size = queue.size();

        // move previous elements behind x
        for (int i = 0; i < size - 1; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

// Time: O(n) push, O(1) pop/top
// Space: O(n)
// @lc code=end

