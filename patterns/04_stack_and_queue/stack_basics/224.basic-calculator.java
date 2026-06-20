/*
 * @lc app=leetcode id=224 lang=java
 *
 * [224] Basic Calculator
 */

import java.util.*;

// @lc code=start
class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0;
        int sign = 1;

        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i += 1;
                }
                result += num * sign;
            } else if (c == '+') {
                sign = 1;
                i++;
            } else if (c == '-') {
                sign = -1;
                i++;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
                i++;
            } else if (c == ')') {
                result = stack.pop() * result + stack.pop();
                i++;
            } else {
                i++;
            }

        }

        return result;
    }
}

// Time: O(n)
// Space: O(n)
// @lc code=end

