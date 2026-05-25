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

        // store all the characters you need
        HashMap<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        // store all the characters in current window
        HashMap<Character, Integer> window = new HashMap<>();

        // valid when formed == required
        int formed = 0;
        int required = need.size();

        // grow right until it's valid
        for (int right = 0; right < s.length(); right++) {
            char val = s.charAt(right);
            // append current val to window
            window.put(val, window.getOrDefault(val, 0) + 1);

            // if val in both need and window, formed
            if (need.containsKey(val)) {
                if (window.get(val).equals(need.get(val))) {
                    formed++;
                }
            }

            // shrink left when remaining valid
            while (formed == required) {
                // track length m
                int current = right - left + 1;
                if (current < min) {
                    min = current;
                    output = s.substring(left, right+1);
                }

                // remove left char
                char removedVal = s.charAt(left);
                left++;

                // update count
                window.put(removedVal, window.get(removedVal) - 1);
                
                // check when window becomes invalid
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
