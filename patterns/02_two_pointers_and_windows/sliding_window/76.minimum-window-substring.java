/*
 * @lc app=leetcode id=76 lang=java
 *
 * [76] Minimum Window Substring
 */

import java.util.*;

// @lc code=start
class Solution {
    public String minWindow(String s, String t) {
        int left = 0;
        String output = "";

        int min = Integer.MAX_VALUE;

        HashMap<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        HashMap<Character, Integer> window = new HashMap<>();

        int formed = 0;
        int required = need.size();

        for (int right = 0; right < s.length(); right++) {
            char val = s.charAt(right);
            window.put(val, window.getOrDefault(val, 0) + 1);

            if (need.containsKey(val)) {
                if (window.get(val).equals(need.get(val))) {
                    formed++;
                }
            }

            while (formed == required) {
                int current = right - left + 1;
                if (current < min) {
                    min = current;
                    output = s.substring(left, right+1);
                }

                char removedVal = s.charAt(left);
                left++;

                window.put(removedVal, window.get(removedVal) - 1);
                
                if (need.containsKey(removedVal)) {
                    if (window.get(removedVal) < need.get(removedVal)) {
                        formed--;
                    }
                }     

            }

        }

        return output;

    }
}
// @lc code=end

// grow right until it's valid

    // valid when for all c, window[c] >= need[c]
    // use counter to check valid
    
// shrink left while remaining valid
