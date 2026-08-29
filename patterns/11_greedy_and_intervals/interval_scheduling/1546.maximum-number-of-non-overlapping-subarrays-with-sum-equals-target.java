/*
 * @lc app=leetcode id=1546 lang=java
 *
 * [1546] Maximum Number of Non-Overlapping Subarrays With Sum Equals Target
 */

import java.util.*;

// @lc code=start
class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        HashSet<Integer> seenPrefixSums = new HashSet<>();
        seenPrefixSums.add(0);

        int prefixSum= 0;
        int count = 0;

        for (int num: nums) {
            prefixSum += num;

            // sum(l,r) = prefix[r] - prefix[l-1]
            if (seenPrefixSums.contains(prefixSum - target)) {
                count++;
                seenPrefixSums.clear();
                seenPrefixSums.add(0);
                prefixSum = 0;
            } else {
                seenPrefixSums.add(prefixSum);
            }
        }

        return count;
    }
}
// @lc code=end

