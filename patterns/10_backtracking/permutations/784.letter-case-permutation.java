/*
 * @lc app=leetcode id=784 lang=java
 *
 * [784] Letter Case Permutation
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();

        backtrack(result, new StringBuilder(s), 0);

        return result;
    }

    private void backtrack(List<String> result, StringBuilder temp, int index) {
        if (index == temp.length()) {
            result.add(temp.toString());
            return;
        }

        char c = temp.charAt(index);

        if (Character.isLetter(c)) {
            temp.setCharAt(index, Character.toLowerCase(c));
            backtrack(result, temp, index + 1);

            temp.setCharAt(index, Character.toUpperCase(c));
            backtrack(result, temp, index + 1);
        } else {
            backtrack(result, temp, index + 1);
        }

    }
}
// @lc code=end

// Time: O(n · 2^L)
// Space: O(n · 2^L)