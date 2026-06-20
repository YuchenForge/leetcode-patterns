/*
 * @lc app=leetcode id=49 lang=java
 *
 * [49] Group Anagrams
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str: strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);

            String key = new String(chars);

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        List<List<String>> res = new ArrayList<>();
        for (String k: map.keySet()) {
            res.add(map.get(k));
        }

        return res;

    }
}

// Time: O(n * k log k)
// Space: O(n * k)
// @lc code=end

