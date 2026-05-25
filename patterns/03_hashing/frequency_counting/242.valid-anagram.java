
import java.util.HashMap;

/*
 * @lc app=leetcode id=242 lang=java
 *
 * [242] Valid Anagram
 */

// @lc code=start
class Solution {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int j = 0; j < t.length(); j++) {
            map.put(t.charAt(j), map.getOrDefault(t.charAt(j), 0) - 1);
        }

        for (int val: map.values()) {
            if (val != 0) {
                return false;
            }
        }

        return true;
        
    }
}
// @lc code=end

