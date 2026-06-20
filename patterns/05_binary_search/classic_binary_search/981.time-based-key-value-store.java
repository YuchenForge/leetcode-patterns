/*
 * @lc app=leetcode id=981 lang=java
 *
 * [981] Time Based Key-Value Store
 */

import java.util.*;

// @lc code=start
class TimeMap {

    // key -> list of [timestamp, value] pairs
    private final HashMap<String, List<Integer>> timestamps;
    private final HashMap<String, List<String>> vals;

    public TimeMap() {
        timestamps = new HashMap<>();
        vals = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        timestamps.computeIfAbsent(key, k -> new ArrayList<>()).add(timestamp);
        vals.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }
    
    public String get(String key, int timestamp) {
        if (!timestamps.containsKey(key)) {
            return "";
        }

        List<Integer> times = timestamps.get(key);
        int lo = 0;
        int hi = times.size() - 1;

        String ans = "";

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (times.get(mid) <= timestamp) {
                ans = vals.get(key).get(mid);
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */

// Time: O(log n)
// Space: O(n)
// @lc code=end

