/*
 * @lc app=leetcode id=17 lang=java
 *
 * [17] Letter Combinations of a Phone Number
 */

import java.util.*;

// @lc code=start
class Solution {
    private HashMap<Character, char[]> phone = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        phone.put('2', new char[] {'a', 'b', 'c'});
        phone.put('3', new char[] {'d', 'e', 'f'});
        phone.put('4', new char[] {'g', 'h', 'i'});
        phone.put('5', new char[] {'j', 'k', 'l'});
        phone.put('6', new char[] {'m', 'n', 'o'});
        phone.put('7', new char[] {'p', 'q', 'r', 's'});
        phone.put('8', new char[] {'t', 'u', 'v'});
        phone.put('9', new char[] {'w', 'x', 'y', 'z'});

        List<String> result = new ArrayList<>();
        permutations(result, digits, new StringBuilder(), 0);
        return result;
        
    }

    private void permutations(List<String> result, String digits, StringBuilder temp, int index) {
        if (temp.length() == digits.length()) {
            result.add(temp.toString());
            return;
        }

        for (char c: phone.get(digits.charAt(index))) {
            temp.append(c);
            permutations(result, digits, temp, index + 1);
            temp.deleteCharAt(temp.length() - 1);
        }

    }
}
// @lc code=end

// Time: O(n * 4^n)
// Space: O(n * 4^n)