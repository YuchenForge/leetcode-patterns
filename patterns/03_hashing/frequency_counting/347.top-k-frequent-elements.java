/*
 * @lc app=leetcode id=347 lang=java
 *
 * [347] Top K Frequent Elements
 */

import java.util.*;

// @lc code=start
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> counts = new HashMap<>();

        for (int num: nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // Bucket: index = frequency
        List<Integer>[] bucket = new ArrayList[nums.length+1];

        for (int num: counts.keySet()) {
            int freq = counts.get(num);
            
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }

            bucket[freq].add(num);
        }

        int[] result = new int[k];
        int index = 0;

        // Traverse buckets
        for (int freq = bucket.length-1; freq >= 0 && index < k; freq--) {
            if (bucket[freq] != null) {
                for (int num: bucket[freq]) {
                    result[index] = num;
                    index++;

                    if (index == k) {
                        break;
                    }
                }
            }
        }

        return result;

    }
}

// Time: O(n)
// Space: O(n)
// @lc code=end

/**
 * Count frequency with a HashMap
 * Group numbers by frequency using bucket sort 
 * (since max frequency is nums.length)
 * Traverse buckets from high → low until you collect k elements
 */