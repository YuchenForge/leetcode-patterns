/*
 * @lc app=leetcode id=1207 lang=java
 *
 * [1207] Unique Number of Occurrences
 */

import java.util.*;

// @lc code=start
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int num: arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        HashSet<Integer> set = new HashSet<>();

        for (int num: freq.keySet()) {
            set.add(freq.get(num));
        }

        return set.size() == freq.size();

    }
}
// @lc code=end

