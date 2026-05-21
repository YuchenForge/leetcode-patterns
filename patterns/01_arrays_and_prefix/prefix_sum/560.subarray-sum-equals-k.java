import java.util.HashMap;

/*
 * @lc app=leetcode id=560 lang=java
 *
 * [560] Subarray Sum Equals K
 */

// @lc code=start
class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // handles subarrays starting at index 0
        // prefixSum - k = 0
        map.put(0, 1); 

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            // Sum(i, j) = prefixSum[j] - prefixSum[i-1]
            // we look for prefixSum[j] - prefixSum[i-1] = k
            // prefixSum[i-1] = prefixSum[j] - k
            // at index j, we look for how many times prefixSum[j] - k appeared before 
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }

            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}
// @lc code=end

