/*
 * @lc app=leetcode id=30 lang=java
 *
 * [30] Substring with Concatenation of All Words
 */

import java.util.*;

// @lc code=start
@SuppressWarnings("unused")
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        HashMap<String, Integer> freq = new HashMap<>();

        int wordLength = words[0].length();

        // store words frequency
        for (String word: words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        
        List<Integer> res = new ArrayList<>();

        for (int offset = 0; offset < wordLength; offset++) {
            int left = offset;
            int count = 0;

            // store seen frequency
            HashMap<String, Integer> seen = new HashMap<>();

            for (int right = offset; 
                    right + wordLength <= s.length(); 
                    right += wordLength) {

                String chunk = s.substring(right, right + wordLength);

                if (!freq.containsKey(chunk)) {
                    seen.clear();
                    count = 0;
                    left = right + wordLength;
                    continue;
                }

                seen.put(chunk, seen.getOrDefault(chunk, 0) + 1);
                count++;

                // shrink left until it's valid
                while (seen.get(chunk) > freq.get(chunk)) {
                    String leftWord = s.substring(left, left + wordLength);
                    seen.put(leftWord, seen.get(leftWord) - 1);
                    left += wordLength;
                    count--;
                }

                if (count == words.length) {
                    res.add(left);

                    String leftWord = s.substring(left, left + wordLength);
                    seen.put(leftWord, seen.get(leftWord) - 1);
                    left += wordLength;
                    count--;
                }
            }

        }

        return res;
    }
}

// Time: O(n * m)
// Space: O(w)
// @lc code=end

// brute force solution (Time limit exceeded)

@SuppressWarnings("unused")
class BruteForceSolution {
    public List<Integer> findSubstring(String s, String[] words) {
        HashMap<String, Integer> freq = new HashMap<>();
        int wordLength = words[0].length();
        int concateLength = wordLength * words.length;

        for (String word: words) {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i <= s.length() - concateLength; i++) {
            boolean valid = true;

            HashMap<String, Integer> seen = new HashMap<>();

            for (int j = 0; j < concateLength; j += wordLength) {
                String chunk = s.substring(i + j, i + j + wordLength);

                if (!freq.containsKey(chunk)) {
                    valid = false;
                    break;
                }

                seen.put(chunk, seen.getOrDefault(chunk, 0) + 1);

                if (seen.get(chunk) > freq.get(chunk)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                res.add(i);
            }


        }

        return res;

    }
}

