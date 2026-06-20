/*
 * @lc app=leetcode id=438 lang=java
 *
 * [438] Find All Anagrams in a String
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        // map index to letters
        int[] pCount = new int[26];
        int[] sCount = new int[26];

        for (int i = 0; i < p.length(); i++) {
            // convert char to index, then increment frequency
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }

        // Check the first window
        if (Arrays.equals(pCount, sCount)) {
            result.add(0);
        }

        // Slide the window across s
        for (int i = p.length(); i < s.length(); i++) {
            // Add the new character (right side)
            sCount[s.charAt(i) - 'a']++;
            // Remove the character that is no longer in the window (left side)
            sCount[s.charAt(i - p.length()) - 'a']--;

            if (Arrays.equals(pCount, sCount)) {
                result.add(i - p.length() + 1);
            }
        }

        return result;
    }
}

// Time: O(n)
// Space: O(1)
// @lc code=end

