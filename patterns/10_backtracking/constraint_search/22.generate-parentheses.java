/*
 * @lc app=leetcode id=22 lang=java
 *
 * [22] Generate Parentheses
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> res, 
        StringBuilder current, int open, int close, int max) {
        
        // Base case
        if (current.length() == max * 2) {
            res.add(current.toString());
            return;
        }

        if (open < max) {
            current.append('(');
            backtrack(res, current, open + 1, close, max);
            // backtrack
            current.deleteCharAt(current.length() - 1);
        }

        if (close < open) {
            current.append(')');
            backtrack(res, current, open, close + 1, max);
            // backtrack
            current.deleteCharAt(current.length() - 1);
        }
    }
}
// @lc code=end

// Time: O(4ⁿ / √n)