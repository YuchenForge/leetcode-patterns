/*
 * @lc app=leetcode id=875 lang=java
 *
 * [875] Koko Eating Bananas
 */

// @lc code=start
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // 1 < k < max(piles)
        int lo = 1;
        int hi = 0;

        for (int pile: piles) {
            hi = Math.max(hi, pile);
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isFeasible(piles, h, mid)) {
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }

        return lo;
        
    }

    private boolean isFeasible(int[] piles, int h, int k) {
        int sum = 0;
        for (int pile: piles) {
            sum += (pile + k - 1) / k;   
            if (sum > h) {
                return false;
            }
        }

        return true;
    }
}

// Time: O(n log M)
// Space: O(1)
// @lc code=end

