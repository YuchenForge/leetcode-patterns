/*
 * @lc app=leetcode id=410 lang=java
 *
 * [410] Split Array Largest Sum
 */

// @lc code=start
class Solution {
    public int splitArray(int[] nums, int k) {
        // ans between nums max and nums total
        int lo = 0;
        int hi = 0;

        for (int num: nums) {
            lo = Math.max(lo, num);
            hi += num;
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (isFeasible(nums, k, mid)) {
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }

        return lo;
    
    }

    // Given a capacity mid
    // Use greedy to split nums into subarrays where each sum ≤ mid
    // Return if subarrays needed <= k
    private boolean isFeasible(int[] nums, int k, int capacity) {
        int sum = 0;
        int subarrays = 1;

        for (int n: nums) {
            if (sum + n > capacity) {
                sum = 0;
                subarrays += 1;
            }
            sum += n;
        }

        return subarrays <= k;

    }
}

// Time: O(n log S)
// Space: O(1)
// @lc code=end

