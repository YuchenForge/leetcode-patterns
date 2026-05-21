/*
 * @lc app=leetcode id=974 lang=java
 *
 * [974] Subarray Sums Divisible by K
 */

import java.util.*;

// @lc code=start
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // handles subarrays starting at index 0
        // prefixSum % k = 0
        map.put(0, 1); 

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

             // handle negative numbers
            int remainder = ((prefixSum % k) + k) % k;

            // Sum(i, j) = prefixSum[j] - prefixSum[i-1]
            // we look for (prefixSum[j] - prefixSum[i-1]) mod k = 0
            // prefixSum[j] mod k = prefixSum[i-1] mod k 
            // at index j, we look for how many times prefixSum[j] - k appeared before 
            if (map.containsKey(remainder)) {
                count += map.get(remainder);
            }

            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }
}
// @lc code=end

