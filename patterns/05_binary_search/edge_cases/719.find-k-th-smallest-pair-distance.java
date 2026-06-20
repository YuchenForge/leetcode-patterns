/*
 * @lc app=leetcode id=719 lang=java
 *
 * [719] Find K-th Smallest Pair Distance
 */

import java.util.*;

// @lc code=start
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;
        int lo = 0;
        // max possible distance
        int hi = nums[n-1] - nums[0];  

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            
            if (countPairs(nums, mid) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    // find # of pairs nums[j] - nums[i] <= mid
    private int countPairs(int[] nums, int mid) {
        int count = 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            while (nums[j] - nums[i] > mid) {
                i++;
            }
            count += j - i;
        }
        return count;
    }
}


// Time: O(n log n + n log D)
// Space: O(1)
// @lc code=end

// count how many pairs have distance <= mid
// find smallest mid where count >= k

/**
Ex: nums = [1, 3, 5, 8], k = 3

all pairs and distances:
(1,3) → |1-3| = 2
(1,5) → |1-5| = 4
(1,8) → |1-8| = 7
(3,5) → |3-5| = 2
(3,8) → |3-8| = 5
(5,8) → |5-8| = 3

sorted distances: [2, 2, 3, 4, 5, 7]
                         ↑
                   k=3 → answer = 3

X=1 → count=0 < 3
X=2 → count=2 < 3
X=3 → count=3 >= 3 ← answer
X=4 → count=4 >= 3

 */