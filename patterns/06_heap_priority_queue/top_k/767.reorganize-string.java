/*
 * @lc app=leetcode id=767 lang=java
 *
 * [767] Reorganize String
 */

import java.util.*;

// @lc code=start
class Solution {
    public String reorganizeString(String s) {
        int n = s.length();

        HashMap<Character, Integer> freq = new HashMap<>();
        int maxFreq = 0;

        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            maxFreq = Math.max(maxFreq, freq.get(c));
        }

        if (maxFreq > (n + 1) / 2) {
            return "";
        }

        PriorityQueue<Character> maxHeap =
            new PriorityQueue<>((a, b) -> freq.get(b) - freq.get(a));

        for (char c : freq.keySet()) {
            maxHeap.offer(c);
        }

        StringBuilder result = new StringBuilder();

        Character prev = null;

        while (!maxHeap.isEmpty()) {
            char cur = maxHeap.poll();

            result.append(cur);
            freq.put(cur, freq.get(cur) - 1);

            if (prev != null && freq.get(prev) > 0) {
                maxHeap.offer(prev);
            }

            prev = cur;
        }

        return result.toString();
    }
}

// Time: O(n log n)
// Space: O(n)
// @lc code=end