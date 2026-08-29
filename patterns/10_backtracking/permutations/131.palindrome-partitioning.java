/*
 * @lc app=leetcode id=131 lang=java
 *
 * [131] Palindrome Partitioning
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();

        backtrack(result, new ArrayList<>(), s, 0);

        return result;
    }

    private void backtrack(List<List<String>> result, 
        List<String> temp, String s, int start) {

            if (start == s.length()) {
                result.add(new ArrayList<>(temp));
                return;
            }

            for (int end = start; end < s.length(); end++) {
                if (!isPalindrome(s, start, end)) {
                    continue;
                }

                temp.add(s.substring(start, end + 1));
                backtrack(result, temp, s, end + 1);
                temp.remove(temp.size() - 1);
            }
        }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
// @lc code=end

// Time: O(n² · 2^n)
// Space: O(n² · 2^n)