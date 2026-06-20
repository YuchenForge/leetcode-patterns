/*
 * @lc app=leetcode id=71 lang=java
 *
 * [71] Simplify Path
 */

import java.util.*;

// @lc code=start
class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque();

        String[] parts = path.split("/");

        for (String part : parts) {
            switch (part) {
                case "", "." -> {
                }
                case ".." -> {
                    if (!stack.isEmpty()) {
                        stack.removeLast();
                    }
                }
                default -> stack.addLast(part);
            }
        }

        StringBuilder res = new StringBuilder();

        for (String dir: stack) {
            res.append("/");
            res.append(dir);
        }

        return res.length() == 0 ? "/" : res.toString();

    }
}

// Time: O(n)
// Space: O(n)
// @lc code=end

