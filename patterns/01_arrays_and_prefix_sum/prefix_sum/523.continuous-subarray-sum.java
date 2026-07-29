/*
 * @lc app=leetcode id=523 lang=java
 *
 * [523] Continuous Subarray Sum
 */

import java.util.*;

// @lc code=start
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        // remainder → earliest index where that remainder occurred
        HashMap<Integer, Integer> remainderIndex = new HashMap<>();
        remainderIndex.put(0, -1);

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int remainder = k == 0? sum: sum % k;

            // if seen remainder before
            if (remainderIndex.containsKey(remainder)) {
                // check length >= 2
                if (i - remainderIndex.get(remainder) > 1) {
                    return true;
                }
            } else {
                remainderIndex.put(remainder, i);
            }
        }

        return false;
    }
}
// @lc code=end

// Time: O(n) 
// Space: O(min(n, k))

/*
If prefix[j] % k == prefix[i] % k, 
that means prefix[j] - prefix[i] is a multiple of k. 
And prefix[j] - prefix[i] is exactly the 
sum of the elements from index i+1 to j.
*/