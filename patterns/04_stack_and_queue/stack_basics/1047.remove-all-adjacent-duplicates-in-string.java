/*
 * @lc app=leetcode id=1047 lang=java
 *
 * [1047] Remove All Adjacent Duplicates In String
 */

import java.util.*;

// @lc code=start
class Solution {
    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c: s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().equals(c)) {
                stack.pop();
                continue;
            }
            stack.push(c);
        }

        StringBuilder res = new StringBuilder();
        for (char c: stack) {
            res.append(c);
        }

        return res.reverse().toString(); 
    }
}

// Time: O(n)
// Space: O(n)
// @lc code=end

