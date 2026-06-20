/*
 * @lc app=leetcode id=155 lang=java
 *
 * [155] Min Stack
 */

import java.util.*;

// @lc code=start
class MinStack {

    private final Deque<int[]> stack = new ArrayDeque<>();

    public MinStack() {

    }
    
    public void push(int val) {
        // int min = stack.isEmpty() ? val : Math.min(val, stack.peek()[1]);
        int min;
        if (stack.isEmpty()) {
            min = val;
        } else {
            min = Math.min(val, stack.peek()[1]);
        }
        stack.push(new int[]{val, min});
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek()[0];
    }
    
    public int getMin() {
        return stack.peek()[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

// Time: O(1)
// Space: O(n)
// @lc code=end

